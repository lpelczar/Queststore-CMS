package com.example.queststore.controllers;


import com.example.queststore.dao.*;
import com.example.queststore.data.contracts.ItemEntry;
import com.example.queststore.data.contracts.StudentItemEntry;
import com.example.queststore.models.Item;
import com.example.queststore.models.StudentData;
import com.example.queststore.views.StudentView;

import java.util.InputMismatchException;
import java.util.List;

public class StudentController {
    private StudentData student;
    private StudentView view = new StudentView();
    private ItemDAO dbItemDAO = new DbItemDAO();
    private StudentDataDAO dbStudentDataDAO = new DbStudentDataDAO();
    private StudentItemDAO dbStudentItemDAO = new DbStudentItemDAO();

    public void start(int studentId) {
        student = getStudentDataBy(studentId);
        boolean isLoopEnd = false;
        int option = 0;

        while (!isLoopEnd) {
            view.displayInfoBar(student.getBalance(), student.getLevel());
            view.handleStudentMenu();

            try {
                option = view.askForOption();
            }
            catch (InputMismatchException e) {
                System.err.println("Wrong option!");
            }
            switch (option) {
                case 1:
                    showStudentBackPack(studentId);
                    break;
                case 2:
                    buyArtifact(studentId);
                    break;
                case 3:
                    buyArtifactForTeam();
                    break;
                case 4:
                    isLoopEnd = true;
            }
        }
    }

    private void showStudentBackPack(int studentId) {
        List<Item> backpack = dbItemDAO.getItemsByStudentId(studentId);
        view.displayStudentBackpack(backpack);
    }

    private void buyArtifact(int studentId) {
        Item item = chooseItemToBuy(ItemEntry.BASIC_ITEM);

        if (item != null) {

            if (!isStudentContainItem(studentId, item.getID())) {
                int price = item.getPrice();

                if (isStudentAffordToBuy(price)) {
                    updateStudentBackpack(studentId, item);
                    updateStudentBalance(price, student);

                } else { view.displayNoMoney(); }
            } else { view.displayItemAlreadyContaining(); }
        }
    }

    private void buyArtifactForTeam() {
        List<StudentData> team = dbStudentDataDAO.getStudentsInSameTeamBy(student.getTeamName());
        Item item = chooseItemToBuy(ItemEntry.EXTRA_ITEM);

        if (item != null && team != null) {

            if (!isTeamMemberContainItem(team, item.getID())) {
                int priceForEachStudent = item.getPrice() / team.size();

                if (isTeamAffordToBuy(priceForEachStudent, team)) {

                    for (StudentData member : team) {
                        updateStudentBackpack(member.getId(), item);
                        updateStudentBalance(priceForEachStudent, member);
                    }

                } else { view.displayNoMoney(); }
            } else { view.displayItemAlreadyContaining(); }
        }
    }

    private Item chooseItemToBuy(String category) {
        List<Item> items = dbItemDAO.getItemsByCategory(category);

        if (items != null) {
            int itemId = view.chooseItemFrom(items);

            if (checkIfIdItemInStore(itemId, items)) {
                return dbItemDAO.getItemById(itemId);

            } else { view.displayWrongId(); return null; }

        } else {
            view.displayOperationFailed();
            return null;
        }
    }

    private boolean checkIfIdItemInStore(int itemId, List<Item> items) {
        for (Item item : items) {
            if (itemId == item.getID()) { return true; }
        }
        return false;
    }

    private boolean isStudentAffordToBuy(int price) {
        int studentBalance = student.getBalance();
        return studentBalance > price;
    }

    private boolean isTeamAffordToBuy(int itemPriceForEachStudent, List<StudentData> team) {

        for (StudentData student : team) {
            if (student.getBalance() < itemPriceForEachStudent) {
                return false;
            }
        }
        return true;
    }

    private void updateStudentBackpack(int studentId, Item item) {
        if (dbStudentItemDAO.add(studentId, item.getID(), StudentItemEntry.IS_NOT_USED_VALUE)) {
            view.displayOperationSuccessfull();
        } else {
            view.displayOperationFailed();
        }
    }

    private void updateStudentBalance(int price, StudentData student) {
        int transactionBalance = student.getBalance() - price;
        student.setBalance(transactionBalance);
        dbStudentDataDAO.updateStudentData(student);
    }

    private boolean isStudentContainItem(int studentId, int itemID) {
        List<Integer> studentsItems = dbStudentItemDAO.getStudentsItemsBy(studentId);
        for (int studentItemID : studentsItems) {
            if (itemID == studentItemID) {
                return true;
            }
        }
        return false;
    }

    private boolean isTeamMemberContainItem(List<StudentData> team, int itemID) {
        for (StudentData member : team) {
            if (isStudentContainItem(member.getId(), itemID)) {
                return true;
            }
        }
        return false;
    }

    private StudentData getStudentDataBy(int student_id) {
        return dbStudentDataDAO.getStudentDataBy(student_id);
    }
}
