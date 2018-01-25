package controllers;


import views.StudentView;
import java.util.InputMismatchException;
import java.util.List;
import models.Item;
import models.StudentData;
import dao.DbItemDAO;
import dao.DbStudentDataDAO;

public class StudentController {

    private StudentView view = new StudentView();
    private DbItemDAO dbItemDAO = new DbItemDAO();
    private DbStudentDataDAO dbStudentDataDAO = new DbStudentDataDAO();

    public void start(int student_id) {
        int option = 0;
        boolean isAppRunning = true;

        while (isAppRunning) {
            view.handleStudentMenu();

            try {
                option = view.askForOption();
            }
            catch (InputMismatchException e) {
                System.err.println("You type wrong sign!");
            }
            if (option == 1) {
                showStudentBackPack(student_id);
            } else if (option == 2) {
            } else if (option == 3) {
            } else if (option == 4) {
                showStudentLevel(student_id);
            } else if (option == 5) {
                isAppRunning = false;
            }
        }
    }

    private void showStudentBackPack(int student_id) {
        List<Item> backpack = dbItemDAO.getItemsBy(student_id);
        view.displayStudentBackpack(backpack);
    }

    private void showStudentLevel(int student_id) {
        StudentData student = dbStudentDataDAO.getStudentLevelBy(student_id);
        String level = student.getLevel();
        view.displayStudentLevel(level);
    }

}
