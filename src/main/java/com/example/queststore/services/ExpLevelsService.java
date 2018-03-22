package com.example.queststore.services;

import com.example.queststore.dao.ExpLevelsDAO;
import com.example.queststore.models.ExpLevel;
import com.example.queststore.views.ExpLevelsView;

import java.util.ArrayList;
import java.util.List;

public class ExpLevelsService {

    private ExpLevelsView expLevelsView;
    private ExpLevelsDAO expLevelsDAO;

    public ExpLevelsService(ExpLevelsView expLevelsView, ExpLevelsDAO expLevelsDAO) {
        this.expLevelsView = expLevelsView;
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
