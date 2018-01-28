package dao;

import java.sql.*;

import models.Item;
import models.StudentData;
import models.User;
import data.DbHelper;
import data.statements.StudentDataStatement;
import data.contracts.StudentDataContract.StudentDataEntry;

public class DbStudentDataDAO extends DbHelper implements StudentDataDAO {

    @Override
    public StudentData getStudentLevelBy(int student_id) {
        String statement = StudentDataStatement.getLevelQuery(student_id);
        return getStudentLevelBy(statement);
    }

    private StudentData getStudentLevelBy(String sqlStatement) {
        StudentData student = new StudentData();

        try {
            ResultSet resultSet = query(sqlStatement);


            while (resultSet.next()) {

                String level = resultSet.getString(StudentDataEntry.LEVEL);
                student.setLevel(level);
            }
            resultSet.close();

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return student;
    }

    public boolean add(StudentData student, User user) {
        String statement = StudentDataStatement.createStudentData(student, user);
        return update(statement);
    }

    public boolean add(int student_id, Item item) {
        String statement = StudentDataStatement.addItemToBackpack(student_id, item);
        return update(statement);
    }
}