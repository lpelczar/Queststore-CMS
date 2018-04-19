package dao;


import data.DbHelper;
import data.PreparedStatementCreator;
import data.statements.QuestStatement;
import model.database.Quest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqliteQuestDAO extends DbHelper implements QuestDAO {

    private PreparedStatementCreator psc = new PreparedStatementCreator();
    private QuestStatement questStatement = new QuestStatement();

    @Override
    public List<Quest> getAll() {
        String sqlStatement = questStatement.selectAllQuests();

        List<Quest> quests = new ArrayList<>();
        try {
            PreparedStatement statement = getPreparedStatement(sqlStatement);
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                quests.add(new Quest(
                        resultSet.getInt("quest_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("price")));
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return quests;
    }

    @Override
    public Quest getById(int id) {
        String sqlStatement = questStatement.selectQuestById();
        Quest quest = null;
        try {
            PreparedStatement statement = getPreparedStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                quest = new Quest(
                        resultSet.getInt("quest_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("price"));
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return quest;
    }

    @Override
    public boolean add(Quest quest) {
        String sqlStatement = questStatement.insertQuestStatement();
        PreparedStatement statement = psc.getPreparedStatementBy(Arrays.asList(quest.getQuestId(), quest.getName(),
                quest.getDescription(), quest.getPrice()), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean update(Quest quest) {
        String sqlStatement = questStatement.updateQuestStatement();
        PreparedStatement statement = psc.getPreparedStatementBy(Arrays.asList(quest.getQuestId(), quest.getName(),
                quest.getDescription(), quest.getPrice(), quest.getQuestId()), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean delete(Quest quest) {
        return false;
    }
}
