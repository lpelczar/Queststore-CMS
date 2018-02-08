package com.example.queststore.views;

import com.example.queststore.utils.InputGetter;

public class RootView extends AbstractView {

    public void displayMenu() {

        clearConsole();
        System.out.println("   ____                  _       _                          ___   ____  \n" +
                "  / __ \\                | |     | |                        / _ \\ |___ \\ \n" +
                " | |  | |_   _  ___  ___| |_ ___| |_ ___  _ __ ___  __   _| | | |  __) |\n" +
                " | |  | | | | |/ _ \\/ __| __/ __| __/ _ \\| '__/ _ \\ \\ \\ / / | | | |__ < \n" +
                " | |__| | |_| |  __/\\__ \\ |_\\__ \\ || (_) | | |  __/  \\ V /| |_| | ___) |\n" +
                "  \\___\\_\\\\__,_|\\___||___/\\__|___/\\__\\___/|_|  \\___|   \\_/  \\___(_)____/ \n" +
                "                                                                        \n" +
                "                                                                        " +
                "\nWhat do you want to do?\n" +
                " 1. Sign in\n" +
                " 2. Sign up\n" +
                " 0. Exit");
    }

    public String getUserInput() {
        System.out.print("Choose option: ");
        return getStringInput();
    }

    public void displayWrongInputMessage() {
        System.out.println("Wrong input!");
    }

    public String getUserLogin() {
        System.out.print("Enter login (or q to quit): ");
        return getStringInput();
    }

    public String getUserPassword() {
        System.out.print("Enter password (or q to quit): ");
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
        return InputGetter.getStringInputFromConsole("Enter name: ");
    }

    public String getNewUserEmail() {
        System.out.print("Enter email: ");
        return getStringInput();
    }

    public String getNewUserPhoneNumber() {
        System.out.print("Enter phone number in format (000-000-000): ");
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
        System.out.println("\nUser not exists!\n");
    }

    public void displayUserWithThisEmailAlreadyExists() {
        System.out.println("\nUser with this email already exists.");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayUserWithThisPhoneNumberAlreadyExists() {
        System.out.println("\nUser with this phone number already exists.");
        displayPressAnyKeyToContinueMessage();
    }
}
