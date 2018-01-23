package dao;

import models.User;

import java.util.List;

public interface UserDAO {
    List<User> getAll();
    User getById(int id);
    boolean add(User user);
    boolean update(User user);
    boolean delete(User user);
}
