package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.data.PreparedStatementCreator;
import com.example.queststore.data.contracts.ItemEntry;
import com.example.queststore.data.statements.ItemStatement;
import com.example.queststore.models.Item;
import com.example.queststore.utils.QueryLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class DbItemDAO extends DbHelper implements ItemDAO {

    private ItemStatement itemStatement = new ItemStatement();
    private PreparedStatementCreator psc = new PreparedStatementCreator();

    @Override
    public List<Item> getItemsByStudentId(int studentId) {
        String sqlStatement = itemStatement.getItemsByStudentId();
        List<Object> params = Collections.singletonList(studentId);
        PreparedStatement statement = psc.getPreparedStatementBy(params, sqlStatement);
        return getItemsBy(statement);
    }

    @Override
    public List<Item> getItemsByCategory(String category) {
        String sqlStatement = itemStatement.getItemsByCategory();
        List<Object> params = Collections.singletonList(category);
        PreparedStatement statement = psc.getPreparedStatementBy(params, sqlStatement);
        return getItemsBy(statement);
    }

    @Override
    public List<Item> getAllItems() {
        String sqlStatement = itemStatement.getAllItems();
        List<Object> params = Collections.emptyList();
        PreparedStatement statement = psc.getPreparedStatementBy(params, sqlStatement);
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
            QueryLogger.logInfo(e.getClass().getName() + ": " + e.getMessage(), "logs/errors.log");
        } finally {
            closeConnection();
        }
        return items;
    }

    @Override
    public Item getItemById(int id) {
        String sqlStatement = itemStatement.getItemById();
        List<Object> params = Collections.singletonList(id);
        PreparedStatement statement = psc.getPreparedStatementBy(params, sqlStatement);
        return getItemFromStore(statement);
    }

    @Override
    public Item getItemByName(String itemName) {
        String sqlStatement = itemStatement.getItemByName();
        List<Object> params = Collections.singletonList(itemName);
        PreparedStatement statement = psc.getPreparedStatementBy(params, sqlStatement);
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
            QueryLogger.logInfo(e.getClass().getName() + ": " + e.getMessage(), "logs/errors.log");
        } finally {
            closeConnection();
        }
        return item;
    }



    public boolean addItem(Item item) {
        String sqlStatement = itemStatement.addItemStatement();
        List<Object> params = Arrays.asList(item.getName(), item.getDescription(),
                item.getPrice(), item.getCategory());
        PreparedStatement statement = psc.getPreparedStatementBy(params, sqlStatement);
        return update(statement);
    }

    public boolean updateItem(Item item) {
        String sqlStatement = itemStatement.updateQueryStatement();
        List<Object> params = Arrays.asList(item.getName(), item.getDescription(),
                item.getPrice(), item.getCategory(), item.getID());
        PreparedStatement statement = psc.getPreparedStatementBy(params, sqlStatement);
        return update(statement);
    }
}