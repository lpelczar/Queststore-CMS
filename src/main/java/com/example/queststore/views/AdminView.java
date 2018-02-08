package com.example.queststore.views;


import com.example.queststore.models.User;
import com.example.queststore.utils.InputGetter;

import java.util.HashMap;
import java.util.Map;

public class AdminView extends AbstractView {

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

    public void displayValueHasBeenChanged() {
        System.out.println("Value has been changed!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayMentorProfile(User mentor) {
        System.out.println("");
        System.out.println(mentor);
    }

    public String getMentorLoginToShow() {
        System.out.print("Enter login of the mentor: ");
        return getStringInput();
    }

    public String getGroupNameInput() {
        System.out.print("Enter group name: ");
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
}
