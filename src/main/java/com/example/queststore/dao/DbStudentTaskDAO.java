package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.data.PreparedStatementCreator;
import com.example.queststore.data.statements.StudentTaskStatement;

import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;

public class DbStudentTaskDAO extends DbHelper implements StudentTaskDAO {

    private StudentTaskStatement studentTaskStatement = new StudentTaskStatement();
    private PreparedStatementCreator psc = new PreparedStatementCreator();

    @Override
    public boolean add(int studentID, int taskID) {
        String sqlStatement = studentTaskStatement.insertConnectionStatement();
        List<Object> params = Arrays.asList(studentID, taskID);
        PreparedStatement statement = psc.getPreparedStatementBy(params, sqlStatement);
        return update(statement);
    }
}
