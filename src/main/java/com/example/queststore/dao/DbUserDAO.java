package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.data.PreparedStatementCreator;
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
    private PreparedStatementCreator psc = new PreparedStatementCreator();

    @Override
    public List<User> getAll() {

        String sqlStatement = userStatement.selectAllUsers();
        PreparedStatement statement = psc.getPreparedStatementBy(sqlStatement);
        return getUsers(statement);
    }

    @Override
    public List<User> getAllByRole(String role) {

        String sqlStatement = userStatement.selectAllUsersByRole();
        PreparedStatement statement = psc.getPreparedStatementBy(role, sqlStatement);
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
        PreparedStatement statement = psc.getPreparedStatementBy(id, sqlStatement);
        return getUser(statement);
    }

    public User getByLoginAndPassword(String login, String password) {

        String sqlStatement = userStatement.selectUserByLoginAndPassword();
        PreparedStatement statement = psc.getPreparedStatementBy(login, password, sqlStatement);
        return getUser(statement);
    }

    public User getByLoginAndRole(String login, String role) {

        String sqlStatement = userStatement.selectUserByLoginAndRole();
        PreparedStatement statement = psc.getPreparedStatementBy(login, role, sqlStatement);
        return getUser(statement);
    }

    public User getByLogin(String login) {

        String sqlStatement = userStatement.selectUserByLogin();
        PreparedStatement statement = psc.getPreparedStatementBy(login, sqlStatement);
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
        PreparedStatement statement = psc.getPreparedStatementBy(user.getId(), sqlStatement);
        return update(statement);
    }
}
