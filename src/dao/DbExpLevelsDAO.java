package dao;

import data.DbHelper;
import data.contracts.ExperienceLevelContract.ExperienceLevelEntry;
import data.contracts.UserContract;
import data.statements.ExperienceLevelStatement;
import models.ExpLevel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbExpLevelsDAO extends DbHelper implements ExpLevelsDAO {

    private ExperienceLevelStatement expStatement = new ExperienceLevelStatement();

    @Override
    public List<ExpLevel> getAll() {
        String statement = expStatement.selectAllExpLevels();

        List<ExpLevel> levels = new ArrayList<>();
        try {
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                levels.add(new ExpLevel(
                        resultSet.getString(ExperienceLevelEntry.NAME),
                        resultSet.getInt(ExperienceLevelEntry.LEVEL)));
            resultSet.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return levels;
    }

    @Override
    public boolean add(ExpLevel expLevel) {
        String statement = expStatement.insertLevelStatement(expLevel);
        return update(statement);
    }
}
