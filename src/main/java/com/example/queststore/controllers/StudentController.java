package main.java.com.example.queststore.controllers;


import views.StudentView;
import java.util.InputMismatchException;
import java.util.List;
import models.Item;
import models.StudentData;
import dao.DbItemDAO;
import dao.DbStudentDataDAO;

public class StudentController {
    private StudentData student;
    private StudentView view = new StudentView();
    private DbItemDAO dbItemDAO = new DbItemDAO();
    private DbStudentDataDAO dbStudentDataDAO = new DbStudentDataDAO();

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
        List<Item> backpack = dbItemDAO.getItemsBy(student_id);
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
        List<Item> items = dbItemDAO.getAllItemsInStore();

        if (items != null) {
            view.showItemsInStore(items);
            int item_id = view.askForInt();

            Item item = dbItemDAO.getItemBy(item_id);
            return item;

        } else {
            view.displayOperationFailed();
            return null;
        }
    }

    private boolean isStudentAffordToBuy(Item item) {
        int studentBalance = student.getBalance();
        int itemPrice = item.getPrice();

        if (studentBalance > itemPrice) {
            return true;
        }
        else {
            return false;
        }
    }

    private void updateStudentBackpack(int student_id, Item item) {
        boolean isItemSuccesfullAdded = dbStudentDataDAO.add(student_id, item);

        if (isItemSuccesfullAdded) {
            view.displayOperationSuccesfull();
        } else {
            view.displayOperationFailed();
        }
    }

    private void updateStudentBalance(Item item) {
        int transactionBalance = student.getBalance() - item.getPrice();
        student.setBalance(transactionBalance);
        dbStudentDataDAO.updateStudentData(student);
    }

    private StudentData getStudentDataBy(int student_id) {
        return dbStudentDataDAO.getStudentDataBy(student_id);
    }
}
