package queststore.dao;


import queststore.data.DbHelper;
import queststore.data.PreparedStatementCreator;
import queststore.data.contracts.ItemEntry;
import queststore.data.statements.ItemStatement;
import queststore.models.Item;
import queststore.utils.QueryLogger;

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
        PreparedStatement statement = psc.getPreparedStatementBy(Collections.singletonList(studentId), sqlStatement);
        return getItemsBy(statement);
    }

    @Override
    public List<Item> getItemsByCategory(String category) {
        String sqlStatement = itemStatement.getItemsByCategory();
        PreparedStatement statement = psc.getPreparedStatementBy(Collections.singletonList(category), sqlStatement);
        return getItemsBy(statement);
    }

    @Override
    public List<Item> getAllItems() {
        String sqlStatement = itemStatement.getAllItems();
        PreparedStatement statement = psc.getPreparedStatementBy(Collections.emptyList(), sqlStatement);
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
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return items;
    }

    @Override
    public Item getItemById(int id) {
        String sqlStatement = itemStatement.getItemById();
        PreparedStatement statement = psc.getPreparedStatementBy(Collections.singletonList(id), sqlStatement);
        return getItemFromStore(statement);
    }

    @Override
    public Item getItemByName(String itemName) {
        String sqlStatement = itemStatement.getItemByName();
        PreparedStatement statement = psc.getPreparedStatementBy(Collections.singletonList(itemName), sqlStatement);
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
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return item;
    }



    public boolean addItem(Item item) {
        String sqlStatement = itemStatement.addItemStatement();
        PreparedStatement statement = psc.getPreparedStatementBy(Arrays.asList(item.getName(), item.getDescription(),
                item.getPrice(), item.getCategory()), sqlStatement);
        return update(statement);
    }

    public boolean updateItem(Item item) {
        String sqlStatement = itemStatement.updateQueryStatement();
        PreparedStatement statement = psc.getPreparedStatementBy(Arrays.asList(item.getName(), item.getDescription(),
                item.getPrice(), item.getCategory(), item.getID()), sqlStatement);
        return update(statement);
    }
}