package com.example.queststore.data.statements;


import com.example.queststore.data.contracts.ExperienceLevelEntry;

public class ExperienceLevelStatement {

    public String insertLevelStatement() {
        return "INSERT INTO " + ExperienceLevelEntry.TABLE_NAME + " (" +
                ExperienceLevelEntry.NAME + "," +
                ExperienceLevelEntry.LEVEL + ")" +
                " VALUES (?,?);" ;
    }

    public String selectAllExpLevels() {
        return "SELECT * FROM " + ExperienceLevelEntry.TABLE_NAME + ";" ;
    }
}
