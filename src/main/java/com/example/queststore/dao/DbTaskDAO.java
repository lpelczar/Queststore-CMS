package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.data.contracts.TaskEntry;
import com.example.queststore.data.statements.TaskStatement;
import com.example.queststore.models.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbTaskDAO extends DbHelper implements TaskDAO {

    private TaskStatement taskStatement = new TaskStatement();

    @Override
    public Task getByName(String name) {
        String sqlStatement = taskStatement.selectTaskByName();
        Task task = null;
        try {
            PreparedStatement statement = getPreparedStatement(sqlStatement);
            statement.setString(1, name);
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                task = new Task(
                        resultSet.getInt(TaskEntry.ID),
                        resultSet.getString(TaskEntry.NAME),
                        resultSet.getInt(TaskEntry.POINTS),
                        resultSet.getString(TaskEntry.DESCRIPTION),
                        resultSet.getString(TaskEntry.CATEGORY));
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return task;
    }
}
