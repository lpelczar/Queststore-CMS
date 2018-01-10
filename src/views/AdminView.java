package views;
import java.util.*;

public class AdminView {

    private Map<Integer, String> menu;
    private Scanner scanner = new Scanner(System.in);

    public void prepareAdminMenu() {
        menu.put(1, "Promote blank user.");
        menu.put(2, "Edit user profile.");
        menu.put(3, "Create new group.");
        menu.put(4, "Edit level treshold.");
        menu.put(5, "Show all users.");
        menu.put(6, "Log out.");
    }

    public void displayAdminMenu() {
        for (Integer option : menu.keySet()) {
            System.out.println(option + ". " + menu.get(option) + "\n");
        }
    }

    public void handleAdminMenu() {
        prepareAdminMenu();
        displayAdminMenu();
    }

    public int askForOption() throws InputMismatchException {
        while(!scanner.hasNextInt()) {
            System.out.println("\nEnter option: ");
            int option = scanner.nextInt();
        }
        return option;
    }
}
