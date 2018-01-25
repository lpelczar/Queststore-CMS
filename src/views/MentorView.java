package views;

import java.util.*;
import models.Item;

public class MentorView extends UserView {

    private Map<Integer, String> menu;
    private Scanner scanner = new Scanner(System.in);

    public void prepareMentorMenu() {
        menu.put(1, "Promote blank user to student.");
        menu.put(2, "Add new quest.");
        menu.put(3, "Add new bonus to store.");
        menu.put(4, "Edit quest.");
        menu.put(5, "Edit bonus.");
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

    public void displayCreatingTask() {
        System.out.println("Create new task:\nName:");
    }

//    public String askForCategory() {
//        char option = 'q';
//
//        while (true) {
//            System.out.println("\nBasic or extra(b/e):");
//            option = scanner.next().charAt(0);
//            if (option == 'b') return "Basic";
//            else if (option == 'e') return "Extra";
//        }
//    }
//
//    @SuppressWarnings("deprecation")
//    public Date askForDeadline() {
//        System.out.println("\nDeadline:");
//        try {
//            return new Date(askForOption(), askForOption(), askForOption());
//        } catch (InputMismatchException e) {
//            System.err.println("You type wrong sign!");
//        }
//        return null;
//    }

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

    public int askForPoints() throws InputMismatchException {

        int points = 0;

        while (!scanner.hasNextInt()) {
            System.out.println("\nPoints:");
            points = scanner.nextInt();
        }
        return points;
    }

    public void displayCreatingItem() {
        System.out.println("Create new Item:\nName:");
    }

    public Integer askForPrice() throws InputMismatchException {

        Integer price = 0;
        System.out.println("\nPrice: ");
        price = scanner.nextInt();
        return price;
    }

    public String askForItemCategory() throws InputMismatchException {
        List<String> itemCategories = new ArrayList<>(Arrays.asList("basic", "advanced"));

        String userCategoryChoose = "";

        while (itemCategories.contains(userCategoryChoose)) {
            System.out.println("Choose category of bonus, type \'basic\' or \'advance\': ");
            userCategoryChoose = scanner.next();
        }
        return userCategoryChoose;
    }

    public void displayUpdateName() {
        System.out.println("Enter new name: ");
    }

    public void displayUpdatePrice() {
        System.out.println("Enter new price: ");
    }

    public void displayUpdateDescription() {
        System.out.println("Enter new description: ");
    }

    public void displayOperationSuccesfull() {
        System.out.println("Item has been added to DB succesfully!");
    }

    public void displayOperationFailed() {
        System.out.println("Operation has been failed!");
    }
}
