package data.statements;

import data.contracts.ExperienceLevelContract.ExperienceLevelEntry;
import models.ExpLevel;

public class ExperienceLevelStatement {

    public String insertLevelStatement(ExpLevel expLevel) {
        return "INSERT INTO " + ExperienceLevelEntry.TABLE_NAME + " (" +
                ExperienceLevelEntry.NAME + "," +
                ExperienceLevelEntry.LEVEL + ")" +
                " VALUES (" +
                "'" + expLevel.getName() + "'," +
                "" + expLevel.getValue() + ");" ;
    }

    public String selectAllExpLevels() {
        return "SELECT * FROM " + ExperienceLevelEntry.TABLE_NAME + ";" ;
    }
}
