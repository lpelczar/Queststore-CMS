package com.example.queststore.data.statements;


import com.example.queststore.data.contracts.GroupEntry;

public class GroupStatement {

    public String selectAllGroups() {
        return "SELECT * FROM " + GroupEntry.TABLE_NAME + " WHERE group_name != 'No group';" ;
    }

    public String selectGroupByName() {
        return "SELECT * FROM " + GroupEntry.TABLE_NAME +
                " WHERE " + GroupEntry.GROUP_NAME + " = ?;" ;
    }

    public String insertGroupStatement() {

        return "INSERT INTO " + GroupEntry.TABLE_NAME + " (" +
                GroupEntry.GROUP_NAME + ") VALUES (?);" ;
    }

    public String deleteGroupStatement() {
        return "DELETE FROM " + GroupEntry.TABLE_NAME +
                " WHERE " + GroupEntry.ID + " = ?;" ;
    }
}
