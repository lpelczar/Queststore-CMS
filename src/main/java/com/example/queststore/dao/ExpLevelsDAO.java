package com.example.queststore.dao;

import com.example.queststore.models.ExpLevel;

import java.util.List;

public interface ExpLevelsDAO {

    List<ExpLevel> getAll();
    boolean add(ExpLevel expLevel);
}
