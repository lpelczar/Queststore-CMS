package queststore.views;


import queststore.models.Task;
import queststore.utils.InputGetter;

import java.util.Arrays;

public class TaskView extends AbstractView {

    public String getQuestNameInput() {
        return InputGetter.getStringInputFromConsole("Enter quest name: ");
    }

    public void displayQuestAlreadyExists() {
        System.out.println("Quest with this name already exists!");
        displayPressAnyKeyToContinueMessage();
    }

    public int getQuestPointsInput() {
        return InputGetter.getIntInputFromConsole("Enter points prize: ");
    }

    public String getQuestDescriptionInput() {
        return InputGetter.getStringInputFromConsole("Enter quest description: ");
    }

    public String getQuestCategory() {

        final String[] CORRECT_OPTIONS = {"b", "e"};
        String userInput = "";
        boolean userInputInCorrectOptions = false;

        while (!userInputInCorrectOptions) {
            userInput = InputGetter.getStringInputFromConsole("Enter 'b' to add basic quest or 'e' to add extra quest: ");
            userInputInCorrectOptions = Arrays.asList(CORRECT_OPTIONS).contains(userInput.toLowerCase());
            if (!userInputInCorrectOptions) {
                System.out.println("Wrong input!");
            }
        }
        return userInput;
    }

    public void displayQuestSuccessfullyAdded() {
        System.out.println("Quest successfully added!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayErrorAddingQuest() {
        System.out.println("Error adding quest!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayThereIsNoTaskWithThisName() {
        System.out.println("There is no task with this name!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayWrongOptionMessage() {
        System.out.println("You have chosen wrong option!");
        displayPressAnyKeyToContinueMessage();
    }

    public String getValueToUpdate(Task task) {
        System.out.println("\n" + task);
        System.out.println("\nWhat would you like to change:" +
                "\n1. Points" +
                "\n2. Description" +
                "\n3. Category");
        return InputGetter.getStringInputFromConsole("Enter option: ");
    }

    public int askForPointsInput() {
        return InputGetter.getIntInputFromConsole("Enter new points value: ");
    }

    public String askForDescriptionInput() {
        return InputGetter.getStringInputFromConsole("Enter new description value: ");
    }

    public String getStudentLoginToMarkQuest() {
        return InputGetter.getStringInputFromConsole("Enter student login to mark the quest: ");
    }

    public String getTaskNameInput() {
        return InputGetter.getStringInputFromConsole("Enter task name: ");
    }

    public void displayTaskConnectionAdded() {
        System.out.println("Task student connection has been added!");
    }

    public void displayErrorAddingTaskConnection() {
        System.out.println("Error adding task student connection!");
        displayPressAnyKeyToContinueMessage();
    }
}
