package com.example.queststore.data.statements;


import com.example.queststore.data.contracts.GroupEntry;
import com.example.queststore.models.Group;

public class GroupStatement {

    public String insertGroupStatement(Group group) {

        return "INSERT INTO " + GroupEntry.TABLE_NAME + " (" +
                GroupEntry.GROUP_NAME + ")" +
                " VALUES (" +
                "'" + group.getGroupName() + "');" ;
    }

    public String selectAllGroups() {
        return "SELECT * FROM " + GroupEntry.TABLE_NAME + ";" ;
    }

    public String selectGroupByName(String name) {
        return "SELECT * FROM " + GroupEntry.TABLE_NAME +
                " WHERE " + GroupEntry.GROUP_NAME + " = '" + name + "';" ;
    }

    public String deleteGroupStatement(Group group) {
        return "DELETE FROM " + GroupEntry.TABLE_NAME +
                " WHERE " + GroupEntry.ID + " = " + group.getId() + ";" ;
    }
}
