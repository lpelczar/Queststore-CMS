package queststore.views;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserView extends AbstractView {

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

    public String askForLogin() {
        scanner = new Scanner(System.in);

        System.out.print("\nEnter login of profile to change: ");
        return scanner.next();
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

    public void displayOperationSuccessfullyCompleted() { System.out.println(" Operation succesfull!"); }
}
