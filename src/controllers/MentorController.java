package controllers;


import java.util.InputMismatchException;

import data.contracts.UserContract.UserEntry;
import dao.DbUserDAO;
import dao.UserDAO;
import models.User;
import views.MentorView;
import dao.DbItemDAO;
import models.Item;

public class MentorController extends UserController {

    private MentorView view = new MentorView();
    private UserDAO dbUserDAO = new DbUserDAO();

    public void start(){
        int option;
        boolean isAppRunning = true;

        while (isAppRunning) {
            view.clearConsole();
            view.handleMentorMenu();
            option = view.askForOption();

            if (option == 1) {
                promoteBlankUser();
            } else if (option == 2) {
//                addStudentToGroup();
            } else if (option == 3) {
//                addNewQuest();
            } else if (option == 4) {
//                addNewItem();
            } else if (option == 5) {
//                editQuest();
            } else if (option == 6) {
//                editItem();
            } else if (option == 7) {
//                markStudentQuest();
            } else if (option == 8) {
//                markStudentItem();
            } else if (option == 9) {
//                showStudentSummary();
            } else if (option == 10) {
                isAppRunning = false;
            }
        }
    }

    @Override
    void promote(User user) {

        user.setRole(UserEntry.STUDENT_ROLE);
        boolean isPromoted = dbUserDAO.update(user);

        if (isPromoted) {
            view.displayHasBeenPromoted();
        } else {
            view.displayUserNotExists();
        }
    }

    private void createItem() {
        DbItemDAO dbItemDAO = new DbItemDAO();
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
