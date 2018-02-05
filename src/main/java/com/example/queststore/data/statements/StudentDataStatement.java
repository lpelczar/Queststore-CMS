package com.example.queststore.data.statements;

import com.example.queststore.data.contracts.StudentDataEntry;

public class StudentDataStatement {

    public String getStudentDataById() {
        return "SELECT * FROM " + StudentDataEntry.TABLE_NAME +
                " WHERE " + StudentDataEntry.ID_USER + " = ?; ";
    }

    public String createStudentData() {
        return "INSERT INTO " + StudentDataEntry.TABLE_NAME + " ( " +
                StudentDataEntry.ID_USER + ", " +
                StudentDataEntry.ID_GROUP + ", " +
                StudentDataEntry.TEAM_NAME + ", " +
                StudentDataEntry.LEVEL + ", " +
                StudentDataEntry.BALANCE + ", " +
                StudentDataEntry.EXPERIENCE + ") VALUES (?,?,?,?,?,?); ";
    }

    public String updateStudentData() {
        return "UPDATE " + StudentDataEntry.TABLE_NAME + " SET " +
                StudentDataEntry.ID_GROUP + " = ?, " +
                StudentDataEntry.TEAM_NAME + " = ?, " +
                StudentDataEntry.LEVEL + " = ?, " +
                StudentDataEntry.BALANCE + " = ?, " +
                StudentDataEntry.EXPERIENCE + " = ? WHERE " + StudentDataEntry.ID_USER + " = ?; ";
    }

    public static String getAllStudents() {
        return "SELECT * FROM " + StudentDataEntry.TABLE_NAME + ";";
    }
}
