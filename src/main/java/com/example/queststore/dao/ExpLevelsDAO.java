package com.example.queststore.dao;

import com.example.queststore.models.ExpLevel;

import java.util.List;

public interface ExpLevelsDAO {

    List<ExpLevel> getAll();
    ExpLevel getByName(String levelName);
    boolean add(ExpLevel expLevel);
    boolean delete(String levelName);
    void setDatabasePath(String path);
}
