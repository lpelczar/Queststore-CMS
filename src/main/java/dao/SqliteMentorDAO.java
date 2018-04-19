package dao;

import data.DbHelper;
import data.PreparedStatementCreator;
import model.Mentor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class SqliteMentorDAO extends DbHelper implements MentorDAO {
    private PreparedStatementCreator statementGenerator = new PreparedStatementCreator();

    @Override
    public void addMentor(Mentor mentor) {
        String sqlStatement = "INSERT INTO mentors (name, lastname, email) VALUES (?, ?, ?);";
        PreparedStatement statement = statementGenerator.getPreparedStatementBy(Arrays.asList(
                mentor.getName(),
                mentor.getLastName(),
                mentor.getEmail()),
                sqlStatement
        );
        update(statement);

    }

    @Override
    public Mentor getMentorBy(int mentorId) {
        String sqlStatement = "SELECT * FROM mentors WHERE mentor_id = ? ;";

        try {
            PreparedStatement statement = getPreparedStatement(sqlStatement);
            statement.setObject(1, mentorId);

            ResultSet resultSet = query(statement);

            while (resultSet.next()) {
                return new Mentor(
                        resultSet.getInt("mentor_id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getString("email")
                );
            }

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + " --> " + e.getMessage());
        }
        return null;
    }
}
