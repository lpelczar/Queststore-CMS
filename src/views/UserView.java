package views;

import java.util.*;

abstract class UserView {

    protected int askForOption() throws InputMismatchException {

        int option = 0;

        while(!scanner.hasNextInt()) {
            System.out.println("\nEnter option: ");
            option = scanner.nextInt();
        }
        return option;
    }

    protected String askForInput() throws InputMismatchException {

        String option = "";

        while(!scanner.hasNextLine()) {
            System.out.println("\nEnter option: ");
            option = scanner.nextLine();
        }
        return option;
    }
    
    protected void displayMessage(String message) {
    
        System.out.println(message);
    
    }

}
