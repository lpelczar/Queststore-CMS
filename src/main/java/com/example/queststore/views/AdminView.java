package com.example.queststore.views;


import com.example.queststore.models.User;
import com.example.queststore.utils.InputGetter;

import java.util.HashMap;
import java.util.Map;

public class AdminView extends UserView {

    private Map<Integer, String> menu = new HashMap<>();

    private void prepareAdminMenu() {
        menu.put(1, "Promote blank user.");
        menu.put(2, "Create new group.");
        menu.put(3, "Assign mentor to group.");
        menu.put(4, "Revoke mentor from group.");
        menu.put(5, "Delete group and all its associations.");
        menu.put(6, "Delete mentor and all his associations.");
        menu.put(7, "Edit mentor profile.");
        menu.put(8, "Show mentor profile and all his students.");
        menu.put(9, "Add level of experience.");
        menu.put(10, "Remove level of experience.");
        menu.put(11, "Show all levels of experience.");
        menu.put(12, "Log out.");
    }

    private void displayAdminMenu() {
        System.out.println("You are logged as Admin.");
        for (Integer option : menu.keySet()) {
            System.out.println(option + ". " + menu.get(option));
        }
    }

    public void handleAdminMenu() {
        prepareAdminMenu();
        displayAdminMenu();
    }

    public void displayNoMentorMessage() {
        System.out.println("There is no mentor with this login!");
        displayPressAnyKeyToContinueMessage();
    }

    public int askForChangeInProfile(User profile) {
        System.out.println("\n" + profile.toString());
        System.out.println("\nWhat would you like to change in profile:" +
                            "\n1. Name" +
                            "\n2. Login" +
                            "\n3. Email" +
                            "\n4. Phone number");

        return askForOption();
    }

    public void displayGroupAdded() {
        System.out.println("Group has been added!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayGroupWithThisNameAlreadyExists() {
        System.out.println("Group with this name already exists!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayThereIsNoMentorWithThisLogin() {
        System.out.println("There is no mentor with this login!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayValueHasBeenChanged() {
        System.out.println("Value has been changed!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayLevelSetMessage() {
        System.out.println("Level has been set!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayMentorProfile(User mentor) {
        System.out.println("");
        System.out.println(mentor);
    }

    public String getMentorLoginToAssignGroup() {
        System.out.print("Enter mentor login: ");
        return getStringInput();
    }

    public String getMentorLoginToShow() {
        System.out.print("Enter login of the mentor: ");
        return getStringInput();
    }

    public String getGroupNameInput() {
        System.out.print("Enter group name: ");
        return getStringInput();
    }

    public String getMentorLoginToEdit() {
        System.out.print("\nEnter mentor login to edit his data (or q to go back): ");
        return getStringInput();
    }

    public String getLevelNameInput() {
        return InputGetter.getStringInputFromConsole("Enter level of experience name: ");
    }

    public String askForNewValue() {
        System.out.print("Enter new value: ");
        return getStringInput();
    }

    public int getLevelValueInput() {
        return InputGetter.getIntInputFromConsole("Enter value of experience to set: ");
    }

    public void displayEmptyListMsg() {
        System.out.println("List is empty!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayUserDoesNotExist() {
        System.out.println("This user does not exist!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayWrongSignError() {
        System.out.println("There is no such option!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayErrorChangingTheValue() {
        System.out.println("Error changing the value!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayGroupConnectionAdded() {
        System.out.println("Group connection has been added!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayErrorAddingGroupConnection() {
        System.out.println("Error adding a connection!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayGroupConnectionRemoved() {
        System.out.println("Group connection has been removed!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayErrorRemovingGroupConnection() {
        System.out.println("Error removing a group connection!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayThereIsNoGroupWithThisName() {
        System.out.println("There is no group with this name!");
        displayPressAnyKeyToContinueMessage();
    }

    public String getMentorLoginToRevokeFromGroup() {
        System.out.print("Enter mentor login to revoke him from group: ");
        return getStringInput();
    }

    public String getMentorLoginToDelete() {
        System.out.print("Enter mentor login to delete all his data: ");
        return getStringInput();
    }

    public void displayMentorDeletedMessage() {
        System.out.println("Mentor has been deleted!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayGroupDeleted() {
        System.out.println("Group has been deleted!");
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

    public void displayGroupName(String groupName) {
        System.out.println("");
        System.out.println("Group name: " + groupName + " | Students in the group: ");
    }

    public void displayThisGroupHasNoStudentsAssigned() {
        System.out.println("This group has no students assigned!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayMentorHasNoGroupsAssigned() {
        System.out.println("Mentor has no groups assigned!");
        displayPressAnyKeyToContinueMessage();
    }
}
