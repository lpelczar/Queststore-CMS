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

    public String getNewUserLogin() {
        System.out.print("Enter login (minimum 6 and maximum 15 characters): ");
        return getStringInput();
    }

    public String getNewUserPassword() {
        System.out.print("Enter password (minimum 6 and maximum 15 characters): ");
        return getStringInput();
    }

    public String getNewUserName() {
        System.out.print("Enter name: ");
        return getStringInput();
    }

    public String getNewUserEmail() {
        System.out.print("Enter email: ");
        return getStringInput();
    }

    public String getNewUserPhoneNumber() {
        System.out.print("Enter phone number: ");
        return getStringInput();
    }

    public void displayUserNotAssignedMessage() {
        System.out.println("\nUser is no assigned yet, you need to wait for Admin to approve your account.");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayUserWithThisNameAlreadyExists() {
        System.out.println("\nUser with that name already exists!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayUserCreated(String login, String name, String email, String phoneNumber) {
        System.out.println("\nUser created: " +
                           "\nLogin: " + login +
                           "\nName: " + name +
                           "\nEmail: " + email +
                           "\nPhone number: " + phoneNumber +
                           "\nYou need to wait for Admin to activate it!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayUserNotExistsMessage() {
        System.out.println("\nUser not exists!");
        displayPressAnyKeyToContinueMessage();
    }

    private void displayPressAnyKeyToContinueMessage() {
        System.out.print("\nPress any key to continue.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}