package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.data.contracts.TaskEntry;
import com.example.queststore.data.statements.TaskStatement;
import com.example.queststore.models.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbTaskDAO extends DbHelper implements TaskDAO {

    private TaskStatement taskStatement = new TaskStatement();

    @Override
    public List<Task> getAll() {
        String sqlStatement = taskStatement.selectAllTasks();

        List<Task> tasks = new ArrayList<>();
        try {
            PreparedStatement statement = getPreparedStatement(sqlStatement);
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                tasks.add(new Task(
                        resultSet.getInt(TaskEntry.ID),
                        resultSet.getString(TaskEntry.NAME),
                        resultSet.getInt(TaskEntry.POINTS),
                        resultSet.getString(TaskEntry.DESCRIPTION),
                        resultSet.getString(TaskEntry.CATEGORY)));
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return tasks;
    }

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

    @Override
    public boolean add(Task task) {
        String sqlStatement = taskStatement.insertTaskStatement();
        PreparedStatement statement = null;
        try {
            statement = getPreparedStatement(sqlStatement);
            statement.setString(1, task.getName());
            statement.setInt(2, task.getPoints());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getCategory());
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return update(statement);
    }

    @Override
    public boolean update(Task task) {
        String sqlStatement = taskStatement.updateTaskStatement();
        PreparedStatement statement = null;
        try {
            statement = getPreparedStatement(sqlStatement);
            statement.setInt(1, task.getPoints());
            statement.setString(2, task.getDescription());
            statement.setString(3, task.getCategory());
            statement.setInt(4, task.getID());
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return update(statement);
    }
}
