package com.example.queststore.data.statements;

import com.example.queststore.data.contracts.TaskEntry;

public class TaskStatement {

    public String selectTaskByName() {
        return "SELECT * FROM " + TaskEntry.TABLE_NAME +
                " WHERE " + TaskEntry.NAME + " = ?;" ;
    }
}
