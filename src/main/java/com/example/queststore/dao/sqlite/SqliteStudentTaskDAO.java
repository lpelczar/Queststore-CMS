package com.example.queststore.dao.sqlite;

import com.example.queststore.dao.StudentTaskDAO;
import com.example.queststore.data.DbHelper;
import com.example.queststore.data.statements.StudentTaskStatement;

import java.sql.PreparedStatement;
import java.util.Arrays;

public class SqliteStudentTaskDAO extends DbHelper implements StudentTaskDAO {

    private StudentTaskStatement studentTaskStatement = new StudentTaskStatement();

    @Override
    public boolean add(int studentID, int taskID) {
        String sqlStatement = studentTaskStatement.insertConnectionStatement();
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(studentID, taskID), sqlStatement);
        return update(statement);
    }
}
