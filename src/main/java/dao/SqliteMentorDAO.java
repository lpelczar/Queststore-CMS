package dao;

import data.DbHelper;
import data.PreparedStatementCreator;
import model.Mentor;

import java.sql.PreparedStatement;
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
}
