package dao;

import data.DbHelper;
import data.contracts.GroupTableContract.GroupTableEntry;
import data.statements.GroupStatement;
import models.Entry;
import models.Group;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbGroupDAO extends DbHelper implements GroupDAO {

    private GroupStatement groupStatement = new GroupStatement();

    @Override
    public List<Entry> getAll() {
        String statement = groupStatement.selectAllGroups();

        List<Entry> groups = new ArrayList<>();
        try {
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                groups.add(new Group(
                        resultSet.getString(GroupTableEntry.GROUP_NAME)));
            resultSet.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return groups;
    }

    @Override
    public Group getByName(String name) {
        String statement = groupStatement.selectGroupByName(name);

        Group group = null;
        try {
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                group = new Group(
                        resultSet.getInt(GroupTableEntry.ID),
                        resultSet.getString(GroupTableEntry.GROUP_NAME));
            resultSet.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return group;
    }

    @Override
    public boolean add(Group group) {
        String statement = groupStatement.insertGroupStatement(group);
        return update(statement);
    }
}
