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
            } else if (option == 2) {
                // createTask();
                ;
            } else if (option == 3) {
                createItem();
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

    public void createItem() {
        view.clearConsole();

        view.displayCreatingItem();
        String name = view.askForInput();
        int price = priceCheck();
        String description = view.askForInput();
        String category = view.askForItemCategory();

        Item item = new Item(name, price, description, category);

        if (dbItemDAO.addItem(item)) {
            view.displayOperationSuccesfull();
        }
        else {
            view.displayOperationFailed();
        }
    }

    public void editBonus() {
        view.clearConsole();

        List<Item> items = dbItemDAO.getAllItemsInStore();
        view.displayItemsInStore(items);
        int id = view.askForInt();

        view.clearConsole();

        Item item = dbItemDAO.getItemBy(id);
        view.displayItemInfo(item);

        int changeOption = view.askForChange();
        handleChangeItem(changeOption, item);


    }

    public void handleChangeItem(int changeOption, Item item) {

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
