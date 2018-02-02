package main.java.com.example.queststore.dao;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import main.java.com.example.queststore.data.statements.StudentItemStatement;
import main.java.com.example.queststore.data.contracts.ItemContract.ItemEntry;
import main.java.com.example.queststore.data.statements.ItemStatement;
import main.java.com.example.queststore.data.DbHelper;
import main.java.com.example.queststore.models.Item;


public class DbItemDAO extends DbHelper implements ItemDAO {
    private StudentItemStatement studentItemStatement = new StudentItemStatement();


    @Override
    public List<Item> getItemsBy(int student_id) {
        String statement = studentItemStatement.getStudentItemsId(student_id);
        return getItemsBy(statement);
    }

    @Override
    public List<Item> getAllItemsInStore() {
        String statement = ItemStatement.getAllItemsInStore();
        return getItemsBy(statement);
    }

    @Override
    public Item getItemBy(int id) {
        String statement = ItemStatement.findItemBy(id);
        return getItemFromStore(statement);
    }

    public List<Item> getItemsBy(String sqlStatement) {
        List<Item> itemSet = new ArrayList<>();
        Item item;

        try {
            ResultSet resultSet = query(sqlStatement);

            while (resultSet.next()) {
                item = new Item (
                        resultSet.getInt(ItemEntry.ID),
                        resultSet.getString(ItemEntry.ITEM_NAME),
                        resultSet.getInt(ItemEntry.PRICE),
                        resultSet.getString(ItemEntry.DESCRIPTION),
                        resultSet.getString(ItemEntry.CATEGORY)
                );
                itemSet.add(item);
            }
            resultSet.close();

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return itemSet;
    }

    public Item getItemFromStore(String sqlStatement) {
        Item item = null;

        try {
            ResultSet resultSet = query(sqlStatement);

            while (resultSet.next()) {
                item = new Item (
                        resultSet.getInt(ItemEntry.ID),
                        resultSet.getString(ItemEntry.ITEM_NAME),
                        resultSet.getInt(ItemEntry.PRICE),
                        resultSet.getString(ItemEntry.DESCRIPTION),
                        resultSet.getString(ItemEntry.CATEGORY)
                );
            }
            resultSet.close();

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return item;
    }

    public boolean addItem(Item item) {
        String statement = ItemStatement.addItem(item);
        return update(statement);
    }

    public boolean updateItem(Item item) {
        String statement = ItemStatement.updateQuery(item);
        return update(statement);
    }
}