package controller;

import dao.SqliteUserDAO;
import utils.InputGetter;
import view.LoginView;


class LoginController {

    private SqliteUserDAO loginDB;
    private LoginView view;

    LoginController() {
        this.loginDB = new SqliteUserDAO();
        this.view = new LoginView();
    }


    String[] processValidation() {

        int idColumn = 0;
        int roleColumn = 1;
        int counter = 3;
        int arrayCapacity = 2;

        String[] idAndRole = new String[arrayCapacity];
        Boolean process = true;

        while (process && (counter > 0)) {
            String login = InputGetter.getString("Please enter your login");
            String password = InputGetter.getString("Please enter your password");
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
 }
