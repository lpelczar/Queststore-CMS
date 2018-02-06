package com.example.queststore.data.statements;


import com.example.queststore.data.contracts.StudentItemEntry;

public class StudentItemStatement {

    public String addStudentItemConnection() {
        return "INSERT INTO " + StudentItemEntry.TABLE_NAME + " ( " +
                StudentItemEntry.ID_STUDENT + ", " +
                StudentItemEntry.ID_ITEM + " ) " +
                "VALUES (?,?); ";
    }

    public String getStudentsItems() {
        return "SELECT " + StudentItemEntry.ID_ITEM + " FROM " + StudentItemEntry.TABLE_NAME +
                " WHERE "  + StudentItemEntry.ID_STUDENT + " = ?; ";
    }
}
