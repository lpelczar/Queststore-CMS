package com.example.queststore.controllers.web;

import com.example.queststore.dao.GroupDAO;
import com.example.queststore.dao.StudentDataDAO;
import com.example.queststore.dao.UserDAO;
import com.example.queststore.dao.sqlite.SqliteGroupDAO;
import com.example.queststore.dao.sqlite.SqliteStudentDataDAO;
import com.example.queststore.dao.sqlite.SqliteUserDAO;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.models.Student;
import com.example.queststore.models.StudentData;
import com.example.queststore.models.User;
import com.example.queststore.utils.FormDataParser;
import com.sun.net.httpserver.HttpExchange;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

class StudentHandler {

    private HttpExchange httpExchange;
    private StudentDataDAO studentDataDAO = new SqliteStudentDataDAO();
    private UserDAO userDAO = new SqliteUserDAO();
    private GroupDAO groupDAO = new SqliteGroupDAO();
    private int mentorId;

    StudentHandler(HttpExchange httpExchange, int mentorId) {
        this.httpExchange = httpExchange;
        this.mentorId = mentorId;
    }

    void handle(String formData) throws IOException {
        if (formData.contains("add-student-to-group")) {
            handleAddingStudentGroupConnection(formData);
        }
    }

    private void handleAddingStudentGroupConnection(String formData) throws IOException {
        final int STUDENT_ID_INDEX = 0;
        final int GROUP_ID_INDEX = 1;
        List<String> values = new FormDataParser().getValues(formData);
        int studentId = Integer.parseInt(values.get(STUDENT_ID_INDEX));
        int groupId = Integer.parseInt(values.get(GROUP_ID_INDEX));
        StudentData studentData = studentDataDAO.getStudentDataByStudentId(studentId);
        studentData.setGroupId(groupId);
        studentDataDAO.updateStudentData(studentData);
        httpExchange.getResponseHeaders().add("Location", "/mentor/" + mentorId + "/students");
        httpExchange.sendResponseHeaders(301, -1);
    }

    void showStudentsPage() throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/display_students.twig");
        JtwigModel model = JtwigModel.newModel();
        List<Student> students = new ArrayList<>();
        for (User u : userDAO.getAllByRole(UserEntry.STUDENT_ROLE)) {
            Student student = new Student(u);
            StudentData studentData = studentDataDAO.getStudentDataByStudentId(student.getId());
            student.setStudentData(studentData);
            student.setGroup(groupDAO.getById(studentData.getGroupId()));
            students.add(student);
        }
        model.with("students", students);
        sendResponse(httpExchange, template.render(model));
    }

    private void sendResponse(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
