package views;

import java.util.*;

abstract class UserView {

    private Scanner scanner;

    public int askForOption() {
        int option = 0;
        scanner = new Scanner(System.in);

        try {
            System.out.println("\nEnter option: ");
            option = scanner.nextInt();

        } catch (InputMismatchException e) {
            System.out.println("You type wrong sign!");
        }
        return option;
    }

    public String askForInput() throws InputMismatchException {

        String option = "";

        scanner = new Scanner(System.in);

        try {
            System.out.println("\nEnter option: ");
            option = scanner.nextLine();

        } catch (InputMismatchException e) {
            System.out.println("You type wrong sign!");
        }
        return option;
    }

    public void displayMessage(String message) {

        System.out.println(message);

    }

    public void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
