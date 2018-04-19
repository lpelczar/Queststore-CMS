package dao;

import model.database.Quest;

import java.util.List;

public interface QuestDAO {

    List<Quest> getAll();
    Quest getById(int id);
    boolean add(Quest quest);
    boolean update(Quest quest);
    boolean delete(Quest quest);
}
