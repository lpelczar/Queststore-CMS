package com.example.queststore.services;

import com.example.queststore.dao.ExpLevelsDAO;
import com.example.queststore.models.ExpLevel;

import java.util.ArrayList;
import java.util.List;

public class ExpLevelsService {

    private ExpLevelsDAO expLevelsDAO;

    public ExpLevelsService(ExpLevelsDAO expLevelsDAO) {
        this.expLevelsDAO = expLevelsDAO;
    }

    public boolean addLevelOfExperience(String levelName, int value) {
        return expLevelsDAO.add(new ExpLevel(levelName, value));
    }

    public boolean removeLevelOfExperience(String levelName) {
        return expLevelsDAO.delete(levelName);
    }

    public List<ExpLevel> getAllLevelsOfExperience() {
        return new ArrayList<>(expLevelsDAO.getAll());
    }
}
