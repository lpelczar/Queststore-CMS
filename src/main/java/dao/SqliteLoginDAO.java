package dao;

import data.DbHelper;
import data.PreparedStatementCreator;
import data.statements.UserStatement;
import model.database.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;

public class SqliteLoginDAO extends DbHelper implements LoginDAO {

    private PreparedStatementCreator psc = new PreparedStatementCreator();
    private UserStatement userStatement = new UserStatement();

    @Override
    public User getById(int id) {
        String statement = userStatement.selectById();
        PreparedStatement preparedStatement = psc.getPreparedStatementBy(Collections.singletonList(id), statement);
        return getUser(preparedStatement);
    }

    @Override
    public User getByLoginAndPassword(String login, String password) {
        String statement = userStatement.selectByLoginAndPassword();
        PreparedStatement preparedStatement = psc.getPreparedStatementBy(Arrays.asList(login, password), statement);
        return getUser(preparedStatement);
    }

    private User getUser(PreparedStatement statement) {
        User user = null;
        try {
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                user = new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getInt("role"));
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return user;
    }
}
