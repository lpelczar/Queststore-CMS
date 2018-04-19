package data.sessiondatabase;

import data.DbHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqliteSessionDAO extends DbHelper implements SessionDAO {

    {
        setDatabasePath("sessions.db");
    }

    @Override
    public Session getById(String id) {

        String statement = "SELECT * FROM sessions_table WHERE session_id = ?;";
        Session session = null;

        try {
            PreparedStatement preparedStatement = getPreparedStatement(statement);
            preparedStatement.setString(1, id);
            ResultSet resultSet = query(preparedStatement);
            while (resultSet.next())
                session = new Session(
                        resultSet.getString("session_id"),
                        resultSet.getInt("user_id"));
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return session;

    }

    @Override
    public boolean add(Session session) {
        String statement = "INSERT INTO sessions_table (session_id, user_id) VALUES (?,?); " ;
        boolean isAdded = false;
        try {
            PreparedStatement preparedStatement = getPreparedStatement(statement);
            preparedStatement.setString(1, session.getSessionId());
            preparedStatement.setInt(2, session.getUserId());
            if (update(preparedStatement)) {
                isAdded = true;
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return isAdded;
    }

    @Override
    public boolean deleteBySessionId(String id) {
        String statement = "DELETE FROM sessions_table WHERE session_id = ?; " ;
        boolean isDeleted = false;
        try {
            PreparedStatement preparedStatement = getPreparedStatement(statement);
            preparedStatement.setString(1, id);
            if (update(preparedStatement)) {
                isDeleted = true;
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return isDeleted;
    }
}
