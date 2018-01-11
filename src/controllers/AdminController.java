package controllers;
import src.views.AdminView;
import models.*;
import dao.*;

public class AdminController {
    AdminView view = new AdminView();
    BlankUser BlankUserDAO = new BlankUser();

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
                //Promote blank user, add argument (list with users) to display method
                view.displayBlankUsers()
                String userLogin = askForLoginToPromote();
                BlankUser user = BlankUserDAO.getUserByLogin(userLogin);

                Admin addAdmin = Admin( user.name,
                                        user.login,
                                        user.password,
                                        user.email,
                                        user.phoneNumber );




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
