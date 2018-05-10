package com.example.queststore.dao.sqlite;

import com.example.queststore.dao.TaskDAO;
import com.example.queststore.data.DbHelper;
import com.example.queststore.data.contracts.TaskEntry;
import com.example.queststore.data.statements.TaskStatement;
import com.example.queststore.models.Task;
import com.example.queststore.utils.QueryLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SqliteTaskDAO extends DbHelper implements TaskDAO {

    private TaskStatement taskStatement = new TaskStatement();

    @Override
    public List<Task> getTasksByStudentId(int id) {
        String sqlStatement = taskStatement.selectTasksByStudentId();
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(id), sqlStatement);
        return getTasks(statement);
    }

    @Override
    public List<Task> getAll() {
        String sqlStatement = taskStatement.selectAllTasks();
        PreparedStatement statement = getPreparedStatementBy(Collections.emptyList(), sqlStatement);
        return getTasks(statement);
    }

    private List<Task> getTasks(PreparedStatement statement) {
        List<Task> tasks = new ArrayList<>();
        try {
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
    public Task getById(int id) {
        String sqlStatement = taskStatement.selectTaskById();
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(id), sqlStatement);
        return getTask(statement);
    }

    @Override
    public Task getByName(String name) {
        String sqlStatement = taskStatement.selectTaskByName();
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(name), sqlStatement);
        return getTask(statement);
    }

    private Task getTask(PreparedStatement statement) {
        Task task = null;
        try {
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
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(task.getName(), task.getPoints(),
                task.getDescription(), task.getCategory()), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean update(Task task) {
        String sqlStatement = taskStatement.updateTaskStatement();
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(task.getId(), task.getName(),
                task.getPoints(), task.getDescription(), task.getCategory(), task.getId()), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean delete(Task task) {
        String sqlStatement = taskStatement.deleteTaskStatement();
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(task.getId()), sqlStatement);
        return update(statement);
    }
}
