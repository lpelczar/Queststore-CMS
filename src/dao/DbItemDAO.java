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

    public List<Item> getItemsBy(String sqlStatement) {
        List<Item> backpack = new ArrayList<>();
        Item item;

        try {
            ResultSet resultSet = query(sqlStatement);

            while (resultSet.next()) {
                item = new Item (
                        resultSet.getString(ItemEntry.ITEM_NAME),
                        resultSet.getInt(ItemEntry.PRICE),
                        resultSet.getString(ItemEntry.DESCRIPTION),
                        resultSet.getString(ItemEntry.CATEGORY)
                );
                backpack.add(item);
            }
            resultSet.close();

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return backpack;
    }

    public boolean addItem(Item item) {
        String statement = ItemStatement.addItem(item);
        return addItem(statement);
    }

    public boolean addItem(String sqlStatement) {
        try {
            update(sqlStatement);
            return true;
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }
}