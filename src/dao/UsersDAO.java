package dao;

import models.User;

import java.util.ArrayList;
import java.util.List;

public class UsersDAO extends AbstractDAO {

    private List<User> usersList = new ArrayList<>();
    private String FILE_PATH = "src/data/users.ser";

    public UsersDAO() {}

    @SuppressWarnings("unchecked")
    private void readAllUsers() {

        usersList = new ArrayList<>();
        this.usersList = (ArrayList<User>) readAllData(FILE_PATH);
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

    public User getUserByLogin(String login) {

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
