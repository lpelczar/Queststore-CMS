package main.java.com.example.queststore.data.statements;

import data.contracts.MentorGroupContract.MentorGroupEntry;

public class MentorGroupStatement {

    public String insertConnectionStatement(int groupID, int mentorID) {
        return "INSERT INTO " + MentorGroupEntry.TABLE_NAME + " (" +
                MentorGroupEntry.ID_GROUP + "," +
                MentorGroupEntry.ID_MENTOR + ")" +
                " VALUES (" +
                "" + groupID + "," +
                "" + mentorID + ");" ;
    }

    public String deleteConnectionStatement(int groupID, int mentorID) {
        return "DELETE FROM " + MentorGroupEntry.TABLE_NAME +
                " WHERE " + MentorGroupEntry.ID_GROUP + " = " + groupID + " AND "
                + MentorGroupEntry.ID_MENTOR + " = " + mentorID + ";" ;
    }
}
