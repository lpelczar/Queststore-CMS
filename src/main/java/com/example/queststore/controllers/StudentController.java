package com.example.queststore.controllers;


import com.example.queststore.dao.DbStudentDataDAO;
import com.example.queststore.dao.StudentDataDAO;
import com.example.queststore.models.StudentData;
import com.example.queststore.services.StudentService;
import com.example.queststore.views.StudentView;

import java.util.InputMismatchException;

public class StudentController {

    private StudentView view;
    private StudentDataDAO studentDataDAO;

    public StudentController(StudentView view, StudentDataDAO studentDataDAO) {
        this.view = view;
        this.studentDataDAO = studentDataDAO;
    }

    public void start(int studentId) {
        StudentData student = getStudentDataBy(studentId);
        StudentService studentService = new StudentService(student);
        boolean isLoopEnd = false;
        int option = 0;

        while (!isLoopEnd) {
            view.displayInfoBar(student.getBalance(), student.getLevel());
            view.handleStudentMenu();

            try {
                option = view.askForOption();
            }
            catch (InputMismatchException e) {
                System.err.println("Wrong option!");
            }
            switch (option) {
                case 1:
                    studentService.showStudentBackPack(studentId);
                    break;
                case 2:
                    studentService.buyArtifact(studentId);
                    break;
                case 3:
                    studentService.buyArtifactForTeam();
                    break;
                case 4:
                    isLoopEnd = true;
            }
        }
    }

    private StudentData getStudentDataBy(int student_id) {
        return studentDataDAO.getStudentDataBy(student_id);
    }
}
