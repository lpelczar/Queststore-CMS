package com.example.queststore.data.statements;


import com.example.queststore.data.contracts.ExperienceLevelEntry;
import com.example.queststore.models.ExpLevel;

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
