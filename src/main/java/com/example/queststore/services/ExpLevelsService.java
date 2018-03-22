package com.example.queststore.services;

import com.example.queststore.dao.DbExpLevelsDAO;
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

    public void removeLevelOfExperience() {

        List<ExpLevel> levels = new ArrayList<>(expLevelsDAO.getAll());
        expLevelsView.displayEntriesNoInput(levels);
        if (expLevelsDAO.getAll().isEmpty()) {
            expLevelsView.displayPressAnyKeyToContinueMessage();
            return;
        }

        String levelName = expLevelsView.getLevelNameInput();
        if (expLevelsDAO.getByName(levelName) != null) {
            if (expLevelsDAO.delete(levelName)) {
                expLevelsView.displayLevelDeletedMessage();
            } else {
                expLevelsView.displayDeleteErrorMessage();
            }
        } else {
            expLevelsView.displayThereIsNoLevelWithThisNameMessage();
        }
    }

    public void showAllLevelsOfExperience() {

        List<ExpLevel> expLevels = new ArrayList<>(expLevelsDAO.getAll());
        expLevelsView.displayEntries(expLevels);
    }
}
