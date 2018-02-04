package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.data.PreparedStatementCreator;
import com.example.queststore.data.statements.StudentItemStatement;

import java.sql.PreparedStatement;

public class DbStudentItemDAO extends DbHelper implements StudentItemDAO {

    private StudentItemStatement studentItemStatement = new StudentItemStatement();
    private PreparedStatementCreator psc = new PreparedStatementCreator();

    @Override
    public boolean add(int student_id, int item_id) {
        String sqlStatement = studentItemStatement.addStudentItemConnection();
        PreparedStatement statement = psc.getPreparedStatementBy(student_id, item_id, sqlStatement);
        return update(statement);
    }
}
