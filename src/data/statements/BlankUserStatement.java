package data.statements;

import static data.contracts.UserContract.*;

public class BankUserDataStatement {

    public String selectAllBlankUsers() {
        return "SELECT * FROM " + UserEntry.TABLE_NAME +
                " WHERE " + UserEntry.ROLE + " = " + id + ";" ;
    }
}
