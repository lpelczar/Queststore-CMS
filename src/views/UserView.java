package views;

import java.util.*;

abstract class UserView {

    private Scanner scanner = new Scanner(System.in);

    public int askForOption() {
        int option = 0;

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

        while(!scanner.hasNextLine()) {
            option = scanner.nextLine();
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
