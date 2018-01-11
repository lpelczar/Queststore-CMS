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
    private List<BlankUser> blankUsersContainer;
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
                //Edit mentor data and his class
                handleEditProfile();

            }
            else if (option == 3) {
                // I want to create a class,
                // So I can assign Mentors to their classes.

            }
            else if (option == 4) {
                // I want to create levels of experience based on amount of earning coolcoins,
                // So Codecoolers can achieve them.
            }
            else if (option == 5) {
                handleShowingMentorProfile();
                // I want to see a Mentor' s profile,
                // So I can view his personal data and Codecoolers in his class.

            }
            else if (option == 6) {
                isRunning = false;
            }
        }
    }

    private void handleShowingMentorProfile() {
        view.displayMentors(mentorDAO.getMentors());
        String login = view.askForLogin();

        Mentor mentor = mentorDAO.getMentorBy(login);

        if (mentor != null) {
            view.displayMentorProfile(mentor);
            // Display mentor class info!!
        } else {
            view.displayNoMentorMessage();
        }
    }

    private void handlePromoteBlankUser() {
        blankUsersContainer = blankUserDAO.getBlankUsers();
        boolean isEmpty = checkIfBlankUsersExist();

        if (isEmpty == false) {
            view.displayBlankUsers(blankUsersContainer);
            String login = view.askForLogin();

            BlankUser user = blankUserDAO.getBlankUserBy(login);
            promote(user);
        }
        else if (isEmpty) {
            view.displayEmptyListMsg();
        }
    }

    private boolean checkIfBlankUsersExist() {
        if (blankUsersContainer != null) {
            return false;
        }
        return true;
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
