package queststore.dao;


import queststore.data.DbHelper;
import queststore.data.PreparedStatementCreator;
import queststore.data.statements.MentorGroupStatement;

import java.sql.PreparedStatement;
import java.util.Arrays;

public class DbMentorGroupDAO extends DbHelper implements MentorGroupDAO {

    private MentorGroupStatement mentorGroupStatement = new MentorGroupStatement();
    private PreparedStatementCreator psc = new PreparedStatementCreator();

    @Override
    public boolean add(int groupID, int mentorID) {

        String sqlStatement = mentorGroupStatement.insertConnectionStatement();
        PreparedStatement statement = psc.getPreparedStatementBy(Arrays.asList(groupID, mentorID), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean delete(int groupID, int mentorID) {
        String sqlStatement = mentorGroupStatement.deleteConnectionStatement();
        PreparedStatement statement = psc.getPreparedStatementBy(Arrays.asList(groupID, mentorID), sqlStatement);
        return update(statement);
    }
}
