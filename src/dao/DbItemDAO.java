package dao;

import java.util.List;
import java.util.ArrayList;
import data.statements.StudentItemStatement;
import data.contracts.ItemContract.ItemEntry;
import data.DbHelper;
import models.Item;


public class DbItemDAO extends DbHelper implements ItemDAO {
    private StudentItemStatement studentItemStatement = new StudentItemStatement();


    @Override
    public List<Item> getItemsBy(int student_id) {
        String statement = studentItemStatement.getStudentItemsId(student_id);
        return getItems(statement);
    }

    public List<Item> getItemsBy(String sqlStatement) {
        List<Item> backpack = new ArrayList<>();
        Item item;

        try {
            ResultSet resultSet = query(sqlStatement);

            while (resultSet.next()) {
                item = new Item (
                        resultSet.getString(ItemEntry.NAME),
                        resultSet.getInt(ItemEntry.PRICE),
                        resultSet.getString(ItemEntry.DESCRIPTION)
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
}