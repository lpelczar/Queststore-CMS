package com.example.queststore.views;

import com.example.queststore.models.User;
import com.example.queststore.utils.InputGetter;

import java.util.HashMap;
import java.util.Map;

public class MentorView extends AbstractView {

    private Map<Integer, String> menu = new HashMap<>();

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

    public String getStudentLoginToAssignGroup() { return InputGetter.getStringInputFromConsole("Enter student name to assign him to group: "); }

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

    public void displayErrorChangingTheValue() {
        System.out.println("Error changing the value!");
        displayPressAnyKeyToContinueMessage();
    }

    public String getMentorLoginToDelete() {
        System.out.print("Enter mentor login to delete all his data: ");
        return getStringInput();
    }

    public void displayMentorDeletedMessage() {
        System.out.println("Mentor has been deleted!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayNoMentorMessage() {
        System.out.println("There is no mentor with this login!");
        displayPressAnyKeyToContinueMessage();
    }

    public String getMentorLoginToShowProfile() {
        System.out.print("Enter login of the mentor: ");
        return getStringInput();
    }

    public void displayMentorProfile(User mentor) {
        System.out.println("");
        System.out.println(mentor);
    }

    public String getMentorLoginToEdit() {
        System.out.print("\nEnter mentor login to edit his data (or q to go back): ");
        return getStringInput();
    }

    public void displayThereIsNoMentorWithThisLogin() {
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

    public String getMentorLoginToAssignGroup() {
        System.out.print("Enter mentor login: ");
        return getStringInput();
    }

    public String getMentorLoginToRevokeFromGroup() {
        System.out.print("Enter mentor login to revoke him from group: ");
        return getStringInput();
    }

    public void displayMentorHasNoGroupsAssigned() {
        System.out.println("Mentor has no groups assigned!");
        displayPressAnyKeyToContinueMessage();
    }


}
