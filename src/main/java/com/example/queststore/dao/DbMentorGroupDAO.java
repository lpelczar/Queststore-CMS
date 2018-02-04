package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.data.PreparedStatementCreator;
import com.example.queststore.data.statements.MentorGroupStatement;

import java.sql.PreparedStatement;

public class DbMentorGroupDAO extends DbHelper implements MentorGroupDAO {

    private MentorGroupStatement mentorGroupStatement = new MentorGroupStatement();
    private PreparedStatementCreator psc = new PreparedStatementCreator();

    @Override
    public boolean add(int groupID, int mentorID) {

        String sqlStatement = mentorGroupStatement.insertConnectionStatement();
        PreparedStatement statement = psc.getPreparedStatementBy(groupID, mentorID, sqlStatement);
        return update(statement);
    }

    @Override
    public boolean delete(int groupID, int mentorID) {
        String sqlStatement = mentorGroupStatement.deleteConnectionStatement();
        PreparedStatement statement = psc.getPreparedStatementBy(groupID, mentorID, sqlStatement);
        return update(statement);
    }
}
