package com.example.queststore.views;

import com.example.queststore.models.Item;
import com.example.queststore.utils.InputGetter;

import java.util.*;

public class StudentView extends AbstractView {

    private Map<Integer, String> menu = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    private void prepareStudentMenu() {
        menu.put(1, "Show your bonuses.");
        menu.put(2, "Buy artifact.");
        menu.put(3, "Buy artifact for you and your teammates.");
        menu.put(4, "Log out.");
    }

    private void displayStudentMenu() {
        System.out.println();
        for (Integer option : menu.keySet()) {
            System.out.println(option + ". " + menu.get(option));
        }
    }

    public void handleStudentMenu() {
        prepareStudentMenu();
        displayStudentMenu();
    }

    public void displayInfoBar(int balance, String level) {
        clearConsole();
        System.out.format("%-10s%-30d%-8s%-30s", "Balance: ", balance , "Level: ", level);
        System.out.println();
    }

    public int askForOption() throws InputMismatchException {
        System.out.println("\nEnter option: ");
        return scanner.nextInt();
    }

    public void displayStudentBackpack(List<Item> backpack) {
        clearConsole();
        if (backpack.isEmpty()) {
            System.out.println("You don\'t have any items!");

        } else {
            for (Item item : backpack) {
                System.out.println(item.getName());
                System.out.println(item.getDescription() + "\n");
            }
        }
        displayPressAnyKeyToContinueMessage();
    }

    public int chooseItemFrom(List<Item> itemsStore) {
        for (Item item : itemsStore) {
            displayItemInfo(item);
        }
        return InputGetter.getIntInputFromConsole("Enter ID of item: ");
    }

    private void displayItemInfo(Item item) {
        System.out.println(item.toString());
        System.out.println();
    }

    public void displayWrongId() { System.out.println("\nYou type wrong ID!"); displayPressAnyKeyToContinueMessage(); }

    public void displayItemAlreadyContaining() { System.out.println("You already have this item!"); displayPressAnyKeyToContinueMessage(); }

    public void displayNoMoney() { System.out.println("You don\'t have enough balance!"); displayPressAnyKeyToContinueMessage(); }

    public void displayStudentDataHasBeenUpdated() {
        System.out.println("Student data has been updated!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayErrorUpdatingStudentData() {
        System.out.println("Error updating student data!");
        displayPressAnyKeyToContinueMessage();

    }

    public void displayStudentInfo(List<String> studentInfo, List<Item> items) {
        int NAME_INDEX = 0;
        int BALANCE_INDEX = 1;

        System.out.println();
        System.out.format("%-1s%-7s%-20s%-10s%-20s",
                "\n", "Name: ", studentInfo.get(NAME_INDEX), "Balance: ", studentInfo.get(BALANCE_INDEX));

        if (!items.isEmpty()) {
            for (Item item : items) {
                System.out.println();
                System.out.format("%-30s%-20s%-40s", item.getName(), item.getCategory(), item.getDescription());
            }
        } else { displayStudentHaveNotItems(); }
    }

    private void displayStudentHaveNotItems() { System.out.println("No items for display."); }

    public void displayNoStudents() { System.out.println("No students for display!"); }

    public void displayStudentHaveNoTeamAssignedMessage() {
        System.out.println("Student have no team assigned!");
        displayPressAnyKeyToContinueMessage();
    }
}
