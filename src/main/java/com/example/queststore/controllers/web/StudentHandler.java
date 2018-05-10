package com.example.queststore.controllers.web;

import com.example.queststore.dao.*;
import com.example.queststore.dao.sqlite.*;
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
    private ItemDAO itemDAO = new SqliteItemDAO();
    private TaskDAO taskDAO = new SqliteTaskDAO();
    private int mentorId;

    StudentHandler(HttpExchange httpExchange, int mentorId) {
        this.httpExchange = httpExchange;
        this.mentorId = mentorId;
    }

    void handle(String formData) throws IOException {
        if (formData.contains("add-student-to-group")) {
            handleAddingStudentGroupConnection(formData);
        } else if (formData.contains("show-student-details-action")) {
            sendStudentDetailsPage(formData);
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

    private void sendStudentDetailsPage(String formData) throws IOException {
        final int STUDENT_ID_INDEX = 0;
        List<String> values = new FormDataParser().getKeys(formData);
        int studentId = Integer.parseInt(values.get(STUDENT_ID_INDEX));
        Student student = new Student(userDAO.getById(studentId));
        StudentData studentData = studentDataDAO.getStudentDataByStudentId(student.getId());
        student.setStudentData(studentData);
        student.setGroup(groupDAO.getById(studentData.getGroupId()));
        student.setBoughtItems(itemDAO.getItemsByStudentId(studentId));
        student.setDoneTasks(taskDAO.getTasksByStudentId(studentId));
        System.out.println(student);
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student_details.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("student", student);
        sendResponse(httpExchange, template.render(model));
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
