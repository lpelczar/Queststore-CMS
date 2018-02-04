package com.example.queststore.data.statements;


import com.example.queststore.data.contracts.ItemEntry;
import com.example.queststore.models.Item;

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

    public static String addItem(Item item) {
        return "INSERT INTO " + ItemEntry.TABLE_NAME + " (" +
                ItemEntry.ITEM_NAME + ", " +
                ItemEntry.DESCRIPTION + ", " +
                ItemEntry.PRICE + ", " +
                ItemEntry.CATEGORY + ") " +
                " VALUES ( \'" +
                item.getName() + "\', \'" +
                item.getDescription()  + "\', " +
                item.getPrice()  + ", \'" +
                item.getCategory()  + "\'); ";

    }

    public static String updateQuery(Item item) {
        return "UPDATE " + ItemEntry.TABLE_NAME + " SET " +
                ItemEntry.ITEM_NAME + " = \'" + item.getName() + "\', " +
                ItemEntry.PRICE + " = \'" + item.getPrice() + "\', " +
                ItemEntry.CATEGORY + " = \'" + item.getCategory() + "\', " +
                ItemEntry.DESCRIPTION + " = \'" + item.getDescription() + "\' " +
                "WHERE " + ItemEntry.ID + " = " + item.getID() + "; ";
    }
}
