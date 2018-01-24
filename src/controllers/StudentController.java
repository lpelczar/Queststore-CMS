package controllers;


import views.StudentView;
import java.util.InputMismatchException;
import dao.DbItemDAO;

public class StudentController {

    private StudentView view = new StudentView();
    private DbItemDAO dbItemDAO = new DbItemDAO();

    private boolean isRunning = true;

    public void start(int student_id) {
        int option = 0;

        while (isRunning) {
            view.handleStudentMenu();

            try {
                option = view.askForOption();
            }
            catch (InputMismatchException e) {
                System.err.println("You type wrong sign!");
            }

            if (option == 1) {
                // See Student's backpack
                List<Item> backpack = dbItemDAO.getItemsBy(student_id);
                displayStudentBackpack(backpack);
                ;
            }
            else if (option == 2) {
                // Buy item
                ;
            }
            else if (option == 3) {
                // Buy item with team
                ;
            }
            else if (option == 4) {
                // See Student's level
                ;
            }
            else if (option == 5) {
                isRunning = false;
            }
        }
    }

}
