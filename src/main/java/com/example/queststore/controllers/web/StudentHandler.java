package com.example.queststore.controllers.web;

import com.example.queststore.dao.StudentDataDAO;
import com.example.queststore.dao.sqlite.SqliteStudentDataDAO;
import com.example.queststore.models.StudentData;
import com.example.queststore.utils.FormDataParser;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;

class StudentHandler {

    private HttpExchange httpExchange;
    private StudentDataDAO studentDataDAO = new SqliteStudentDataDAO();
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
}
