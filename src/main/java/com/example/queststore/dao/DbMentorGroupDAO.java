package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.data.statements.MentorGroupStatement;

public class DbMentorGroupDAO extends DbHelper implements MentorGroupDAO {

    private MentorGroupStatement mentorGroupStatement = new MentorGroupStatement();

    @Override
    public boolean add(int groupID, int mentorID) {

        String statement = mentorGroupStatement.insertConnectionStatement(groupID, mentorID);
        return update(statement);
    }

    @Override
    public boolean delete(int groupID, int mentorID) {
        String statement = mentorGroupStatement.deleteConnectionStatement(groupID, mentorID);
        return update(statement);
    }
}
