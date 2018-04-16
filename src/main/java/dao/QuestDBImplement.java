package dao;

import model.QuestModel;

import java.sql.*;
import java.util.ArrayList;

public class QuestDBImplement extends OpenCloseConnectionWithDB implements QuestDB {

    private QueriesGenerator generator;
    private String tableName;

    public QuestDBImplement() {
        this.tableName = "quests";
        this.generator = new QueriesGenerator();
    }

    public Integer getLastId() {
        String sql = "SELECT quest_id FROM quests;";
        Integer lastId = 0;

        try {
            openConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                lastId = rs.getInt("quest_id");
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return lastId;
    }

    @Override
    public QuestModel getQuestById(int id) {
        PreparedStatement statement = generator.getQuestById(id);
        QuestModel quest = null;
        try {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int quest_id = resultSet.getInt("quest_id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int price = resultSet.getInt("price");
                quest = new QuestModel(String.valueOf(quest_id), name, description, price);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return quest;
    }


    public QuestModel getAllQuests() {

        PreparedStatement statement = generator.getAllQuests(tableName);
        QuestModel quest = null;

        try {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("quest_id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int price = resultSet.getInt("price");
                quest = new QuestModel(String.valueOf(id), name, description, price);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return quest;
    }

    public void saveNewQuestToDatabase(QuestModel quest) {
        PreparedStatement statement = generator.insertItem(tableName,
                quest.getId(), quest.getName(), quest.getDescription(), quest.getPrice());

        try {
            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void updateEditedQuestInDatabase(QuestModel quest) {
        PreparedStatement statement = generator.updateItem(tableName, quest.getId(), quest.getName(),
                quest.getDescription(), quest.getPrice());

        try {
            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void deleteQuestByID (QuestModel quest){
        PreparedStatement statement = generator.deleteItem(tableName, quest.getId());

        try {
            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            }
        }


}
