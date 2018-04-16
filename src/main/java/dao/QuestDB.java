package dao;

import model.QuestModel;

import java.sql.Connection;
import java.util.ArrayList;

public interface QuestDB {

    Integer getLastId();
    QuestModel getQuestById(int id);
    void saveNewQuestToDatabase(QuestModel quest);
    void updateEditedQuestInDatabase(QuestModel quest);
    QuestModel getAllQuests();

}
