package com.example.queststore.views;

import com.example.queststore.models.Entry;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserView {

    private Scanner scanner;

    public int askForOption() {
        int option = 0;
        scanner = new Scanner(System.in);

        try {
            System.out.print("\nEnter option: ");
            option = scanner.nextInt();

        } catch (InputMismatchException e) {
            System.out.println("You type wrong sign!");
        }
        return option;
    }


    public Boolean getTypeOfPromotion() {
        String userChoose = "";
        String[] promoteOptions = {"m", "s"};
        scanner = new Scanner(System.in);

        System.out.println("Type 'm' if you want promote user to mentor or 's' to student");

        while (!Arrays.asList(promoteOptions).contains(userChoose.toLowerCase())) {
            userChoose = scanner.next();

            if (userChoose.equals("m")) {
                return true;
            }
            else if (userChoose.equals("s")) {
                return false;
            }
        }
        return null;
    }

    public void displayHasBeenPromoted() {
        System.out.println("User has been promoted!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayUserNotExists() {
        System.out.println("User not exists!");
        displayPressAnyKeyToContinueMessage();
    }

    public String askForLogin() {
        scanner = new Scanner(System.in);

        System.out.print("\nEnter login of profile to change: ");
        return scanner.next();
    }

    public void displayEntries(List<Entry> entries) {
        System.out.println("");
        if(!entries.isEmpty()) {
            for (Entry entry : entries) {
                System.out.println(entry);
            }
        } else {
            System.out.println("List is empty!");
        }
        displayPressAnyKeyToContinueMessage();
    }

    public void displayEntriesNoInput(List<Entry> entries) {
        System.out.println("");
        if(!entries.isEmpty()) {
            for (Entry entry : entries) {
                System.out.println(entry);
            }
        } else {
            System.out.println("List is empty!");
        }
    }

    public void displayPressAnyKeyToContinueMessage() {
        System.out.print("\nPress any key to continue.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    String getStringInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void displayUserDoesNotExist() {
        System.out.println("This user does not exist!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayEmptyListMsg() {
        System.out.println("List is empty!");
        displayPressAnyKeyToContinueMessage();
    }

    public int askForInt() {
        int option = 0;
        scanner = new Scanner(System.in);

        try {
            option = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("You type wrong sign");
        }

        return option;
    }

    public String askForString() {
        scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    public void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void displayOperationFailed() { System.out.println("Operation has been failed!"); }

    public void displayOperationSuccesfull() { System.out.println(" Operation succesfull!"); }
}
