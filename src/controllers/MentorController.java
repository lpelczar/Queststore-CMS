package controllers;


import views.MentorView;

import java.util.InputMismatchException;

public class MentorController {
    MentorView view = new MentorView();

    private boolean isRunning = true;

    public void start() {
        int option = 0;

        while (isRunning) {
            view.handleMentorMenu();

            try {
                option = view.askForOption();
            }
            catch (InputMismatchException e) {
                System.err.println("You type wrong sign!");
            }

            if (option == 1) {
                //Promote blank user to Student, add argument (list with users) to display method
                //view.displayBlankUsers();
                String userLogin = view.askForLoginToPromote();
                ;
            }
            else if (option == 2) {
                // Add new task
                ;
            }
            else if (option == 3) {
                // Add new item
                ;
            }
            else if (option == 4) {
                // Edit quest
                ;
            }
            else if (option == 5) {
                // Edit item
                ;
            }
            else if (option == 5) {
                // Mark Student's task
                ;
            }
            else if (option == 5) {
                // Mark Students's bought items
                ;
            }
            else if (option == 5) {
                // See Student's backpack
                ;
            }
            else if (option == 6) {
                isRunning = false;
            }
        }
    }

}
