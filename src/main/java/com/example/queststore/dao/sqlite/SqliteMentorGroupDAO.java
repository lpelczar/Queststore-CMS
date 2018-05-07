package com.example.queststore.dao.sqlite;

import com.example.queststore.dao.MentorGroupDAO;
import com.example.queststore.data.DbHelper;
import com.example.queststore.data.statements.MentorGroupStatement;

import java.sql.PreparedStatement;
import java.util.Arrays;

public class SqliteMentorGroupDAO extends DbHelper implements MentorGroupDAO {

    private MentorGroupStatement mentorGroupStatement = new MentorGroupStatement();

    @Override
    public boolean add(int groupID, int mentorID) {

        String sqlStatement = mentorGroupStatement.insertConnectionStatement();
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(groupID, mentorID), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean delete(int groupID, int mentorID) {
        String sqlStatement = mentorGroupStatement.deleteConnectionStatement();
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(groupID, mentorID), sqlStatement);
        return update(statement);
    }
}
