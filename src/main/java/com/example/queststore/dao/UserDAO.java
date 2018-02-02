package com.example.queststore.dao;

import com.example.queststore.models.User;

import java.util.List;

public interface UserDAO {
    List<User> getAll();
    List<User> getAllByRole(String role);
    User getById(int id);
    User getByLoginAndPassword(String login, String password);
    User getByLoginAndRole(String login, String role);
    User getByLogin(String login);
    boolean add(User user);
    boolean update(User user);
    boolean delete(User user);
}
