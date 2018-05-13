package com.example.queststore.dao.sqlite;

import com.example.queststore.dao.StudentItemDAO;
import com.example.queststore.data.DbHelper;
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

    @Override
    public boolean add(int studentId, int itemId, int isUsed) {
        String sqlStatement = studentItemStatement.addStudentItemConnection();
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(studentId, itemId, isUsed), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean markItemAsUsed(int studentId, int itemId) {
        String sqlStatement = studentItemStatement.markItemAsUsed();
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(studentId, itemId), sqlStatement);
        return update(statement);
    }

    @Override
    public List<Integer> getStudentItemsIdsBy(int studentID) {
        String sqlStatement = studentItemStatement.getStudentsItems();
        List<Integer> studentsItems = new ArrayList<>();

        try {
            PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(studentID), sqlStatement);
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
        PreparedStatement statement = getPreparedStatementBy(Collections.emptyList(), sqlStatement);
        return update(statement);
    }
}
