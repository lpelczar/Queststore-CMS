package views;

import java.util.*;

abstract class UserView {

    private Scanner scanner = new Scanner(System.in);
    public int askForOption() throws InputMismatchException {

        int option = 0;

        while(!scanner.hasNextInt()) {
            System.out.println("\nEnter option: ");
            option = scanner.nextInt();
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

}
