package controllers;
import java.util.InputMismatchException;
import views.AdminView;
import models.*;
import dao.*;

public class AdminController {
    AdminView view = new AdminView();
    BlankUserDAO blankUserDAO = new BlankUserDAO();
    MentorDAO mentorDAO = new MentorDAO();
    StudentDAO studentDAO = new StudentDAO();
    UsersDAO usersDAO = new UsersDAO();

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
            String login = view.askForLogin();

            User user = usersDAO.getUserBy(login);
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

    public void promote(User user) {
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

            blankUserDAO.removeUser(user);

        } catch (InputMismatchException e) {
            view.displayWrongSignError();
        }
    }

    public void handleEditProfile() {
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

    public void updateProfileAttribute(User profile) {
        toChange = view.askForChangeInProfile(profile);

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
            profile.setPhoneNumber(setPhoneNumber);
        }
        else {
            view.displayWrongSignError();
        }
    }
}
