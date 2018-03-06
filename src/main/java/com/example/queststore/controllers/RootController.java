package com.example.queststore.controllers;


import com.example.queststore.dao.UserDAO;
import com.example.queststore.data.DbHelper;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.models.User;
import com.example.queststore.utils.EmailValidator;
import com.example.queststore.utils.PhoneValidator;
import com.example.queststore.views.RootView;

public class RootController {

    private UserDAO userDAO;
    private RootView rootView;
    private AdminController adminController;
    private StudentController studentController;
    private MentorController mentorController;

    private final int MIN_LENGTH = 6;
    private final int MAX_LENGTH = 15;

    public RootController(UserDAO userDAO, RootView rootView, AdminController adminController,
                          StudentController studentController, MentorController mentorController) {
        this.userDAO = userDAO;
        this.rootView = rootView;
        this.adminController = adminController;
        this.studentController = studentController;
        this.mentorController = mentorController;
    }

    public void start() {
        boolean isAppRunning = true;
        checkDatabaseSetup();

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

    private void checkDatabaseSetup() {
        DbHelper dbHelper = new DbHelper();
        if (!dbHelper.isDatabaseFileExists()) {
            dbHelper.createDatabase();
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
            user = userDAO.getByLoginAndPassword(login, password);

            if(user != null) {
                isLoggedIn = true;
                switch (user.getRole()) {
                    case UserEntry.BLANK_USER_ROLE:
                        rootView.displayUserNotAssignedMessage();
                        break;
                    case UserEntry.STUDENT_ROLE:
                        studentController.start(user.getId());
                        break;
                    case UserEntry.MENTOR_ROLE:
                        mentorController.start();
                        break;
                    case UserEntry.ADMIN_ROLE:
                        adminController.start();
                        break;
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

        while(!isUserCreated) {
            login = createUserLogin();
            if (userDAO.getByLogin(login) != null) {
                rootView.displayUserWithThisNameAlreadyExists();
                return;
            }
            email = createUserEmail();
            if (userDAO.getByEmail(email) != null) {
                rootView.displayUserWithThisEmailAlreadyExists();
                return;
            }
            phoneNumber = createUserPhoneNumber();
            if (userDAO.getByPhoneNumber(phoneNumber) != null) {
                rootView.displayUserWithThisPhoneNumberAlreadyExists();
                return;
            }
            password = createUserPassword();
            name = createUserName();
            this.userDAO.add(new User(name, login, email, password, phoneNumber, UserEntry.BLANK_USER_ROLE));
            rootView.displayUserCreated(login, name, email, phoneNumber);
            isUserCreated = true;
        }
    }

    private String createUserLogin() {

        String login = null;
        boolean isCorrectInput = false;

        while(!isCorrectInput) {
            login = rootView.getNewUserLogin();
            if (login.length() >= MIN_LENGTH && login.length() <= MAX_LENGTH) {
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
            if (password.length() >= MIN_LENGTH && password.length() <= MAX_LENGTH) {
                isCorrectInput = true;
            }
        }
        return password;
    }

    private String createUserName() {
        return rootView.getNewUserName();
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
