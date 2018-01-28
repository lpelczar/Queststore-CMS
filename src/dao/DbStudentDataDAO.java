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
    public StudentData getStudentDataBy(int student_id) {
        String statement = StudentDataStatement.getStudentData(student_id);
        return getStudentDataBy(statement);
    }

    private StudentData getStudentDataBy(String sqlStatement) {
        StudentData student = null;

        try {
            ResultSet resultSet = query(sqlStatement);


            while (resultSet.next()) {
                student = new StudentData(
                        resultSet.getInt(StudentDataEntry.ID_USER),
                        resultSet.getInt(StudentDataEntry.ID_GROUP),
                        resultSet.getString(StudentDataEntry.TEAM_NAME),
                        resultSet.getString(StudentDataEntry.LEVEL),
                        resultSet.getInt(StudentDataEntry.BALANCE),
                        resultSet.getInt(StudentDataEntry.EXPERIENCE)
                );
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

    @Override
    public boolean updateStudentData(StudentData student) {
        String statement = StudentDataStatement.updateStudentData(student);
        return update(statement);
    }
}