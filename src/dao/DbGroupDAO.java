package dao;

import data.DbHelper;
import data.statements.GroupStatement;
import models.Group;

public class DbGroupDAO extends DbHelper implements GroupDAO {

    private GroupStatement groupStatement = new GroupStatement();

    @Override
    public boolean add(Group group) {
        String statement = groupStatement.insertGroupStatement(group);
        return update(statement);
    }
}
