package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.data.statements.MentorGroupStatement;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbMentorGroupDAO extends DbHelper implements MentorGroupDAO {

    private MentorGroupStatement mentorGroupStatement = new MentorGroupStatement();

    @Override
    public boolean add(int groupID, int mentorID) {

        String sqlStatement = mentorGroupStatement.insertConnectionStatement();
        PreparedStatement statement = null;
        try {
            statement = getPreparedStatement(sqlStatement);
            statement.setInt(1, groupID);
            statement.setInt(2, mentorID);
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return update(statement);
    }

    @Override
    public boolean delete(int groupID, int mentorID) {
        String sqlStatement = mentorGroupStatement.deleteConnectionStatement();
        PreparedStatement statement = null;
        try {
            statement = getPreparedStatement(sqlStatement);
            statement.setInt(1, groupID);
            statement.setInt(2, mentorID);
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return update(statement);
    }
}
