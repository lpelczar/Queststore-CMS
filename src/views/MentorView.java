package views;
import models.BlankUser;

import java.util.*;

public class MentorView {

    private Map<Integer, String> menu;
    private Scanner scanner = new Scanner(System.in);

    public void prepareMentorMenu() {
        menu.put(1, "Promote blank user to student.");
        menu.put(2, "Add new quest.");
        menu.put(3, "Add new artifact to store.");
        menu.put(4, "Edit quest.");
        menu.put(5, "Edit artifact.");
        menu.put(6, "Mark Codecooler's quest.");
        menu.put(7, "Mark Codecooler's bought artifacts.");
        menu.put(8, "See Codecooler's wallet.");
        menu.put(9, "Log out.");
    }

    public void displayMentorMenu() {
        for (Integer option : menu.keySet()) {
            System.out.println(option + ". " + menu.get(option) + "\n");
        }
    }

    public void handleMentorMenu() {
        prepareMentorMenu();
        displayMentorMenu();
    }

    public int askForOption() throws InputMismatchException {

        int option = 0;

        while(!scanner.hasNextInt()) {
            System.out.println("\nEnter option: ");
            option = scanner.nextInt();
        }
        return option;
    }

    public void displayBlankUsers(List<BlankUser> blankUsers) {
        for (BlankUser user : blankUsers) {
            System.out.println(user.toString());
        }
    }
    
    public String askForLoginToPromote() {
        return scanner.next();
    }

}
