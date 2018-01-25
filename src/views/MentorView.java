package views;

import java.util.*;

public class MentorView extends UserView {

    private Map<Integer, String> menu;
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

    public String askForCategory() {
        char option = 'q';

        while (true) {
            System.out.println("\nBasic or extra(b/e):");
            option = scanner.next().charAt(0);
            if (option == 'b') return "Basic";
            else if (option == 'e') return "Extra";
        }
    }

    @SuppressWarnings("deprecation")
    public Date askForDeadline() {
        System.out.println("\nDeadline:");
        try {
            return new Date(askForOption(), askForOption(), askForOption());
        } catch (InputMismatchException e) {
            System.err.println("You type wrong sign!");
        }
        return null;
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

    public int askForPrice() throws InputMismatchException {

        int price = 0;
        System.out.println("\nPrice: ");
        price = scanner.nextInt();
        return price;
    }

    public String askForItemCategory() throws InputMismatchException {
        List<String> itemCategories = new List<>("basic", "advanced");
        String userCategoryChoose = "";

        while (itemCategories.contains(userCategoryChoose)) {
            System.out.println("Choose category of bonus, type \'basic\' or \'advance\': ");
            userCategoryChoose = scanner.next();
        }
        return userCategoryChoose;
    }

    public void displayOperationSuccesfull() {
        System.out.println("Item has been added to DB succesfully!");
    }

    public void displayOperationFailed() {
        System.out.println("Operation has been failed!");
    }
}
