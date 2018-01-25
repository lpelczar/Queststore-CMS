package controllers;


import java.util.InputMismatchException;
import java.util.List;
import views.MentorView;
import dao.DbItemDAO;
import models.Item;

public class MentorController {
    MentorView view = new MentorView();
    DbItemDAO dbItemDAO = new DbItemDAO();

    private boolean isRunning = true;

    public void start(){

        while (isRunning) {
            view.handleMentorMenu();

            int option = 0;

            try {
                option = view.askForOption();
            } catch (InputMismatchException e) {
                System.err.println("You type wrong sign!");
            }

            if (option == 1) {
            }
            else if (option == 2) {
                // createTask();
                ;
            } else if (option == 3) {
                handleCreateBonus();
            } else if (option == 4) {
            } else if (option == 5) {
                editBonus();
            } else if (option == 6) {
            } else if (option == 7) {
            } else if (option == 8) {
            } else if (option == 9) {
                isRunning = false;
            }
        }
    }

//    public void createTask() {
//
//        TaskDAO taskdao = new TaskDAO();
//        view.displayCreatingTask();
//        String name = view.askForInput();
//        String category = view.askForCategory();
//        String description = view.askForInput();
//        Date deadline = view.askForDeadline();
//        int points = 0;
//        try {
//            points = view.askForPoints();
//        }
//        catch (InputMismatchException e) {
//            System.err.println("You type wrong sign!");
//        }
//        Task task = new Task(name, category, description, deadline, points);
//
//        taskdao.addTask(task);
//
//    }

    public void handleCreateBonus() {
        Item item = isCreateBonus();

        if (item != null) {

            if (dbItemDAO.addItem(item)) {
                view.displayOperationSuccesfull();
            }
            else {
                view.displayOperationFailed();
            }
        }
        else {
            view.displayOperationFailed();
        }
    }

    public Item isCreateBonus() {
        view.clearConsole();

        view.displayCreatingItem();
        String name = view.askForString();

        int price = priceCheck();

        String category = view.askForItemCategory();

        view.displayUpdateDescription();
        String description = view.askForString();

        Item item = new Item(name, price, description, category);

        view.clearConsole();
        view.displayItemInfo(item);

        if (view.isUserAccept()) { return item; }
        else { return null; }
    }

    public void editBonus() {
        view.clearConsole();

        List<Item> items = dbItemDAO.getAllItemsInStore();
        view.displayItemsInStore(items);
        int id = view.askForInt();

        view.clearConsole();

        Item item = dbItemDAO.getItemBy(id);
        view.displayItemInfo(item);

        int updateOption = view.askForChange(item);
        handleUpdateBonus(updateOption, item);

    }

    public void handleUpdateBonus(int updateOption, Item item) {
        int UPDATE_NAME = 1;
        int UPDATE_PRICE = 2;
        int UPDATE_CATEGORY = 3;
        int UPDATE_DESCRIPTION = 4;

        if (updateOption == UPDATE_NAME) {
            view.displayUpdateName();
            item.setName(view.askForString());
        }
        else if (updateOption == UPDATE_PRICE) {
            view.displayUpdatePrice();
            item.setPrice(view.askForInt());
        }
        else if (updateOption == UPDATE_CATEGORY) {
            item.setCategory(view.askForItemCategory());
        }
        else if (updateOption == UPDATE_DESCRIPTION) {
            view.displayUpdateDescription();
            item.setDescription(view.askForString());
        }
        else {
            view.displayOperationFailed();
        }

        boolean isUpdate = dbItemDAO.updateItem(item);
        if (isUpdate) {
            view.displayOperationSuccesfull();
        }
    }

    public Integer priceCheck() {
        Integer price = 0;
        boolean incorrect = true;

        try {
            while(incorrect) {
                price = view.askForPrice();
                if (price instanceof Integer) {
                    incorrect = false;
                }
            }
        } catch (InputMismatchException e) {
            System.err.println("You type wrong sign!");
        }
        return price;
    }
}
