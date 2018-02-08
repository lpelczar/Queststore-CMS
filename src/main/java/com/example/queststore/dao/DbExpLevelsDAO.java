package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.data.PreparedStatementCreator;
import com.example.queststore.data.contracts.ExperienceLevelEntry;
import com.example.queststore.data.statements.ExperienceLevelStatement;
import com.example.queststore.models.ExpLevel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DbExpLevelsDAO extends DbHelper implements ExpLevelsDAO {

    private ExperienceLevelStatement expStatement = new ExperienceLevelStatement();
    private PreparedStatementCreator psc = new PreparedStatementCreator();

    @Override
    public List<ExpLevel> getAll() {
        String sqlStatement = expStatement.selectAllExpLevels();

        List<ExpLevel> levels = new ArrayList<>();
        try {
            PreparedStatement statement = getPreparedStatement(sqlStatement);
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                levels.add(new ExpLevel(
                        resultSet.getString(ExperienceLevelEntry.NAME),
                        resultSet.getInt(ExperienceLevelEntry.LEVEL)));
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return levels;
    }

    @Override
    public ExpLevel getByName(String levelName) {
        String sqlStatement = expStatement.selectLevelByName();
        PreparedStatement statement = psc.getPreparedStatementBy(levelName, sqlStatement);
        ExpLevel level = null;
        try {
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                level = new ExpLevel(
                        resultSet.getString(ExperienceLevelEntry.NAME),
                        resultSet.getInt(ExperienceLevelEntry.LEVEL));
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return level;
    }

    @Override
    public boolean add(ExpLevel expLevel) {
        String sqlStatement = expStatement.insertLevelStatement();
        List<Object> params = Arrays.asList(expLevel.getName(), expLevel.getValue());
        PreparedStatement statement = psc.getPreparedStatementBy(params, sqlStatement);
        return update(statement);
    }

    @Override
    public boolean delete(String levelName) {
        String sqlStatement = expStatement.deleteLevelStatement();
        PreparedStatement statement = psc.getPreparedStatementBy(levelName, sqlStatement);
        return update(statement);
    }
}
