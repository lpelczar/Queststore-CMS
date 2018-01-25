package views;

import java.util.*;

public class MentorView extends UserView {

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

    public void displayOperationSuccesfull() {
        System.out.println("Item has been added to DB succesfully!");
    }

    public void displayOperationFailed() {
        System.out.println("Operation has been failed!");
    }
}
