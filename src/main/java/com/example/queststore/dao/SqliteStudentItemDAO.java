package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.data.PreparedStatementCreator;
import com.example.queststore.data.contracts.StudentItemEntry;
import com.example.queststore.data.statements.StudentItemStatement;
import com.example.queststore.utils.QueryLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SqliteStudentItemDAO extends DbHelper implements StudentItemDAO {

    private StudentItemStatement studentItemStatement = new StudentItemStatement();
    private PreparedStatementCreator psc = new PreparedStatementCreator();

    @Override
    public boolean add(int studentId, int itemId, int isUsed) {
        String sqlStatement = studentItemStatement.addStudentItemConnection();
        PreparedStatement statement = psc.getPreparedStatementBy(Arrays.asList(studentId, itemId, isUsed), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean markItemAsUsed(int studentId, int itemId) {
        String sqlStatement = studentItemStatement.markItemAsUsed();
        PreparedStatement statement = psc.getPreparedStatementBy(Arrays.asList(studentId, itemId), sqlStatement);
        return update(statement);
    }

    @Override
    public List<Integer> getStudentItemsIdsBy(int studentID) {
        String sqlStatement = studentItemStatement.getStudentsItems();
        List<Integer> studentsItems = new ArrayList<>();

        try {
            PreparedStatement statement = psc.getPreparedStatementBy(Collections.singletonList(studentID), sqlStatement);
            ResultSet resultSet = query(statement);

            while (resultSet.next()) {
                studentsItems.add(resultSet.getInt(StudentItemEntry.ID_ITEM));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            QueryLogger.logInfo(e.getClass().getName() + ": " + e.getMessage(), "logs/errors.log");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return studentsItems;
    }

    @Override
    public boolean removeTeamItems() {
        String sqlStatement = studentItemStatement.deleteTeamItemsStatement();
        PreparedStatement statement = psc.getPreparedStatementBy(Collections.emptyList(), sqlStatement);
        return update(statement);
    }
}
