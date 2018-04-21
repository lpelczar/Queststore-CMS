package queststore.dao;


import queststore.data.DbHelper;
import queststore.data.PreparedStatementCreator;
import queststore.data.statements.StudentTaskStatement;

import java.sql.PreparedStatement;
import java.util.Arrays;

public class DbStudentTaskDAO extends DbHelper implements StudentTaskDAO {

    private StudentTaskStatement studentTaskStatement = new StudentTaskStatement();
    private PreparedStatementCreator psc = new PreparedStatementCreator();

    @Override
    public boolean add(int studentID, int taskID) {
        String sqlStatement = studentTaskStatement.insertConnectionStatement();
        PreparedStatement statement = psc.getPreparedStatementBy(Arrays.asList(studentID, taskID), sqlStatement);
        return update(statement);
    }
}
