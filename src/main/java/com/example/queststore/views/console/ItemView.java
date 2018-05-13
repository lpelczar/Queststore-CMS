package com.example.queststore.views.console;

import com.example.queststore.models.Item;
import com.example.queststore.utils.ConsoleInputGetter;

import java.util.*;

public class ItemView extends AbstractView {

    public void displayCreatingItem() {
        clearConsole();
        System.out.println("Create new bonus menu: ");
    }

    public String displayGetName() {return ConsoleInputGetter.getStringInputFromConsole("Enter new name: "); }

    public int displayGetPrice() {return ConsoleInputGetter.getIntInputFromConsole("Enter new price: "); }

    public String displayGetDescription() {return ConsoleInputGetter.getStringInputFromConsole("Enter new description: "); }

    public void displayItemHasBeenAdded() { System.out.println("Item has been added successfully!" ); }

    public String askForItemCategory() throws InputMismatchException {
        List<String> itemCategories = new ArrayList<>(Arrays.asList("Basic", "Extra"));

        String userCategoryChoose = "";

        while (!itemCategories.contains(userCategoryChoose)) {
            System.out.println("Choose category of bonus, type \'Basic\' or \'Extra\': ");
            Scanner scanner = new Scanner(System.in);
            userCategoryChoose = scanner.next();
        }
        return userCategoryChoose;
    }

    public int getIdOfItem() { return ConsoleInputGetter.getIntInputFromConsole("\nEnter id of item to edit: "); }

    public int askForPropertyToEdit(Item item) {
        clearConsole();
        return ConsoleInputGetter.getIntInputFromConsole(item.toString() +
                "\n\nWhat would you like to update:" +
                "\n1. Name" +
                "\n2. Price" +
                "\n3. Category" +
                "\n4. Description");
    }

    public void displayNoItems() {
        System.out.println("There is no items!");
        displayPressAnyKeyToContinueMessage();
    }

    public String getStudentLoginToMarkArtifact() {
        return ConsoleInputGetter.getStringInputFromConsole("Enter student login to mark the artifact: ");
    }

    public String getItemNameInput() {
        return ConsoleInputGetter.getStringInputFromConsole("Enter item name: ");
    }

    public void displayItemHasBeenMarked() {
        System.out.println("Item has been marked!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayErrorMarkingItem() {
        System.out.println("Error marking item!");
        displayPressAnyKeyToContinueMessage();
    }
}
