package data.sessiondatabase;

import data.DbHelper;
import data.PreparedStatementCreator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;

public class SqliteSessionDAO extends DbHelper implements SessionDAO {

    {
        setDatabasePath("sessions.db");
    }

    private PreparedStatementCreator psc = new PreparedStatementCreator();

    @Override
    public Session getById(String id) {

        String statement = "SELECT * FROM sessions WHERE session_id = ?;";
        PreparedStatement preparedStatement = psc.getPreparedStatementBy(Collections.singletonList(id), statement);

        Session session = null;
        try {
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
        String sqlStatement = "INSERT INTO sessions (session_id, user_id) VALUES (?,?); " ;
        PreparedStatement statement = psc.getPreparedStatementBy(
                Arrays.asList(session.getSessionId(), session.getUserId()), sqlStatement);
        return update(statement);
    }
}
