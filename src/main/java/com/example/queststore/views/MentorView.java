package com.example.queststore.views;

import com.example.queststore.models.Item;

import java.util.*;

public class MentorView extends UserView {

    private Map<Integer, String> menu = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    private void prepareMentorMenu() {
        menu.put(1, "Promote blank user to student.");
        menu.put(2, "Add student to group.");
        menu.put(3, "Add new quest.");
        menu.put(4, "Add new artifact.");
        menu.put(5, "Edit quest.");
        menu.put(6, "Edit artifact.");
        menu.put(7, "Mark student achieved quest.");
        menu.put(8, "Mark student bought artifact.");
        menu.put(9, "Show student balance and all his artifacts.");
        menu.put(10, "Log out.");
    }

    private void displayMentorMenu() {
        for (Integer option : menu.keySet()) {
            System.out.println(option + ". " + menu.get(option));
        }
    }

    public void handleMentorMenu() {
        prepareMentorMenu();
        clearConsole();
        displayMentorMenu();
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

    public int askForChange(Item item) {
        System.out.println("\n" + item.toString());
        System.out.println("\nWhat would you like to update:" +
                "\n1. Name" +
                "\n2. Price" +
                "\n3. Category" +
                "\n4. Description");

        return askForOption();
    }

    public void displayCreatingItem() {

        System.out.println("Create new bonus menu: ");
        displayUpdateName();
    }

    public Integer askForPrice() throws InputMismatchException {
        Integer price = 0;
        System.out.println("Price: ");
        price = scanner.nextInt();
        return price;
    }

    public String askForItemCategory() throws InputMismatchException {
        List<String> itemCategories = new ArrayList<>(Arrays.asList("basic", "advanced"));

        String userCategoryChoose = "";

        while (!itemCategories.contains(userCategoryChoose)) {
            System.out.println("Choose category of bonus, type \'basic\' or \'advanced\': ");
            userCategoryChoose = scanner.next();
        }
        return userCategoryChoose;
    }

    public void displayUpdateName() { System.out.println("Enter new name: "); }

    public void displayUpdatePrice() { System.out.println("Enter new price: "); }

    public void displayUpdateDescription() { System.out.println("Enter new description: "); }

    public void displayOperationSuccessful() { System.out.println("Item has been added to DB succesfully!" ); }

}
