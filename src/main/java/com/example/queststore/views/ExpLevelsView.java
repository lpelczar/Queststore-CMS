package com.example.queststore.views;

import com.example.queststore.utils.InputGetter;

public class ExpLevelsView extends AbstractView {

    public String getLevelNameInput() {
        return InputGetter.getStringInputFromConsole("Enter level of experience name: ");
    }

    public int getLevelValueInput() {
        return InputGetter.getIntInputFromConsole("Enter value of experience to set: ");
    }

    public void displayLevelSetMessage() {
        System.out.println("Level has been set!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayLevelDeletedMessage() {
        System.out.println("Experience level has been deleted!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayDeleteErrorMessage() {
        System.out.println("Error deleting experience level!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayThereIsNoLevelWithThisNameMessage() {
        System.out.println("There is no experience level with this name!");
        displayPressAnyKeyToContinueMessage();
    }

}
