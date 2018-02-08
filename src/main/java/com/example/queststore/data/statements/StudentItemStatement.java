package com.example.queststore.data.statements;


import com.example.queststore.data.contracts.ItemEntry;
import com.example.queststore.data.contracts.StudentItemEntry;

public class StudentItemStatement {

    public String addStudentItemConnection() {
        return "INSERT INTO " + StudentItemEntry.TABLE_NAME + " ( " +
                StudentItemEntry.ID_STUDENT + ", " +
                StudentItemEntry.ID_ITEM + ", " +
                StudentItemEntry.IS_USED + " ) " +
                "VALUES (?,?,?); ";
    }

    public String getStudentsItems() {
        return "SELECT " + StudentItemEntry.ID_ITEM + " FROM " + StudentItemEntry.TABLE_NAME +
                " WHERE "  + StudentItemEntry.ID_STUDENT + " = ?; ";
    }

    public String markItemAsUsed() {
        return "UPDATE " + StudentItemEntry.TABLE_NAME + " SET " +
                StudentItemEntry.IS_USED + " = " + StudentItemEntry.IS_USED_VALUE +
                " WHERE " + StudentItemEntry.ID_STUDENT + " = ? AND " +
                StudentItemEntry.ID_ITEM + " = ?; ";
    }

    public String deleteTeamItemsStatement() {
        return " WHERE " + StudentItemEntry.TABLE_NAME + "." + StudentItemEntry.ID_ITEM + " IN (" +
               " SELECT " + ItemEntry.ID +
               " FROM " + ItemEntry.TABLE_NAME +
               " JOIN " + StudentItemEntry.TABLE_NAME + " ON " +
               ItemEntry.TABLE_NAME + "." + ItemEntry.ID + " = " + StudentItemEntry.TABLE_NAME + "." + StudentItemEntry.ID_ITEM +
               " WHERE " + ItemEntry.TABLE_NAME + "." + ItemEntry.CATEGORY + " = \'" + ItemEntry.EXTRA_ITEM + "\'); ";
    }
}
