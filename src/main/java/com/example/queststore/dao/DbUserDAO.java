package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.data.statements.UserStatement;
import com.example.queststore.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbUserDAO extends DbHelper implements UserDAO {

    private UserStatement userStatement = new UserStatement();

    @Override
    public List<User> getAll() {

        String sqlStatement = userStatement.selectAllUsers();
        PreparedStatement statement = null;
        try {
            statement = getPreparedStatement(sqlStatement);
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return getUsers(statement);
    }

    @Override
    public List<User> getAllByRole(String role) {

        String sqlStatement = userStatement.selectAllUsersByRole();
        PreparedStatement statement = null;
        try {
            statement = getPreparedStatement(sqlStatement);
            statement.setString(1, role);
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return getUsers(statement);
    }

    private List<User> getUsers(PreparedStatement statement) {
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
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return users;
    }

    @Override
    public User getById(int id) {

        String sqlStatement = userStatement.selectUserById();
        PreparedStatement statement = null;
        try {
            statement = getPreparedStatement(sqlStatement);
            statement.setInt(1, id);
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return getUser(statement);
    }

    public User getByLoginAndPassword(String login, String password) {

        String sqlStatement = userStatement.selectUserByLoginAndPassword();
        PreparedStatement statement = getPreparedStatementBy(login, password, sqlStatement);
        return getUser(statement);
    }

    private PreparedStatement getPreparedStatementBy(String param1, String param2, String sqlStatement) {
        PreparedStatement statement = null;
        try {
            statement = getPreparedStatement(sqlStatement);
            statement.setString(1, param1);
            statement.setString(2, param2);
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return statement;
    }

    public User getByLoginAndRole(String login, String role) {

        String sqlStatement = userStatement.selectUserByLoginAndRole();
        PreparedStatement statement = getPreparedStatementBy(login, role, sqlStatement);
        return getUser(statement);
    }

    public User getByLogin(String login) {

        String sqlStatement = userStatement.selectUserByLogin();
        PreparedStatement statement = null;
        try {
            statement = getPreparedStatement(sqlStatement);
            statement.setString(1, login);
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return getUser(statement);
    }

    private User getUser(PreparedStatement statement) {
        User user = null;
        try {
            ResultSet resultSet = query(statement);
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
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return user;
    }

    @Override
    public boolean add(User user) {
        String sqlStatement = userStatement.insertUserStatement();
        PreparedStatement statement = null;
        try {
            statement = getPreparedStatement(sqlStatement);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getPhoneNumber());
            statement.setString(6, user.getRole());
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return update(statement);
    }

    @Override
    public boolean update(User user) {
        String sqlStatement = userStatement.updateUserStatement();
        PreparedStatement statement = null;
        try {
            statement = getPreparedStatement(sqlStatement);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getPhoneNumber());
            statement.setString(6, user.getRole());
            statement.setInt(7, user.getId());
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return update(statement);
    }

    @Override
    public boolean delete(User user) {
        String sqlStatement = userStatement.deleteUserStatement();
        PreparedStatement statement = null;
        try {
            statement = getPreparedStatement(sqlStatement);
            statement.setInt(1, user.getId());
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return update(statement);
    }
}
