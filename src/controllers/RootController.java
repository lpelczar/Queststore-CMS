package controllers;

import models.User;
import views.RootView;

public class RootController {

    private UsersDAO usersDAO;
    private RootView rootView;

    RootController() {

        this.usersDAO = new UsersDAO();
        this.rootView = new RootView();
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
                } else (user instanceof Student) {
                    studentController.start();
                } else (user instanceof Mentor) {
                    mentorController.start();
                } else (user instanceof Admin) {
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
        String password;
        String user;

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
                usersDAO.addUser(new BlankUser(name, login, password, email, phoneNumber));
                rootView.displayUserCreated(login, name, email, phoneNumber);
                isUserCreated = true;
            }
        }
    }

    private String createUserLogin() {

        String login = null;
        boolean isCorrectInput = false;

        while(!isCorrectInput) {
            login = rootView.createUserLogin();
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
            password = rootView.createUserPassword();
            if (password.length() >= 6 && password.length() <= 15) {
                isCorrectInput = true;
            }
        }
        return password;
    }
}
