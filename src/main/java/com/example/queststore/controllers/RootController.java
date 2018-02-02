package com.example.queststore.controllers;


import com.example.queststore.dao.DbUserDAO;
import com.example.queststore.dao.UserDAO;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.models.User;
import com.example.queststore.utils.EmailValidator;
import com.example.queststore.utils.PhoneValidator;
import com.example.queststore.views.RootView;

public class RootController {

    private UserDAO dbUserDAO;
    private RootView rootView;
    private AdminController adminController;
    private StudentController studentController;
    private MentorController mentorController;

    public RootController() {

        this.dbUserDAO = new DbUserDAO();
        this.rootView = new RootView();
        this.adminController = new AdminController();
        this.studentController = new StudentController();
        this.mentorController = new MentorController();
    }


    public void start() {
        boolean isAppRunning = true;

        while (isAppRunning) {
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
                    isAppRunning = false;
                    break;
                default:
                    rootView.displayWrongInputMessage();
            }
        }
    }

    private void signIn() {

        final String QUIT_OPTION = "q";
        boolean isLoggedIn = false;
        String login;
        String password;
        User user;

        while(!isLoggedIn) {

            login = rootView.getUserLogin();
            if (login.equals(QUIT_OPTION)) return;
            password = rootView.getUserPassword();
            if (password.equals(QUIT_OPTION)) return;
            user = dbUserDAO.getByLoginAndPassword(login, password);

            if(user != null) {
                isLoggedIn = true;
                if (user.getRole().equals(UserEntry.BLANK_USER_ROLE)) {
                    rootView.displayUserNotAssignedMessage();
                } else if (user.getRole().equals(UserEntry.STUDENT_ROLE)) {
                    studentController.start(user.getId());
                } else if (user.getRole().equals(UserEntry.MENTOR_ROLE)) {
                    mentorController.start();
                } else if (user.getRole().equals(UserEntry.ADMIN_ROLE)) {
                    adminController.start();
                }
            } else {
                rootView.displayUserNotExistsMessage();
            }
        }
    }

    private void signUp() {

        boolean isUserCreated = false;
        String name;
        String login;
        String email;
        String password;
        String phoneNumber;
        User user;

        while(!isUserCreated) {
            login = createUserLogin();
            user = dbUserDAO.getByLogin(login);
            if (user != null) {
                rootView.displayUserWithThisNameAlreadyExists();
            } else {
                password = createUserPassword();
                name = createUserName();
                email = createUserEmail();
                phoneNumber = createUserPhoneNumber();
                this.dbUserDAO.add(new User(name, login, email, password, phoneNumber, "Blank"));
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
