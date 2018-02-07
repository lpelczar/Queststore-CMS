package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.data.PreparedStatementCreator;
import com.example.queststore.data.contracts.StudentItemEntry;
import com.example.queststore.data.statements.StudentItemStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbStudentItemDAO extends DbHelper implements StudentItemDAO {

    private StudentItemStatement studentItemStatement = new StudentItemStatement();
    private PreparedStatementCreator psc = new PreparedStatementCreator();

    @Override
    public boolean add(int studentId, int itemId, int isUsed) {
        String sqlStatement = studentItemStatement.addStudentItemConnection();
        PreparedStatement statement = psc.getPreparedStatementBy(studentId, itemId, isUsed, sqlStatement);
        return update(statement);
    }

    @Override
    public boolean markItemAsUsed(int studentId, int itemId) {
        String sqlStatement = studentItemStatement.markItemAsUsed();
        PreparedStatement statement = psc.getPreparedStatementBy(studentId, itemId, sqlStatement);
        return update(statement);
    }

    @Override
    public List<Integer> getStudentsItemsBy(int studentID) {
        String sqlStatement = studentItemStatement.getStudentsItems();
        List<Integer> studentsItems = new ArrayList<>();

        try {
            PreparedStatement statement = psc.getPreparedStatementBy(studentID, sqlStatement);
            ResultSet resultSet = query(statement);

            while (resultSet.next()) {
                studentsItems.add(resultSet.getInt(StudentItemEntry.ID_ITEM));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return studentsItems;
    }
}
