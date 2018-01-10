package dao;

import models.User;

import java.util.ArrayList;
import java.util.List;

public class UsersDAO extends AbstractDAO {

    public UsersDAO() {}

    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {

        List<User> usersList = new ArrayList<>();
        String filepath = "src/data/users.ser";
        usersList = (ArrayList<User>) readAllData(filepath);
        return usersList;
    }
}
