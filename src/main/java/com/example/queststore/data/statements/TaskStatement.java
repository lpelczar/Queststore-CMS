package com.example.queststore.data.statements;

import com.example.queststore.data.contracts.TaskEntry;

public class TaskStatement {

    public String selectTaskByName() {
        return "SELECT * FROM " + TaskEntry.TABLE_NAME +
                " WHERE " + TaskEntry.NAME + " = ?;" ;
    }

    public String insertTaskStatement() {
        return "INSERT INTO " + TaskEntry.TABLE_NAME + " (" +
                TaskEntry.NAME + "," +
                TaskEntry.POINTS + "," +
                TaskEntry.DESCRIPTION + "," +
                TaskEntry.CATEGORY + ")" +
                " VALUES (?,?,?,?);" ;
    }

    public String updateTaskStatement() {
        return "UPDATE " + TaskEntry.TABLE_NAME + " SET " +
                TaskEntry.POINTS + " = ?," +
                TaskEntry.DESCRIPTION + " = ?," +
                TaskEntry.CATEGORY + " = ? WHERE " + TaskEntry.ID + " = ?;";
    }

    public String selectAllTasks() {
        return "SELECT * FROM " + TaskEntry.TABLE_NAME + ";" ;
    }
}