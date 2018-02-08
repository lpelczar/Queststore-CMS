package com.example.queststore.services;

import com.example.queststore.dao.DbExpLevelsDAO;
import com.example.queststore.dao.ExpLevelsDAO;
import com.example.queststore.models.Entry;
import com.example.queststore.models.ExpLevel;
import com.example.queststore.views.ExpLevelsView;

import java.util.ArrayList;
import java.util.List;

public class ExpLevelsService {

    private ExpLevelsView expLevelsView = new ExpLevelsView();
    private ExpLevelsDAO dbExpLevelsDAO = new DbExpLevelsDAO();

    public void addLevelOfExperience() {

        String levelName = expLevelsView.getLevelNameInput();
        int value = expLevelsView.getLevelValueInput();

        if (dbExpLevelsDAO.add(new ExpLevel(levelName, value))) {
            expLevelsView.displayLevelSetMessage();
        } else {
            expLevelsView.displayErrorChangingTheValue();
        }
    }

    public void removeLevelOfExperience() {

        List<Entry> levels = new ArrayList<>(dbExpLevelsDAO.getAll());
        expLevelsView.displayEntriesNoInput(levels);
        if (dbExpLevelsDAO.getAll().isEmpty()) {
            expLevelsView.displayPressAnyKeyToContinueMessage();
            return;
        }

        String levelName = expLevelsView.getLevelNameInput();
        if (dbExpLevelsDAO.getByName(levelName) != null) {
            if (dbExpLevelsDAO.delete(levelName)) {
                expLevelsView.displayLevelDeletedMessage();
            } else {
                expLevelsView.displayDeleteErrorMessage();
            }
        } else {
            expLevelsView.displayThereIsNoLevelWithThisNameMessage();
        }
    }

    public void showAllLevelsOfExperience() {

        List<Entry> expLevels = new ArrayList<>(dbExpLevelsDAO.getAll());
        expLevelsView.displayEntries(expLevels);
    }
}
