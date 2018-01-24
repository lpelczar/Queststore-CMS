package data.statements;

import data.contracts.ExperienceLevelContract.ExperienceLevelEntry;

public class ExperienceLevelStatement {

    public String insertLevelStatement(String name, int value) {
        return "INSERT INTO " + ExperienceLevelEntry.TABLE_NAME + " (" +
                ExperienceLevelEntry.NAME + "," +
                ExperienceLevelEntry.LEVEL + ")" +
                " VALUES (" +
                "'" + name + "'," +
                "" + value + ");" ;
    }
}
