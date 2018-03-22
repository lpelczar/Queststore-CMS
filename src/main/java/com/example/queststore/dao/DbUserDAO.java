package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.data.PreparedStatementCreator;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.data.statements.UserStatement;
import com.example.queststore.models.User;
import com.example.queststore.utils.QueryLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DbUserDAO extends DbHelper implements UserDAO {

    private UserStatement userStatement = new UserStatement();
    private PreparedStatementCreator psc = new PreparedStatementCreator();

    @Override
    public List<User> getAll() {

        String sqlStatement = userStatement.selectAllUsers();
        PreparedStatement statement = psc.getPreparedStatementBy(new ArrayList<>(), sqlStatement);
        return getUsers(statement);
    }

    @Override
    public List<User> getAllByRole(String role) {

        String sqlStatement = userStatement.selectAllUsersByRole();
        PreparedStatement statement = psc.getPreparedStatementBy(Collections.singletonList(role), sqlStatement);
        return getUsers(statement);
    }

    @Override
    public List<User> getStudentsByGroupId(int groupID) {
        String sqlStatement = userStatement.selectAllStudentsByGroupId();
        PreparedStatement statement = psc.getPreparedStatementBy(Collections.singletonList(groupID), sqlStatement);
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
            QueryLogger.logInfo(e.getClass().getName() + ": " + e.getMessage(), "logs/errors.log");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return users;
    }

    @Override
    public User getById(int id) {

        String sqlStatement = userStatement.selectUserById();
        PreparedStatement statement = psc.getPreparedStatementBy(Collections.singletonList(id), sqlStatement);
        return getUser(statement);
    }

    public User getByLoginAndPassword(String login, String password) {

        String sqlStatement = userStatement.selectUserByLoginAndPassword();
        PreparedStatement statement = psc.getPreparedStatementBy(Arrays.asList(login, password), sqlStatement);
        return getUser(statement);
    }

    public User getByLoginAndRole(String login, String role) {
        String sqlStatement = userStatement.selectUserByLoginAndRole();
        PreparedStatement statement = psc.getPreparedStatementBy(Arrays.asList(login, role), sqlStatement);
        return getUser(statement);
    }

    public User getByLogin(String login) {

        String sqlStatement = userStatement.selectUserByLogin();
        PreparedStatement statement = psc.getPreparedStatementBy(Collections.singletonList(login), sqlStatement);
        return getUser(statement);
    }

    public User getByEmail(String email) {

        String sqlStatement = userStatement.selectUserByEmail();
        PreparedStatement statement = psc.getPreparedStatementBy(Collections.singletonList(email), sqlStatement);
        return getUser(statement);
    }

    public User getByPhoneNumber(String phoneNumber) {

        String sqlStatement = userStatement.selectUserByPhoneNumber();
        PreparedStatement statement = psc.getPreparedStatementBy(Collections.singletonList(phoneNumber), sqlStatement);
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
            QueryLogger.logInfo(e.getClass().getName() + ": " + e.getMessage(), "logs/errors.log");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return user;
    }

    @Override
    public boolean add(User user) {
        String sqlStatement = userStatement.insertUserStatement();
        PreparedStatement statement = psc.getPreparedStatementBy(Arrays.asList(user.getName(), user.getLogin(),
                user.getEmail(), user.getPassword(), user.getPhoneNumber(), user.getRole()), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean update(User user) {
        String sqlStatement = userStatement.updateUserStatement();
        PreparedStatement statement = psc.getPreparedStatementBy(Arrays.asList(user.getName(), user.getLogin(),
                user.getEmail(), user.getPassword(), user.getPhoneNumber(), user.getRole(), user.getId()), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean delete(User user) {
        String sqlStatement = userStatement.deleteUserStatement();
        PreparedStatement statement = psc.getPreparedStatementBy(Collections.singletonList(user.getId()), sqlStatement);
        return update(statement);
    }
}
