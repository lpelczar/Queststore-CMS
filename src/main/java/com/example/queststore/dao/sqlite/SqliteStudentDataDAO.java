package com.example.queststore.dao.sqlite;

import com.example.queststore.data.DbHelper;
import com.example.queststore.data.PreparedStatementCreator;
import com.example.queststore.data.contracts.StudentDataEntry;
import com.example.queststore.data.statements.StudentDataStatement;
import com.example.queststore.models.StudentData;
import com.example.queststore.utils.QueryLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqliteStudentDataDAO extends DbHelper implements StudentDataDAO {

    private StudentDataStatement studentDataStatement = new StudentDataStatement();
    private PreparedStatementCreator psc = new PreparedStatementCreator();

    @Override
    public StudentData getStudentDataByStudentId(int student_id) {

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
            QueryLogger.logInfo(e.getClass().getName() + ": " + e.getMessage(), "logs/errors.log");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return studentData;
    }

    @Override
    public List<StudentData> getAllStudentsData() {
        String sqlStatement = studentDataStatement.getAllStudents();
        List<StudentData> students = new ArrayList<>();
        try {
            PreparedStatement statement = getPreparedStatement(sqlStatement);
            ResultSet resultSet = query(statement);
            while (resultSet.next()) {
                students.add(new StudentData(
                        resultSet.getInt(StudentDataEntry.ID_USER),
                        resultSet.getInt(StudentDataEntry.ID_GROUP),
                        resultSet.getString(StudentDataEntry.TEAM_NAME),
                        resultSet.getString(StudentDataEntry.LEVEL),
                        resultSet.getInt(StudentDataEntry.BALANCE),
                        resultSet.getInt(StudentDataEntry.EXPERIENCE)));
            }
            resultSet.close();
        } catch (SQLException e) {
            QueryLogger.logInfo(e.getClass().getName() + ": " + e.getMessage(), "logs/errors.log");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return students;
    }

    @Override
    public List<StudentData> getStudentsDataByTeamName(String teamName) {
        String sqlStatement = studentDataStatement.getStudentsInSameTeam();
        List<StudentData> studentsTeam = new ArrayList<>();
        try {
            PreparedStatement statement = getPreparedStatement(sqlStatement);
            statement.setString(1, teamName);
            ResultSet resultSet = query(statement);
            while (resultSet.next()) {
                studentsTeam.add(new StudentData(
                        resultSet.getInt(StudentDataEntry.ID_USER),
                        resultSet.getInt(StudentDataEntry.ID_GROUP),
                        resultSet.getString(StudentDataEntry.TEAM_NAME),
                        resultSet.getString(StudentDataEntry.LEVEL),
                        resultSet.getInt(StudentDataEntry.BALANCE),
                        resultSet.getInt(StudentDataEntry.EXPERIENCE)));
            }
        } catch (SQLException e) {
            QueryLogger.logInfo(e.getClass().getName() + ": " + e.getMessage(), "logs/errors.log");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return studentsTeam;
    }

    public boolean add(StudentData student) {
        String sqlStatement = studentDataStatement.createStudentData();
        PreparedStatement statement = psc.getPreparedStatementBy(Arrays.asList(student.getId(), student.getGroupId(),
                student.getTeamName(),student.getLevel(), student.getBalance(), student.getExperience()), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean updateStudentData(StudentData student) {
        if (student != null) {
            String sqlStatement = studentDataStatement.updateStudentData();
            PreparedStatement statement = psc.getPreparedStatementBy(Arrays.asList(student.getGroupId(), student.getTeamName(),
                    student.getLevel(), student.getBalance(), student.getExperience(), student.getId()), sqlStatement);
            return update(statement);

        } else { return false; }
    }
}