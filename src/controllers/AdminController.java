package controllers;
import java.util.InputMismatchException;
import src.views.AdminView;
import models.*;
import dao.*;

public class AdminController {
    AdminView view = new AdminView();
    BlankUserDAO blankUserDAO = new BlankUserDAO();
    MentorDAO mentorDAO = new MentroDAO();
    StudentDAO studentDAO = new StudentDAO();

    List<BlankUser> blankUsersContainer = blankUserDAO.getBlankUsers();

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
                handlePromoteBlankUser();
            }

            else if (option == 2) {
                handleEditProfile();
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

    public void handlePromoteBlankUser() {
        if (isBlankUsersExist()) {

            view.displayBlankUsers(blankUsersContainer);
            String login = askForLoginToPromote();

            BlankUser user = blankUserDAO.getUserBy(login);
            promote(user);
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

    public void promote(BlankUser user) {
        try {
            boolean isPromoteToMentor = view.typeOfPromotion();

            if (isPromoteToMentor) {
                Mentor mentor = new Mentor( user.name,
                                            user.login,
                                            user.password,
                                            user.email,
                                            user.phoneNumber );

                mentorDAO.addMentor(mentor);

            } else {
                Student student = new Student( user.name,
                                               user.login,
                                               user.password,
                                               user.email,
                                               user.phoneNumber );

                studentDAO.addStudent(student);
            }

            blankUserDAO.removeBlankUser(user);

        } catch (InputMismatchException e) {
            view.displayWrongSignError();
        }
    }

    public void handleEditProfile() {

    }
}
