package dao;

import models.User;

import java.util.ArrayList;
import java.util.List;

public class UsersDAO extends AbstractDAO {

    private List<User> usersList;

    public UsersDAO() {
        usersList = new ArrayList<>();
    }
}
