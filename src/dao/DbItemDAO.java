package dao;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import data.statements.StudentItemStatement;
import data.contracts.ItemContract.ItemEntry;
import data.statements.ItemStatement;
import data.DbHelper;
import models.Item;


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
        return getItemFromStore(statement)
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
                )
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
}