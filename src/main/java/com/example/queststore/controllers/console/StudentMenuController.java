package com.example.queststore.controllers.console;


import com.example.queststore.dao.StudentDataDAO;
import com.example.queststore.models.StudentData;
import com.example.queststore.views.console.StudentView;

import java.util.InputMismatchException;

public class StudentMenuController {

    private StudentView studentView;
    private StudentDataDAO studentDataDAO;
    private StudentController studentController;

    public StudentMenuController(StudentView studentView, StudentDataDAO studentDataDAO, StudentController studentController) {
        this.studentView = studentView;
        this.studentDataDAO = studentDataDAO;
        this.studentController = studentController;
    }

    void start(int studentId) {
        StudentData student = getStudentDataBy(studentId);
        boolean isLoopEnd = false;
        int option = 0;

        while (!isLoopEnd) {
            studentView.displayInfoBar(student.getBalance(), student.getLevel());
            studentView.handleStudentMenu();

            try {
                option = studentView.askForOption();
            }
            catch (InputMismatchException e) {
                System.err.println("Wrong option!");
            }
            switch (option) {
                case 1:
                    studentController.showStudentBackPack(studentId);
                    break;
                case 2:
                    studentController.buyArtifact(studentId);
                    break;
                case 3:
                    studentController.buyArtifactForTeam(studentId);
                    break;
                case 4:
                    isLoopEnd = true;
            }
        }
    }

    private StudentData getStudentDataBy(int student_id) {
        return studentDataDAO.getStudentDataByStudentId(student_id);
    }
}
