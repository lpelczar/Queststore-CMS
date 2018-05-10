package com.example.queststore.data.statements;

import com.example.queststore.data.contracts.StudentTaskEntry;
import com.example.queststore.data.contracts.TaskEntry;

public class TaskStatement {

    public String selectTaskByName() {
        return "SELECT * FROM " + TaskEntry.TABLE_NAME +
                " WHERE " + TaskEntry.NAME + " = ?;" ;
    }

    public String selectTaskById() {
        return "SELECT * FROM " + TaskEntry.TABLE_NAME +
                " WHERE " + TaskEntry.ID + " = ?;" ;
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
                TaskEntry.ID + " = ?," +
                TaskEntry.NAME + " = ?," +
                TaskEntry.POINTS + " = ?," +
                TaskEntry.DESCRIPTION + " = ?," +
                TaskEntry.CATEGORY + " = ? WHERE " + TaskEntry.ID + " = ?;";
    }

    public String selectAllTasks() {
        return "SELECT * FROM " + TaskEntry.TABLE_NAME + ";" ;
    }

    public String selectTasksByStudentId() {
        return "SELECT " +
                TaskEntry.ID + "," +
                TaskEntry.NAME + "," +
                TaskEntry.POINTS + "," +
                TaskEntry.DESCRIPTION + "," +
                TaskEntry.CATEGORY
                + " FROM " + TaskEntry.TABLE_NAME +
                " JOIN " + StudentTaskEntry.TABLE_NAME + " ON " +
                TaskEntry.TABLE_NAME + "." + TaskEntry.ID + " = " +
                StudentTaskEntry.TABLE_NAME + "." + StudentTaskEntry.ID_TASK +
                " WHERE " + StudentTaskEntry.TABLE_NAME + "." + StudentTaskEntry.ID_STUDENT + " = ?;" ;
    }

    public String deleteTaskStatement() {
        return "DELETE FROM " + TaskEntry.TABLE_NAME +
                " WHERE " + TaskEntry.ID + " = ?;" ;
    }

    public String createTable() {
        return "CREATE TABLE " + TaskEntry.TABLE_NAME + " (" +
                TaskEntry.ID + " INTEGER PRIMARY KEY," +
                TaskEntry.NAME + " TEXT NOT NULL UNIQUE," +
                TaskEntry.POINTS + " INTEGER NOT NULL," +
                TaskEntry.DESCRIPTION + " TEXT," +
                TaskEntry.CATEGORY + " TEXT" +
                ");" ;
    }
}
