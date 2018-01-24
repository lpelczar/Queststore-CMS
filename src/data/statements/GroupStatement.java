package data.statements;

import data.contracts.GroupTableContract.GroupTableEntry;
import models.Group;

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
}
