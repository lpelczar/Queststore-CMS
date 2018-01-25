package views;

import java.util.*;
import models.Item;

public class StudentView {

    private Map<Integer, String> menu = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void prepareStudentMenu() {
        menu.put(1, "Show your items.");
        menu.put(2, "Buy artifact.");
        menu.put(3, "Buy artifact for you and your teammates.");
        menu.put(4, "Show your level.");
        menu.put(5, "Log out.");
    }

    public void displayStudentMenu() {
        System.out.println();
        for (Integer option : menu.keySet()) {
            System.out.println(option + ". " + menu.get(option));
        }
    }

    public void handleStudentMenu() {
        prepareStudentMenu();
        displayStudentMenu();
    }

    public int askForOption() throws InputMismatchException {
        System.out.println("\nEnter option: ");
        int option = scanner.nextInt();
        return option;
    }

    public void displayStudentBackpack(List<Item> backpack) {
        for (Item item : backpack) {
            System.out.println(item.getName());
            System.out.println(item.getDescription() + "\n");
        }
    }

    public void displayItemsInStore(List<Item> itemsStore) {
        for (Item item : itemsStore) {
            displayItemInfo(item);
        }
        System.out.println("Enter ID of item to edit: ");
    }

    public void displayItemInfo(Item item) {
        System.out.println("ID: " + item.getID() + item.toString());
        System.out.println();
    }

    public void displayStudentLevel(String level) {
        System.out.println("Your level is " + level + ".");
    }

}
