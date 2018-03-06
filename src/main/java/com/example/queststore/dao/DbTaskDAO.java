package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.data.PreparedStatementCreator;
import com.example.queststore.data.contracts.TaskEntry;
import com.example.queststore.data.statements.TaskStatement;
import com.example.queststore.models.Task;
import com.example.queststore.utils.QueryLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DbTaskDAO extends DbHelper implements TaskDAO {

    private TaskStatement taskStatement = new TaskStatement();
    private PreparedStatementCreator psc = new PreparedStatementCreator();

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
            QueryLogger.logInfo(e.getClass().getName() + ": " + e.getMessage(), "logs/errors.log");
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
            QueryLogger.logInfo(e.getClass().getName() + ": " + e.getMessage(), "logs/errors.log");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return task;
    }

    @Override
    public boolean add(Task task) {
        String sqlStatement = taskStatement.insertTaskStatement();
        PreparedStatement statement = psc.getPreparedStatementBy(Arrays.asList(task.getName(), task.getPoints(),
                task.getDescription(), task.getCategory()), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean update(Task task) {
        String sqlStatement = taskStatement.updateTaskStatement();
        PreparedStatement statement = psc.getPreparedStatementBy(Arrays.asList(task.getPoints(), task.getDescription(),
                task.getCategory(), task.getID()), sqlStatement);
        return update(statement);
    }
}
