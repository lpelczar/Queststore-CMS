package controllers;
import src.views.AdminView;

public class AdminController {
    AdminView view = new AdminView();

    private boolean isRunning = true;

    public void start() {
        while (isRunning) {
            view.handleAdminMenu();

            try {
                int option = view.askForOption();
            }
            catch (InputMismatchException e) {
                System.err.println("You type wrong sign!");
            }

            if (option == 1) {
                //Promote blank user
                ;
            }
            else if (option == 2) {
                // Edit user profile
                ;
            }
            else if (option == 3) {
                // Create new group
                ;
            }
            else if (option == 4) {
                // Edit level treshold
                ;
            }
            else if (option == 5) {
                // Show all accounts with details
                ;
            }
            else if (option == 6) {
                isRunning = false;
            }
        }

    }
}
