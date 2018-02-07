package com.example.queststore.views;

import com.example.queststore.models.Item;
import com.example.queststore.models.Task;
import com.example.queststore.utils.InputGetter;

import java.util.*;

public class MentorView extends UserView {

    private Map<Integer, String> menu = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    private void prepareMentorMenu() {
        menu.put(1, "Promote blank user to student.");
        menu.put(2, "Add student to group.");
        menu.put(3, "Add new quest.");
        menu.put(4, "Add new artifact.");
        menu.put(5, "Edit quest.");
        menu.put(6, "Edit artifact.");
        menu.put(7, "Mark student achieved quest.");
        menu.put(8, "Mark student bought artifact.");
        menu.put(9, "Show student balance and all his artifacts.");
        menu.put(10, "Reroll students teams.");
        menu.put(11, "Log out.");
    }

    private void displayMentorMenu() {
        for (Integer option : menu.keySet()) {
            System.out.println(option + ". " + menu.get(option));
        }
    }

    public void handleMentorMenu() {
        prepareMentorMenu();
        clearConsole();
        displayMentorMenu();
    }

    public void displayStudentInfo(List<String> studentInfo, List<Item> items) {
        int NAME_INDEX = 0;
        int BALANCE_INDEX = 1;

        System.out.format("%-20%-20", studentInfo.get(NAME_INDEX), studentInfo.get(BALANCE_INDEX));
        for (Item item : items) {
            System.out.format("%-5%-20%-20%", item.getName(), item.getCategory(), item.getDescription());
        }
    }

    public void displayNoStudents() { System.out.println("No students for display!"); }

    public void displayCreatingItem() {
        clearConsole();
        System.out.println("Create new bonus menu: ");
    }

    public int askForPropertyToEdit(Item item) {
        clearConsole();
        return InputGetter.getIntInputFromConsole(item.toString() +
                                                    "\n\nWhat would you like to update:" +
                                                    "\n1. Name" +
                                                    "\n2. Price" +
                                                    "\n3. Category" +
                                                    "\n4. Description");
    }

    public String askForItemCategory() throws InputMismatchException {
        List<String> itemCategories = new ArrayList<>(Arrays.asList("basic", "advanced"));

        String userCategoryChoose = "";

        while (!itemCategories.contains(userCategoryChoose)) {
            System.out.println("Choose category of bonus, type \'basic\' or \'advanced\': ");
            userCategoryChoose = scanner.next();
        }
        return userCategoryChoose;
    }

    public String displayGetName() {return InputGetter.getStringInputFromConsole("Enter new name: "); }

    public int displayGetPrice() {return InputGetter.getIntInputFromConsole("Enter new price: "); }

    public String displayGetDescription() {return InputGetter.getStringInputFromConsole("Enter new description: "); }

    public void displayItemHasBeenAdded() { System.out.println("Item has been added to DB successful!" ); }

    public String getStudentLoginToAssignGroup() { return InputGetter.getStringInputFromConsole("Enter student name to assign him to group: "); }

    public void displayThereIsNoStudentWithThisLogin() {
        System.out.println("There is no student with this login!");
        displayPressAnyKeyToContinueMessage();
    }

    public String getGroupNameInput() {
        return InputGetter.getStringInputFromConsole("Enter group name: ");
    }

    public void displayGroupUpdated() {
        System.out.println("Group has been updated!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayErrorUpdatingGroup() {
        System.out.println("Error updating group!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayThereIsNoGroupWithThisName() {
        System.out.println("There is no group with this name!");
        displayPressAnyKeyToContinueMessage();
    }

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

    public String getValueToUpdate(Task task) {
        System.out.println("\n" + task);
        System.out.println("\nWhat would you like to change:" +
                "\n1. Points" +
                "\n2. Description" +
                "\n3. Category");
        return InputGetter.getStringInputFromConsole("Enter option: ");
    }

    public void displayValueHasBeenChanged() {
        System.out.println("Value has been changed!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayErrorChangingTheValue() {
        System.out.println("Error changing the value!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayWrongOptionMessage() {
        System.out.println("You have chosen wrong option!");
        displayPressAnyKeyToContinueMessage();
    }

    public int askForPointsInput() {
        return InputGetter.getIntInputFromConsole("Enter new points value: ");
    }

    public String askForDescriptionInput() {
        return InputGetter.getStringInputFromConsole("Enter new description value: ");
    }

    public void displayThereIsNoTaskWithThisName() {
        System.out.println("There is no task with this name!");
        displayPressAnyKeyToContinueMessage();
    }

    public String getStudentLoginToMarkQuest() {
        return InputGetter.getStringInputFromConsole("Enter student login to mark the quest: ");
    }

    public String getTaskNameInput() {
        return InputGetter.getStringInputFromConsole("Enter task name: ");
    }

    public void displayTaskConnectionAdded() {
        System.out.println("Task student connection has been added!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayErrorAddingTaskConnection() {
        System.out.println("Error adding task student connection!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayNoItems() {
        System.out.println("There is no items!");
        displayPressAnyKeyToContinueMessage();
    }

    public int getIdOfItem() { return InputGetter.getIntInputFromConsole("\nEnter id of item to edit: "); }

    public void displayStudentDataHasBeenUpdated() {
        System.out.println("Student data has been updated!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayErrorUpdatingStudentData() {
        System.out.println("Error updating student data!");
        displayPressAnyKeyToContinueMessage();

    }

}
