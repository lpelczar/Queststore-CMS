package views;

import java.util.*;
import models.Item;

public class StudentView {

    private Map<Integer, String> menu;
    private Scanner scanner = new Scanner(System.in);

    public void prepareStudentMenu() {
        menu.put(1, "Show your wallet.");
        menu.put(2, "Buy artifact.");
        menu.put(3, "Buy artifact for you and your teammates.");
        menu.put(4, "Show your level.");
        menu.put(5, "Log out.");
    }

    public void displayStudentMenu() {
        for (Integer option : menu.keySet()) {
            System.out.println(option + ". " + menu.get(option) + "\n");
        }
    }

    public void handleStudentMenu() {
        prepareStudentMenu();
        displayStudentMenu();
    }

    public int askForOption() throws InputMismatchException {

        int option = 0;

        while(!scanner.hasNextInt()) {
            System.out.println("\nEnter option: ");
            option = scanner.nextInt();
        }
        return option;
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
