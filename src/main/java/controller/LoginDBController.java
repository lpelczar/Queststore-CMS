package controller;

import dao.LoginDBImplement;
import utils.ProcessManager;
import view.LoginView;

import java.sql.Connection;


public class LoginDBController {

    LoginDBImplement loginDB;
    LoginView view;

    public LoginDBController() {
        this.loginDB = new LoginDBImplement();
        this.view = new LoginView();
    }


    public String[] processValidation() {

        int idColumn = 0;
        int roleColumn = 1;
        int counter = 3;
        int arrayCapacity = 2;

        String[] idAndRole = new String[arrayCapacity];
        Boolean process = true;

        while (process && (counter > 0)) {
            String login = InputController.getString("Please enter your login");
            String password = InputController.getString("Please enter your password");
            idAndRole = loginDB.findUserIdAndRole(login, password);

            if (idAndRole[idColumn] == null || idAndRole[roleColumn] == null) {
                counter--;
                view.displayText("Login or password incorrect, try again!");

            } else {
                process = false;
            }
        }
        return idAndRole;
    }

    public void addUserLoginData() {

        String login = InputController.getString("Please enter user's login");
        String password = InputController.getString("Please enter user's password");
        String role = InputController.getString("Please enter user's role");

        loginDB.insertAllLoginData(login, password, role);
    }

    public void updateUserLoginData() {

        String login = InputController.getString("Please enter user's login");
        String password = InputController.getString("Please enter user's password");
        int role = InputController.getNumber("Please enter user's role");

        loginDB.updateUserLoginAndPassword(login, password, role);
    }

    public void deleteUserLoginData() {
        int userId = InputController.getNumber("Please enter users ID number");
        loginDB.deleteAllUserLoginData(userId);
    }
 }
