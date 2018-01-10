package controllers;

import dao.*;
import models.*;
import services.*;
import views.RootView;

public class RootController {

    private UsersDAO usersDAO;
    private BlankUserDAO blankUserDAO;
    private RootView rootView;
    private AdminController adminController;
    private StudentController studentController;
    private MentorController mentorController;

    public RootController() {

        this.usersDAO = new UsersDAO();
        this.blankUserDAO = new BlankUserDAO();
        this.rootView = new RootView();
        this.adminController = new AdminController();
        this.studentController = new StudentController();
        this.mentorController = new MentorController();
    }

    public void start() {
        boolean shouldExit = false;

        while (!shouldExit) {
            rootView.displayMenu();
            String userInput = rootView.getUserInput();
            switch (userInput) {
                case "1":
                    signIn();
                    break;
                case "2":
                    signUp();
                    break;
                case "0":
                    shouldExit = true;
                    break;
                default:
                    rootView.displayWrongInputMessage();
            }
        }
    }

    private void signIn() {

        boolean isLoggedIn = false;
        String login;
        String password;
        User user;

        while(!isLoggedIn) {

            login = rootView.getUserLogin();
            password = rootView.getUserPassword();
            user = usersDAO.getUserByLoginAndPassword(login, password);

            if(user != null) {
                isLoggedIn = true;
                if (user instanceof BlankUser) {
                    rootView.displayUserNotAssignedMessage();
                } else if (user instanceof Student) {
                    studentController.start();
                } else if (user instanceof Mentor) {
                    mentorController.start();
                } else if (user instanceof Admin) {
                    adminController.start();
                }
            } else {
                rootView.displayUserNotExistsMessage();
            }
        }
    }

    private void signUp() {

        boolean isUserCreated = false;
        String login;
        String name;
        String email;
        String phoneNumber;
        String password;
        User user;

        while(!isUserCreated) {
            login = createUserLogin();
            user = usersDAO.getUserByLogin(login);
            if (user != null) {
                rootView.displayUserWithThisNameAlreadyExists();
            } else {
                password = createUserPassword();
                name = createUserName();
                email = createUserEmail();
                phoneNumber = createUserPhoneNumber();
                this.blankUserDAO.addBlankUser(new BlankUser(name, login, password, email, phoneNumber));
                rootView.displayUserCreated(login, name, email, phoneNumber);
                isUserCreated = true;
            }
        }
    }

    private String createUserLogin() {

        String login = null;
        boolean isCorrectInput = false;

        while(!isCorrectInput) {
            login = rootView.getNewUserLogin();
            if (login.length() >= 6 && login.length() <= 15) {
                isCorrectInput = true;
            }
        }
        return login;
    }

    private String createUserPassword() {

        String password = null;
        boolean isCorrectInput = false;

        while(!isCorrectInput) {
            password = rootView.getNewUserPassword();
            if (password.length() >= 6 && password.length() <= 15) {
                isCorrectInput = true;
            }
        }
        return password;
    }

    private String createUserName() {

        String name = null;
        boolean isCorrectInput = false;

        while(!isCorrectInput) {
            name = rootView.getNewUserName();
            if (name.length() > 1) {
                isCorrectInput = true;
            }
        }
        return name;
    }

    private String createUserEmail() {

        String email = null;
        boolean isCorrectInput = false;

        while(!isCorrectInput) {
            email = rootView.getNewUserEmail();
            if (new EmailValidator().validate(email)) {
                isCorrectInput = true;
            }
        }
        return email;
    }

    private String createUserPhoneNumber() {

        String phoneNumber = null;
        boolean isCorrectInput = false;

        while(!isCorrectInput) {
            phoneNumber = rootView.getNewUserPhoneNumber();
            if (new PhoneValidator().validate(phoneNumber)) {
                isCorrectInput = true;
            }
        }
        return phoneNumber;
    }
}