package com.example.queststore.data.statements;

import com.example.queststore.data.contracts.StudentTaskEntry;

public class StudentTaskStatement {


    public String insertConnectionStatement() {
        return "INSERT INTO " + StudentTaskEntry.TABLE_NAME + " (" +
                StudentTaskEntry.ID_STUDENT + "," +
                StudentTaskEntry.ID_TASK + ")" +
                " VALUES (?,?);" ;
    }
}
