package data.statements;

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
}
