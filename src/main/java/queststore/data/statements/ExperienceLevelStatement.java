package queststore.data.statements;


import queststore.data.contracts.ExperienceLevelEntry;

public class ExperienceLevelStatement {

    public String insertLevelStatement() {
        return "INSERT INTO " + ExperienceLevelEntry.TABLE_NAME + " (" +
                ExperienceLevelEntry.NAME + "," +
                ExperienceLevelEntry.LEVEL + ")" +
                " VALUES (?,?);" ;
    }

    public String selectAllExpLevels() {
        return "SELECT * FROM " + ExperienceLevelEntry.TABLE_NAME + ";" ;
    }

    public String selectLevelByName() {
        return "SELECT * FROM " + ExperienceLevelEntry.TABLE_NAME +
                " WHERE " + ExperienceLevelEntry.NAME + " = ?;" ;
    }

    public String deleteLevelStatement() {
        return "DELETE FROM " + ExperienceLevelEntry.TABLE_NAME +
                " WHERE " + ExperienceLevelEntry.NAME + " = ?;" ;
    }

    public String createTable() {
        return "CREATE TABLE " + ExperienceLevelEntry.TABLE_NAME + " (" +
                ExperienceLevelEntry.NAME + " TEXT PRIMARY KEY UNIQUE," +
                ExperienceLevelEntry.LEVEL + " INTEGER NOT NULL);" ;
    }
}
