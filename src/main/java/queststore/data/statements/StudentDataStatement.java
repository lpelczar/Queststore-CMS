package queststore.data.statements;


import queststore.data.contracts.GroupEntry;
import queststore.data.contracts.StudentDataEntry;
import queststore.data.contracts.UserEntry;

public class StudentDataStatement {

    public String getStudentDataById() {
        return "SELECT * FROM " + StudentDataEntry.TABLE_NAME +
                " WHERE " + StudentDataEntry.ID_USER + " = ?; ";
    }

    public String createStudentData() {
        return "INSERT INTO " + StudentDataEntry.TABLE_NAME + " ( " +
                StudentDataEntry.ID_USER + ", " +
                StudentDataEntry.ID_GROUP + ", " +
                StudentDataEntry.TEAM_NAME + ", " +
                StudentDataEntry.LEVEL + ", " +
                StudentDataEntry.BALANCE + ", " +
                StudentDataEntry.EXPERIENCE + ") VALUES (?,?,?,?,?,?); ";
    }

    public String updateStudentData() {
        return "UPDATE " + StudentDataEntry.TABLE_NAME + " SET " +
                StudentDataEntry.ID_GROUP + " = ?, " +
                StudentDataEntry.TEAM_NAME + " = ?, " +
                StudentDataEntry.LEVEL + " = ?, " +
                StudentDataEntry.BALANCE + " = ?, " +
                StudentDataEntry.EXPERIENCE + " = ? WHERE " + StudentDataEntry.ID_USER + " = ?; ";
    }

    public String getStudentsInSameTeam() {
        return "SELECT * FROM " + StudentDataEntry.TABLE_NAME +
                " WHERE " + StudentDataEntry.TEAM_NAME + " = ?; ";
    }

    public String getAllStudents() {
        return "SELECT * FROM " + StudentDataEntry.TABLE_NAME + ";";
    }

    public String createTable() {
        return "CREATE TABLE " + StudentDataEntry.TABLE_NAME + " (" +
                StudentDataEntry.ID_USER + " INTEGER," +
                StudentDataEntry.ID_GROUP + " INTEGER DEFAULT 1," +
                StudentDataEntry.TEAM_NAME + " TEXT," +
                StudentDataEntry.LEVEL + " TEXT," +
                StudentDataEntry.BALANCE + " INTEGER," +
                StudentDataEntry.EXPERIENCE + " INTEGER," +
                "PRIMARY KEY (" + StudentDataEntry.ID_USER + ", " + StudentDataEntry.ID_GROUP + ")," +
                "FOREIGN KEY (" + StudentDataEntry.ID_USER + ") REFERENCES " + UserEntry.TABLE_NAME +
                "(" + UserEntry.ID + ") ON DELETE CASCADE," +
                "FOREIGN KEY (" + StudentDataEntry.ID_GROUP + ") REFERENCES " + GroupEntry.TABLE_NAME +
                "(" + GroupEntry.ID + ") ON DELETE SET DEFAULT );" ;
    }
}
