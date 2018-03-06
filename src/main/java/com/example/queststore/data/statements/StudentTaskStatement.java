package com.example.queststore.data.statements;

import com.example.queststore.data.contracts.StudentTaskEntry;
import com.example.queststore.data.contracts.TaskEntry;
import com.example.queststore.data.contracts.UserEntry;

public class StudentTaskStatement {


    public String insertConnectionStatement() {
        return "INSERT INTO " + StudentTaskEntry.TABLE_NAME + " (" +
                StudentTaskEntry.ID_STUDENT + "," +
                StudentTaskEntry.ID_TASK + ")" +
                " VALUES (?,?);" ;
    }

    public String createTable() {
        return "CREATE TABLE " + StudentTaskEntry.TABLE_NAME + " (" +
                StudentTaskEntry.ID_STUDENT + " INTEGER," +
                StudentTaskEntry.ID_TASK + " INTEGER," +
                "PRIMARY KEY (" + StudentTaskEntry.ID_STUDENT + ", " + StudentTaskEntry.ID_TASK + ")," +
                "FOREIGN KEY (" + StudentTaskEntry.ID_STUDENT + ") REFERENCES " + UserEntry.TABLE_NAME +
                "(" + UserEntry.ID + ") ON DELETE CASCADE," +
                "FOREIGN KEY (" + StudentTaskEntry.ID_TASK + ") REFERENCES " + TaskEntry.TABLE_NAME +
                "(" + TaskEntry.ID + ") ON DELETE CASCADE);" ;
    }
}
