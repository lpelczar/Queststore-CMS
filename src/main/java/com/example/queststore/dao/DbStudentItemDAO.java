package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.data.statements.StudentItemStatement;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbStudentItemDAO extends DbHelper implements StudentItemDAO {

    private StudentItemStatement studentItemStatement = new StudentItemStatement();

    @Override
    public boolean add(int student_id, int item_id) {
        String sqlStatement = studentItemStatement.addStudentItemConnection();
        PreparedStatement statement = null;
        try {
            statement = getPreparedStatement(sqlStatement);
            statement.setInt(1, student_id);
            statement.setInt(2, item_id);
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return update(statement);
    }
}
