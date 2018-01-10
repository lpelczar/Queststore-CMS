package controllers;


import views.AdminView;

import java.util.InputMismatchException;

public class AdminController {
    AdminView view = new AdminView();

    private boolean isRunning = true;

    public void start() {
        int option = 0;

        while (isRunning) {
            view.handleAdminMenu();

            try {
                option = view.askForOption();
            }
            catch (InputMismatchException e) {
                System.err.println("You type wrong sign!");
            }

            if (option == 1) {
                //Promote blank user, add argument (list with users) to display method
                //view.displayBlankUsers();
                String userLogin = view.askForLoginToPromote();
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

    public void promoteUser() {
        boolean userTypePromote = view.typeOfPromotion();
        if (userTypePromote) {
            
        }
    }
}
