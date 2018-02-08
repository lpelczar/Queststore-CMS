package com.example.queststore.views;

import com.example.queststore.models.Entry;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

abstract class AbstractView {

    String getStringInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void displayPressAnyKeyToContinueMessage() {
        System.out.print("\nPress any key to continue.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void displayEntries(List<Entry> entries) {
        showAllEntries(entries);
        displayPressAnyKeyToContinueMessage();
    }

    public void displayEntriesNoInput(List<Entry> entries) {
        showAllEntries(entries);
    }

    private void showAllEntries(List<Entry> entries) {
        System.out.println("");
        if(!entries.isEmpty()) {
            int index = 1;
            for (Entry entry : entries) {
                System.out.println(index + ". " + entry);
                index++;
            }
        } else {
            System.out.println("List is empty!");
        }
    }

    public int askForOption() {
        int option = 0;
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("\nEnter option: ");
            option = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("You type wrong sign!");
        }
        return option;
    }

    public void displayErrorChangingTheValue() {
        System.out.println("Error changing the value!");
        displayPressAnyKeyToContinueMessage();
    }

    public String askForNewValue() {
        System.out.print("Enter new value: ");
        return getStringInput();
    }

    public void displayWrongSignError() {
        System.out.println("There is no such option!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayValueHasBeenChanged() {
        System.out.println("Value has been changed!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayHasBeenPromoted() {
        System.out.println("User has been promoted!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayUserNotExists() {
        System.out.println("User not exists!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayThereIsNoStudentWithThisLogin() {
        System.out.println("There is no student with this login!");
        displayPressAnyKeyToContinueMessage();
    }

    public int askForInt() {
        int option = 0;
        Scanner scanner = new Scanner(System.in);

        try {
            option = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("You type wrong sign");
        }

        return option;
    }

    public void displayOperationSuccessfull() { System.out.println(" Operation successfully completed!"); }

    public void displayOperationFailed() { System.out.println("Operation failed!"); }
}
