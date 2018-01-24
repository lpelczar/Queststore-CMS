package dao;

import data.DbHelper;
import data.statements.ExperienceLevelStatement;

public class DbExpLevelsDAO extends DbHelper implements ExpLevelsDAO {

    private ExperienceLevelStatement expStatement = new ExperienceLevelStatement();

    @Override
    public boolean add(String name, int value) {
        String statement = expStatement.insertLevelStatement(name, value);
        return update(statement);
    }
}
