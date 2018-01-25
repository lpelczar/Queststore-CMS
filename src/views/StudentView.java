package views;

import java.util.*;
import models.Item;

public class StudentView {

    private Map<Integer, String> menu = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    private void prepareStudentMenu() {
        menu.put(1, "Show your wallet.");
        menu.put(2, "Buy artifact.");
        menu.put(3, "Buy artifact for you and your teammates.");
        menu.put(4, "Show your level.");
        menu.put(5, "Log out.");
    }

    private void displayStudentMenu() {
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
        return scanner.nextInt();
    }

    public void displayStudentBackpack(List<Item> backpack) {
        for (Item item : backpack) {
            System.out.println(item.getName());
            System.out.println(item.getDescription() + "\n");
        }
    }

    public void displayStudentLevel(String level) {
        System.out.println("Your level is " + level + ".");
    }

}
