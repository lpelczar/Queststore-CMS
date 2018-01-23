package dao;

import data.DbHelper;
import data.contracts.UserContract;
import data.statements.BlankUserStatement;
import models.BlankUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbBlankUserDAO extends DbHelper implements BlankUserDAO {

    private BlankUserStatement blankUserStatement = new BlankUserStatement();

    @Override
    public List<BlankUser> getAll() {
        String statement = blankUserStatement.selectAllBlankUsers();

        List<BlankUser> blankUsers = new ArrayList<>();
        try {
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                blankUsers.add(new BlankUser(
                        resultSet.getInt(UserContract.UserEntry.ID),
                        resultSet.getString(UserContract.UserEntry.NAME),
                        resultSet.getString(UserContract.UserEntry.LOGIN),
                        resultSet.getString(UserContract.UserEntry.EMAIL),
                        resultSet.getString(UserContract.UserEntry.PASSWORD),
                        resultSet.getString(UserContract.UserEntry.PHONE_NUMBER),
                        resultSet.getString(UserContract.UserEntry.ROLE)));
            resultSet.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return blankUsers;
    }
}
