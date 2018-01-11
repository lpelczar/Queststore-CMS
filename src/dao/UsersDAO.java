package dao;

import models.User;

import java.util.ArrayList;
import java.util.List;

public class UsersDAO extends AbstractDAO {

    private List<User> usersList = new ArrayList<>();
    private final String FILE_PATH = "src/data/users.ser";

    public UsersDAO() { readAllUsers();}

    @SuppressWarnings("unchecked")
    private void readAllUsers() {

        this.usersList.addAll(new BlankUserDAO().getBlankUsers());

        if (readAllData(FILE_PATH) != null) {
            this.usersList = (ArrayList<User>) readAllData(FILE_PATH);
        }
    }

    public User getUserByLoginAndPassword(String login, String password) {

        readAllUsers();
        User user = null;

        for (User u : usersList) {
            if (u.getLogin().equals(login) && u.getPassword().equals(password)) {
                user = u;
            }
        }
        return user;
    }

    public User getUserBy(String login) {

        readAllUsers();
        User user = null;

        for (User u : usersList) {
            if (u.getLogin().equals(login)) {
                user = u;
            }
        }
        return user;
    }
}
