package com.example.queststore.data.statements;


import com.example.queststore.data.contracts.GroupEntry;
import com.example.queststore.data.contracts.MentorGroupEntry;
import com.example.queststore.data.contracts.UserEntry;

public class MentorGroupStatement {

    public String insertConnectionStatement() {
        return "INSERT INTO " + MentorGroupEntry.TABLE_NAME + " (" +
                MentorGroupEntry.ID_GROUP + "," +
                MentorGroupEntry.ID_MENTOR + ")" +
                " VALUES (?,?);" ;
    }

    public String deleteConnectionStatement() {
        return "DELETE FROM " + MentorGroupEntry.TABLE_NAME +
                " WHERE " + MentorGroupEntry.ID_GROUP + " = ? AND "
                + MentorGroupEntry.ID_MENTOR + " = ?;" ;
    }

    public String createTable() {
        return "CREATE TABLE " + MentorGroupEntry.TABLE_NAME + " (" +
                MentorGroupEntry.ID_MENTOR + " INTEGER," +
                MentorGroupEntry.ID_GROUP + " INTEGER," +
                "PRIMARY KEY (" + MentorGroupEntry.ID_MENTOR + "," + MentorGroupEntry.ID_GROUP + ")," +
                "FOREIGN KEY (" + MentorGroupEntry.ID_MENTOR + ") REFERENCES " + UserEntry.TABLE_NAME +
                "(" + UserEntry.ID + ") ON DELETE CASCADE," +
                "FOREIGN KEY (" + MentorGroupEntry.ID_GROUP + ") REFERENCES " + GroupEntry.TABLE_NAME +
                "(" + GroupEntry.ID + ") ON DELETE CASCADE);" ;
    }

    public String deleteConnectionStatementByMentorID() {
        return "DELETE FROM " + MentorGroupEntry.TABLE_NAME + " ( " +
                " WHERE " + MentorGroupEntry.ID_MENTOR + " = ? ;";
    }
}
