package dao;

import models.StudentData;
import data.DbHelper;
import data.statements.StudentDataStatement;
import data.contracts.StudentDataContract.StudentDataEntry;

public class DbStudentDataDAO extends DbHelper implements StudentDAO {

    @Override
    public StudentData getStudentLevelBy(int student_id) {
        String statement = StudentDataStatement.getLevelQuery(student_id);
        return getStudentLevelBy(statement);
    }

    public StudentData getStudentLevelBy(String sqlStatement) {
        StudentData student;

        try {
            ResultSet resultSet = query(sqlStatement);

            while (resultSet.next()) {
                student = new StudentData();

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
}