package dao;

import data.DbHelper;
import data.statements.MentorGroupStatement;

public class DbMentorGroupDAO extends DbHelper implements MentorGroupDAO {

    private MentorGroupStatement mentorGroupStatement = new MentorGroupStatement();

    @Override
    public boolean add(int groupID, int mentorID) {

        String statement = mentorGroupStatement.insertConnectionStatement(groupID, mentorID);
        return update(statement);
    }
}
