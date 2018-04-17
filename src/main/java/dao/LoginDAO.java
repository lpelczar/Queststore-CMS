package dao;

import model.database.User;

public interface LoginDAO {

    User getById(int id);
    User getByLoginAndPassword(String login, String password);
}
