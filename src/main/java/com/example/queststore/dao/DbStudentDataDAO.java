package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.data.contracts.StudentDataEntry;
import com.example.queststore.data.statements.StudentDataStatement;
import com.example.queststore.models.Item;
import com.example.queststore.models.StudentData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbStudentDataDAO extends DbHelper implements StudentDataDAO {

    private StudentDataStatement studentDataStatement = new StudentDataStatement();

    @Override
    public StudentData getStudentDataBy(int student_id) {

        String sqlStatement = studentDataStatement.getStudentDataById();
        StudentData studentData = null;
        try {
            PreparedStatement statement = getPreparedStatement(sqlStatement);
            statement.setInt(1, student_id);
            ResultSet resultSet = query(statement);
            while (resultSet.next()) {
                studentData = new StudentData(
                        resultSet.getInt(StudentDataEntry.ID_USER),
                        resultSet.getInt(StudentDataEntry.ID_GROUP),
                        resultSet.getString(StudentDataEntry.TEAM_NAME),
                        resultSet.getString(StudentDataEntry.LEVEL),
                        resultSet.getInt(StudentDataEntry.BALANCE),
                        resultSet.getInt(StudentDataEntry.EXPERIENCE)
                );
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return studentData;
    }

    public boolean add(StudentData student) {
        String statement = StudentDataStatement.createStudentData(student);
        return update(statement);
    }

    public boolean add(int student_id, Item item) {
        String statement = StudentDataStatement.addItemToBackpack(student_id, item);
        return update(statement);
    }

    @Override
    public boolean updateStudentData(StudentData student) {
        String statement = StudentDataStatement.updateStudentData(student);
        return update(statement);
    }
}