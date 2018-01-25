package data.statements;

import data.contracts.ItemContract.ItemEntry;
import models.Item;

public class ItemStatement {

    public static String addItem(Item item) {
        return "INSERT INTO " + ItemEntry.TABLE_NAME +
                "VALUES (" +
                item.getName() + ", " +
                item.getDescription()  + ", " +
                item.getPrice()  + ", " +
                item.getCategory()  + "); ";

    }
    public static String getAllItemsInStore() {
        return "SELECT * FROM " + ItemEntry.TABLE_NAME + "; ";
    }

    public static String findItemBy(int id) {
        return "SELECT * FROM " + ItemEntry.TABLE_NAME +
                " WHERE " + ItemEntry.ID + " = " + id + "; ";
    }
}
