package dao;

import models.User;

import java.util.ArrayList;
import java.util.List;

public class UsersDAO extends AbstractDAO {

    private List<User> usersList = new ArrayList<>();

    public UsersDAO() {}

    @SuppressWarnings("unchecked")
    private void readAllUsers() {

        usersList = new ArrayList<>();
        String filepath = "src/data/users.ser";
        this.usersList = (ArrayList<User>) readAllData(filepath);
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
}
