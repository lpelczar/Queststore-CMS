package controllers;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import data.contracts.UserContract.UserEntry;
import dao.DbUserDAO;
import dao.UserDAO;
import models.Entry;
import models.User;
import views.MentorView;
import dao.DbItemDAO;
import models.Item;

public class MentorController {

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
//                promoteBlankUser();
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

    private int priceCheck() {
        int price;

        try {
            while(!scanner.hasNextInt()) {
                price = askForPrice();
            }
        } catch (InputMismatchException e) {
            System.err.println("You type wrong sign!");
        }
    }
}
