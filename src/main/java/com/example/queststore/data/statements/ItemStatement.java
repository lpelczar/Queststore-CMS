package com.example.queststore.data.statements;


import com.example.queststore.data.contracts.ItemEntry;

public class ItemStatement {

    public String getAllItems() {
        return "SELECT * FROM " + ItemEntry.TABLE_NAME + "; ";
    }

    public String getItemsByStudentId() {
        // TODO 1: Join two tables and get items!
        return "";
    }

    public String getItemById() {
        return "SELECT * FROM " + ItemEntry.TABLE_NAME +
                " WHERE " + ItemEntry.ID + " = ?; ";
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
