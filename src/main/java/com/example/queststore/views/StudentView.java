package main.java.com.example.queststore.views;

import java.util.*;
import main.java.com.example.queststore.models.Item;

public class StudentView extends UserView{

    private Map<Integer, String> menu = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void prepareStudentMenu() {
        menu.put(1, "Show your bonuses.");
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

    public void displayInfoBar(int balance, String level) {
        clearConsole();
        System.out.format("%-10s%-30d%-8s%-30s", "Balance: ", balance , "Level: ", level);
        System.out.println();
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

    public void showItemsInStore(List<Item> itemsStore) {
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

    public void displayNoMoney() { System.out.println("You don\'t have enough balance!"); }
}
