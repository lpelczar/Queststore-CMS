package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.data.contracts.ItemEntry;
import com.example.queststore.data.statements.ItemStatement;
import com.example.queststore.models.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DbItemDAO extends DbHelper implements ItemDAO {

    private ItemStatement itemStatement = new ItemStatement();

    @Override
    public List<Item> getItemsByStudentId(int student_id) {
        String sqlStatement = itemStatement.getItemsByStudentId();
        PreparedStatement statement = null;
        try {
            statement = getPreparedStatement(sqlStatement);
            statement.setInt(1, student_id);
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return getItemsBy(statement);
    }

    @Override
    public List<Item> getAllItems() {
        String sqlStatement = itemStatement.getAllItems();
        PreparedStatement statement = null;
        try {
            statement = getPreparedStatement(sqlStatement);
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return getItemsBy(statement);
    }

    private List<Item> getItemsBy(PreparedStatement statement) {

        List<Item> items = new ArrayList<>();
        try {
            ResultSet resultSet = query(statement);
            while (resultSet.next()) {
                items.add(new Item (
                        resultSet.getInt(ItemEntry.ID),
                        resultSet.getString(ItemEntry.ITEM_NAME),
                        resultSet.getInt(ItemEntry.PRICE),
                        resultSet.getString(ItemEntry.DESCRIPTION),
                        resultSet.getString(ItemEntry.CATEGORY)));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return items;
    }

    @Override
    public Item getItemById(int id) {
        String sqlStatement = itemStatement.getItemById();
        PreparedStatement statement = null;
        try {
            statement = getPreparedStatement(sqlStatement);
            statement.setInt(1, id);
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return getItemFromStore(statement);
    }

    private Item getItemFromStore(PreparedStatement statement) {

        Item item = null;
        try {
            ResultSet resultSet = query(statement);
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
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return item;
    }

    public boolean addItem(Item item) {
        String sqlStatement = itemStatement.addItemStatement();
        PreparedStatement statement = null;
        try {
            statement = getPreparedStatement(sqlStatement);
            statement.setString(1, item.getName());
            statement.setString(2, item.getDescription());
            statement.setInt(3, item.getPrice());
            statement.setString(4, item.getCategory());
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return update(statement);
    }

    public boolean updateItem(Item item) {
        String sqlStatement = itemStatement.updateQueryStatement();
        PreparedStatement statement = null;
        try {
            statement = getPreparedStatement(sqlStatement);
            statement.setString(1, item.getName());
            statement.setString(2, item.getDescription());
            statement.setInt(3, item.getPrice());
            statement.setString(4, item.getCategory());
            statement.setInt(5, item.getID());
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return update(statement);
    }
}