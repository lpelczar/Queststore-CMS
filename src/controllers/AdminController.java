package controllers;
import src.views.AdminView;
import models.*;
import dao.*;

public class AdminController {
    AdminView view = new AdminView();
    BlankUser BlankUserDAO = new BlankUser();
    List<BlankUser> blankUsersContainer = BlankUserDAO.getBlankUsers();

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
                handlePromoteBlankUser();
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

    public void promoteUser(BlankUser userToPromote) {
        try {
            boolean isPromoteToMentor = view.typeOfPromotion();

            if (isPromoteToMentor) {
                Mentor addMentor = Mentor( user.name,
                                           user.login,
                                           user.password,
                                           user.email,
                                           user.phoneNumber );
            } else {
                Student addStudent = Student( user.name,
                                              user.login,
                                              user.password,
                                              user.email,
                                              user.phoneNumber );
            }
        } catch (InputMismatchException e) {
            view.displayWrongSignError();
        }

    }

    public void handlePromoteBlankUser() {
        if (isBlankUsersExist()) {
            view.displayBlankUsers(blankUsersContainer);
            String userLogin = askForLoginToPromote();
            BlankUser userToPromote = BlankUserDAO.getUserByLogin(userLogin);
        }
        else {
            view.displayEmptyListMsg();
        }
    }

    public boolean isBlankUsersExist() {
        if (blankUsersContainer != null) {
            return true;
        }
        return false;
    }
}
