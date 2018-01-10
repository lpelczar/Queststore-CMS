package views;

import java.util.Scanner;

public class RootView {

    public void displayMenu() {

        System.out.println("Welcome to QuestStore" +
                "\nWhat do you want to do?\n" +
                " 1. Sign in\n" +
                " 2. Sign up\n" +
                " 0. Exit");
    }

    public String getUserInput() {
        System.out.print("Choose option: ");
        return getStringInput();
    }

    private String getStringInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void displayWrongInputMessage() {
        System.out.println("Wrong input!");
    }

    public String getUserLogin() {
        System.out.print("Enter login: ");
        return getStringInput();
    }

    public String getUserPassword() {
        System.out.print("Enter password: ");
        return getStringInput();
    }

    public void displayUserNotAssignedMessage() {
        System.out.println("\nUser is no assigned yet, you need to wait for Admin to approve your account.");
        displayPressAnyKeyToContinueMessage();
    }

    private void displayPressAnyKeyToContinueMessage() {
        System.out.print("\nPress any key to continue.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
