package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.data.contracts.GroupEntry;
import com.example.queststore.data.statements.GroupStatement;
import com.example.queststore.models.Entry;
import com.example.queststore.models.Group;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbGroupDAO extends DbHelper implements GroupDAO {

    private GroupStatement groupStatement = new GroupStatement();

    @Override
    public List<Entry> getAll() {
        String sqlStatement = groupStatement.selectAllGroups();

        List<Entry> groups = new ArrayList<>();
        try {
            PreparedStatement statement = getPreparedStatement(sqlStatement);
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                groups.add(new Group(
                        resultSet.getString(GroupEntry.GROUP_NAME)));
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return groups;
    }

    @Override
    public Group getByName(String name) {
        String sqlStatement = groupStatement.selectGroupByName();
        Group group = null;
        try {
            PreparedStatement statement = getPreparedStatement(sqlStatement);
            statement.setString(1, name);
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                group = new Group(
                        resultSet.getInt(GroupEntry.ID),
                        resultSet.getString(GroupEntry.GROUP_NAME));
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return group;
    }

    @Override
    public boolean add(Group group) {
        String sqlStatement = groupStatement.insertGroupStatement();
        PreparedStatement statement = null;
        try {
            statement = getPreparedStatement(sqlStatement);
            statement.setString(1, group.getGroupName());
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return update(statement);
    }

    @Override
    public boolean delete(Group group) {
        String sqlStatement = groupStatement.deleteGroupStatement();
        PreparedStatement statement = null;
        try {
            statement = getPreparedStatement(sqlStatement);
            statement.setInt(1, group.getId());
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return update(statement);
    }
}
