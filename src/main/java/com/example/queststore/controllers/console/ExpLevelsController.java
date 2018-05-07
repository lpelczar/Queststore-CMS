package com.example.queststore.controllers.console;

import com.example.queststore.dao.ExpLevelsDAO;
import com.example.queststore.models.ExpLevel;
import com.example.queststore.views.console.ExpLevelsView;

import java.util.ArrayList;
import java.util.List;

public class ExpLevelsController {

    private ExpLevelsView expLevelsView;
    private ExpLevelsDAO expLevelsDAO;

    public ExpLevelsController(ExpLevelsView expLevelsView, ExpLevelsDAO expLevelsDAO) {
        this.expLevelsView = expLevelsView;
        this.expLevelsDAO = expLevelsDAO;
    }

    public void addLevelOfExperience() {

        String levelName = expLevelsView.getLevelNameInput();
        int value = expLevelsView.getLevelValueInput();

        if (expLevelsDAO.add(new ExpLevel(levelName, value))) {
            expLevelsView.displayLevelSetMessage();
        } else {
            expLevelsView.displayErrorChangingTheValue();
        }
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
