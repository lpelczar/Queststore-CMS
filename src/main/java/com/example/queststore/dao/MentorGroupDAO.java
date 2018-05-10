package com.example.queststore.dao;

public interface MentorGroupDAO {

    boolean add(int groupID, int mentorID);
    boolean delete(int groupID, int mentorID);
    boolean deleteBy(int mentorID);
    void setDatabasePath(String path);
}
