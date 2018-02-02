package main.java.com.example.queststore.data.statements;

import data.contracts.ItemContract.ItemEntry;
import main.java.com.example.queststore.models.Item;

public class ItemStatement {

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
    public static String getAllItemsInStore() {
        return "SELECT * FROM " + ItemEntry.TABLE_NAME + "; ";
    }

    public static String findItemBy(int id) {
        return "SELECT * FROM " + ItemEntry.TABLE_NAME +
                " WHERE " + ItemEntry.ID + " = " + id + "; ";
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
