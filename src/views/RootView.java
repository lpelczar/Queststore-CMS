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
}
