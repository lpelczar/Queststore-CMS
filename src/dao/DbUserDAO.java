package dao;

import data.statements.UserStatement;
import models.User;
import data.DbHelper;
import data.contracts.UserContract.UserEntry;

import java.sql.*;
import java.util.*;

public class DbUserDAO extends DbHelper implements UserDAO {

    private UserStatement userStatement = new UserStatement();

    @Override
    public List<User> getAll() {

        String statement = userStatement.selectAllUsers();

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
