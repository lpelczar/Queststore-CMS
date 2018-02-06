package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.data.PreparedStatementCreator;
import com.example.queststore.data.statements.StudentTaskStatement;

import java.sql.PreparedStatement;

public class DbStudentTaskDAO extends DbHelper implements StudentTaskDAO {

    private StudentTaskStatement studentTaskStatement = new StudentTaskStatement();
    private PreparedStatementCreator psc = new PreparedStatementCreator();

    @Override
    public boolean add(int studentID, int taskID) {
        String sqlStatement = studentTaskStatement.insertConnectionStatement();
        PreparedStatement statement = psc.getPreparedStatementBy(studentID, taskID, sqlStatement);
        return update(statement);
    }
}
