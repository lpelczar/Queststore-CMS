package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.data.statements.UserStatement;
import com.example.queststore.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbUserDAO extends DbHelper implements UserDAO {

    private UserStatement userStatement = new UserStatement();

    @Override
    public List<User> getAll() {

        String statement = userStatement.selectAllUsers();
        return getUsers(statement);
    }

    @Override
    public List<User> getAllByRole(String role) {
        String statement = userStatement.selectAllUsersByRole(role);
        return getUsers(statement);
    }

    private List<User> getUsers(String statement) {
        List<User> users = new ArrayList<>();
        try {
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                users.add(new User(
                        resultSet.getInt(UserEntry.ID),
                        resultSet.getString(UserEntry.NAME),
                        resultSet.getString(UserEntry.LOGIN),
                        resultSet.getString(UserEntry.EMAIL),
                        resultSet.getString(UserEntry.PASSWORD),
                        resultSet.getString(UserEntry.PHONE_NUMBER),
                        resultSet.getString(UserEntry.ROLE)));
            resultSet.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return users;
    }

    @Override
    public User getById(int id) {

        String statement = userStatement.selectUserById(id);
        return getUser(statement);
    }

    public User getByLoginAndPassword(String login, String password) {

        String statement = userStatement.selectUserByLoginAndPassword(login, password);
        return getUser(statement);
    }

    public User getByLoginAndRole(String login, String role) {

        String statement = userStatement.selectUserByLoginAndRole(login, role);
        return getUser(statement);
    }

    public User getByLogin(String login) {

        String statement = userStatement.selectUserByLogin(login);
        return getUser(statement);
    }

    private User getUser(String sqlStatement) {

        User user = null;
        try {
            ResultSet resultSet = query(sqlStatement);
            while (resultSet.next())
                user = new User(
                        resultSet.getInt(UserEntry.ID),
                        resultSet.getString(UserEntry.NAME),
                        resultSet.getString(UserEntry.LOGIN),
                        resultSet.getString(UserEntry.EMAIL),
                        resultSet.getString(UserEntry.PASSWORD),
                        resultSet.getString(UserEntry.PHONE_NUMBER),
                        resultSet.getString(UserEntry.ROLE));
            resultSet.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return user;
    }

    @Override
    public boolean add(User user) {
        String statement = userStatement.insertUserStatement(user);
        return update(statement);
    }

    @Override
    public boolean update(User user) {
        String statement = userStatement.updateUserStatement(user);
        return update(statement);
    }

    @Override
    public boolean delete(User user) {
        String statement = userStatement.deleteUserStatement(user);
        return update(statement);
    }
}
