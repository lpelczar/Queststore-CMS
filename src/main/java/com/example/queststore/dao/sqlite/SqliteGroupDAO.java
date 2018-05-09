package com.example.queststore.dao.sqlite;

import com.example.queststore.dao.GroupDAO;
import com.example.queststore.data.DbHelper;
import com.example.queststore.data.contracts.GroupEntry;
import com.example.queststore.data.statements.GroupStatement;
import com.example.queststore.models.Group;
import com.example.queststore.utils.QueryLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SqliteGroupDAO extends DbHelper implements GroupDAO {

    private GroupStatement groupStatement = new GroupStatement();

    @Override
    public List<Group> getAll() {
        String sqlStatement = groupStatement.selectAllGroups();
        List<Group> groups = new ArrayList<>();
        try {
            PreparedStatement statement = getPreparedStatement(sqlStatement);
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                groups.add(new Group(
                        resultSet.getInt(GroupEntry.ID),
                        resultSet.getString(GroupEntry.GROUP_NAME)));
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            QueryLogger.logInfo(e.getClass().getName() + ": " + e.getMessage(), "logs/errors.log");
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
            PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(name), sqlStatement);
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                group = new Group(
                        resultSet.getInt(GroupEntry.ID),
                        resultSet.getString(GroupEntry.GROUP_NAME));
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            QueryLogger.logInfo(e.getClass().getName() + ": " + e.getMessage(), "logs/errors.log");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return group;
    }

    @Override
    public List<String> getGroupsNamesByMentorId(int mentorID) {

        String sqlStatement = groupStatement.selectGroupsNamesByMentorId();
        List<String> groupNames = new ArrayList<>();
        try {
            PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(mentorID), sqlStatement);
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                groupNames.add(
                        resultSet.getString(GroupEntry.GROUP_NAME));
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            QueryLogger.logInfo(e.getClass().getName() + ": " + e.getMessage(), "logs/errors.log");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return groupNames;
    }

    @Override
    public boolean add(Group group) {
        String sqlStatement = groupStatement.insertGroupStatement();
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(group.getGroupName()),
                sqlStatement);
        return update(statement);
    }

    @Override
    public boolean delete(Group group) {
        String sqlStatement = groupStatement.deleteGroupStatement();
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(group.getId()), sqlStatement);
        return update(statement);
    }
}