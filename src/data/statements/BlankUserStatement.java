package data.statements;


import data.contracts.BlankUserContract.BlankUserEntry;
import data.contracts.UserContract.UserEntry;

public class BlankUserStatement {

    public String selectAllBlankUsers() {
        return "SELECT * FROM " + UserEntry.TABLE_NAME +
                " WHERE " + UserEntry.ROLE + " = '" + BlankUserEntry.ROLE + "';" ;
    }
}
