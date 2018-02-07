package com.example.queststore.data.statements;


import com.example.queststore.data.contracts.ItemEntry;
import com.example.queststore.data.contracts.StudentItemEntry;

public class ItemStatement {

    public String getAllItems() {
        return "SELECT * FROM " + ItemEntry.TABLE_NAME + "; ";
    }

    public String getItemsByStudentId() {
        return "SELECT " +
                ItemEntry.ID + "," +
                ItemEntry.ITEM_NAME + "," +
                ItemEntry.DESCRIPTION + "," +
                ItemEntry.PRICE + "," +
                ItemEntry.CATEGORY
                + " FROM " + ItemEntry.TABLE_NAME +
                " JOIN " + StudentItemEntry.TABLE_NAME + " ON " +
                ItemEntry.TABLE_NAME + "." + ItemEntry.ID + " = " +
                StudentItemEntry.TABLE_NAME + "." + StudentItemEntry.ID_ITEM +
                " WHERE " + StudentItemEntry.TABLE_NAME + "." + StudentItemEntry.ID_STUDENT + " = ?;" ;
    }

    public String getItemById() {
        return "SELECT * FROM " + ItemEntry.TABLE_NAME +
                " WHERE " + ItemEntry.ID + " = ?; ";
    }

    public String getItemByName() {
        return "SELECT * FROM " + ItemEntry.TABLE_NAME +
                " WHERE " + ItemEntry.ITEM_NAME + " = ?; ";
    }

    public String getItemsByCategory() {
        return "SELECT * FROM " + ItemEntry.TABLE_NAME +
                " WHERE " + ItemEntry.CATEGORY + " = ?; ";
    }

    public String addItemStatement() {
        return "INSERT INTO " + ItemEntry.TABLE_NAME + " (" +
                ItemEntry.ITEM_NAME + ", " +
                ItemEntry.DESCRIPTION + ", " +
                ItemEntry.PRICE + ", " +
                ItemEntry.CATEGORY + ") VALUES (?,?,?,?); ";
    }

    public String updateQueryStatement() {
        return "UPDATE " + ItemEntry.TABLE_NAME + " SET " +
                ItemEntry.ITEM_NAME + " = ?, " +
                ItemEntry.PRICE + " = ?, " +
                ItemEntry.CATEGORY + " = ?, " +
                ItemEntry.DESCRIPTION + " = ? " +
                "WHERE " + ItemEntry.ID + " = ?; ";
    }
}
