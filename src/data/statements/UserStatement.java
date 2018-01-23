package data.statements;


import data.contracts.UserContract.UserEntry;

public class UserStatement {

    public String selectAllUsers() {
        return "SELECT * FROM " + UserEntry.TABLE_NAME + ";" ;
    }
}
