package com.example.queststore.controllers;


import com.example.queststore.dao.*;
import com.example.queststore.models.Item;
import com.example.queststore.models.StudentData;
import com.example.queststore.views.StudentView;

import java.util.InputMismatchException;
import java.util.List;

public class StudentController {
    private StudentData student;
    private StudentView view = new StudentView();
    private ItemDAO ItemDAO = new DbItemDAO();
    private StudentDataDAO StudentDataDAO = new DbStudentDataDAO();
    private StudentItemDAO StudentItemDAO = new DbStudentItemDAO();

    public void start(int student_id) {
        student = getStudentDataBy(student_id);
        boolean isLoopEnd = false;
        int option = 0;

        while (!isLoopEnd) {
            view.displayInfoBar(student.getBalance(), student.getLevel());
            view.handleStudentMenu();

            try {
                option = view.askForOption();
            }
            catch (InputMismatchException e) {
                System.err.println("You type wrong sign!");
            }
            if (option == 1) {
                showStudentBackPack(student_id);
            } else if (option == 2) {
                buyArtifact(student_id);
            } else if (option == 3) {
            } else if (option == 4) {
                showStudentLevel();
            } else if (option == 5) {
                isLoopEnd = true;
            }
        }
    }

    private void showStudentBackPack(int student_id) {
        List<Item> backpack = ItemDAO.getItemsByStudentId(student_id);
        view.displayStudentBackpack(backpack);
    }

    private void showStudentLevel() {
        String level = student.getLevel();
        view.displayStudentLevel(level);
    }

    private void buyArtifact(int student_id) {
        Item item = chooseItemToBuy();

        if (item != null) {

            if (isStudentAffordToBuy(item)) {
                updateStudentBackpack(student_id, item);
                updateStudentBalance(item);

            } else {
                view.displayNoMoney();
            }
        }
    }

    private Item chooseItemToBuy() {
        List<Item> items = ItemDAO.getAllItems();

        if (items != null) {
            view.showItemsInStore(items);
            int item_id = view.askForInt();

            return ItemDAO.getItemById(item_id);

        } else {
            view.displayOperationFailed();
            return null;
        }
    }

    private boolean isStudentAffordToBuy(Item item) {
        int studentBalance = student.getBalance();
        int itemPrice = item.getPrice();

        return studentBalance > itemPrice;
    }

    private void updateStudentBackpack(int student_id, Item item) {
        if (StudentItemDAO.add(student_id, item.getID())) {
            view.displayOperationSuccesfull();
        } else {
            view.displayOperationFailed();
        }
    }

    private void updateStudentBalance(Item item) {
        int transactionBalance = student.getBalance() - item.getPrice();
        student.setBalance(transactionBalance);
        StudentDataDAO.updateStudentData(student);
    }

    private StudentData getStudentDataBy(int student_id) {
        return StudentDataDAO.getStudentDataBy(student_id);
    }
}
