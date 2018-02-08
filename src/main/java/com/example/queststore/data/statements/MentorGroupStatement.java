package com.example.queststore.data.statements;


import com.example.queststore.data.contracts.MentorGroupEntry;

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
}
