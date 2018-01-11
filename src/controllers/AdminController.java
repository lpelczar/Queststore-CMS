package controllers;
import java.util.InputMismatchException;
import java.util.List;

import views.AdminView;
import models.*;
import dao.*;

public class AdminController {

    private AdminView view = new AdminView();
    private BlankUserDAO blankUserDAO = new BlankUserDAO();
    private MentorDAO mentorDAO = new MentorDAO();
    private StudentDAO studentDAO = new StudentDAO();
    private UsersDAO usersDAO = new UsersDAO();
    private List<BlankUser> blankUsersContainer = blankUserDAO.getBlankUsers();
    private boolean isRunning = true;

    public void start() {
        int option = 0;

        while (isRunning) {
            view.clearConsole();
            view.handleAdminMenu();

            option = view.askForOption();

            if (option == 1) {
                handlePromoteBlankUser();
            }

            else if (option == 2) {
                handleEditProfile();

            }
            else if (option == 3) {
                // Create new group

            }
            else if (option == 4) {
                // Edit level treshold

            }
            else if (option == 5) {
                // Show all accounts with details

            }
            else if (option == 6) {
                isRunning = false;
            }
        }
    }

    private void handlePromoteBlankUser() {
        if (isBlankUsersExist()) {

            view.displayBlankUsers(blankUsersContainer);
            String login = view.askForLogin();

            BlankUser user = blankUserDAO.getBlankUserBy(login);
            promote(user);
        }
        else {
            view.displayEmptyListMsg();
        }
    }

    private boolean isBlankUsersExist() {
        if (blankUsersContainer != null) {
            return true;
        }
        return false;
    }

    private void promote(BlankUser user) {
        try {
            boolean isPromoteToMentor = view.typeOfPromotion();

            if (isPromoteToMentor) {
                Mentor mentor = new Mentor( user.getName(),
                                            user.getLogin(),
                                            user.getPassword(),
                                            user.getEmail(),
                                            user.getPhoneNumber() );

                mentorDAO.addMentor(mentor);

            } else {
                Student student = new Student( user.getName(),
                                               user.getLogin(),
                                               user.getPassword(),
                                               user.getEmail(),
                                               user.getPhoneNumber(),
                                               0 );

                studentDAO.addStudent(student);
            }

            blankUserDAO.removeBlankUser(user);

        } catch (InputMismatchException e) {
            view.displayWrongSignError();
        }
    }

    private void handleEditProfile() {
        List<Mentor> mentorContainer = mentorDAO.getMentors();
        List<Student> studentContainer = studentDAO.getStudents();

        view.displayMentors(mentorContainer);
        view.displayStudents(studentContainer);

        String login = view.askForLogin();
        User profileToEdit = usersDAO.getUserBy(login);
        updateProfileAttribute(profileToEdit);

        studentDAO.saveAllStudents();
        mentorDAO.saveAllMentors();
    }

    private void updateProfileAttribute(User profile) {
        int toChange = view.askForChangeInProfile(profile);

        if (toChange == 1) {
            String name = view.askForInput();
            profile.setName(name);
        }
        else if (toChange == 2) {
            String login = view.askForInput();
            profile.setLogin(login);
        }
        else if (toChange == 3) {
            String email = view.askForInput();
            profile.setEmail(email);
        }
        else if (toChange == 4) {
            String phoneNumber = view.askForInput();
            profile.setPhoneNumber(phoneNumber);
        }
        else {
            view.displayWrongSignError();
        }
    }
}
