package controller;

import dao.LoginDAOImplement;
import view.LoginView;
import java.util.ArrayList;

public class LoginController {

    LoginDAOImplement loginDAO = new LoginDAOImplement();
    LoginView view = new LoginView();
    InputController userInput = new InputController();

    public String[] validateLoginData(String login, String password) {

        int idColumn = 0;
        int loginColumn = 1;
        int passwordColumn = 2;
        int roleColumn = 3;
        int id = 0;
        int role = 1;
        int arrayCapacity = 2;
        String[] idAndRole = new String[arrayCapacity];
        ArrayList<String[]> loginCollection = loginDAO.readDataFromFile();

        for (int x = 0; x < loginCollection.size(); x++) {
            String[] row = loginCollection.get(x);
            if (login.equals(row[loginColumn]) && password.equals(row[passwordColumn])) {
                idAndRole[id] = row[idColumn];
                idAndRole[role] = row[roleColumn];
            }
        }
        return idAndRole;
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
            idAndRole = this.validateLoginData(login, password);

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