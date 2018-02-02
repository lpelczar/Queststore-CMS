package com.example.queststore.data.statements;


import com.example.queststore.data.contracts.GroupTableEntry;
import com.example.queststore.models.Group;

public class GroupStatement {

    public String insertGroupStatement(Group group) {

        return "INSERT INTO " + GroupTableEntry.TABLE_NAME + " (" +
                GroupTableEntry.GROUP_NAME + ")" +
                " VALUES (" +
                "'" + group.getGroupName() + "');" ;
    }

    public String selectAllGroups() {
        return "SELECT * FROM " + GroupTableEntry.TABLE_NAME + ";" ;
    }

    public String selectGroupByName(String name) {
        return "SELECT * FROM " + GroupTableEntry.TABLE_NAME +
                " WHERE " + GroupTableEntry.GROUP_NAME + " = '" + name + "';" ;
    }

    public String deleteGroupStatement(Group group) {
        return "DELETE FROM " + GroupTableEntry.TABLE_NAME +
                " WHERE " + GroupTableEntry.ID + " = " + group.getId() + ";" ;
    }
}
