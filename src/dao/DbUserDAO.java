package dao;

import models.User;

import java.util.ArrayList;
import java.util.List;

public class UsersDAO extends AbstractDAO {

    private List<User> usersList = new ArrayList<>();

    public UsersDAO() { readAllUsers();}

    @SuppressWarnings("unchecked")
    private void readAllUsers() {

        this.usersList.addAll(new BlankUserDAO().getBlankUsers());
        this.usersList.addAll(new AdminDAO().getAdmins());

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
