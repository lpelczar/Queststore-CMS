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
    private ItemDAO dbItemDAO = new DbItemDAO();
    private StudentDataDAO dbStudentDataDAO = new DbStudentDataDAO();
    private StudentItemDAO dbStudentItemDAO = new DbStudentItemDAO();

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
                buyArtifactForTeam();
            } else if (option == 4) {
                isLoopEnd = true;
            }
        }
    }

    private void showStudentBackPack(int student_id) {
        List<Item> backpack = dbItemDAO.getItemsByStudentId(student_id);
        view.displayStudentBackpack(backpack);
    }

    private void buyArtifact(int student_id) {
        final String CATEGORY = "basic";
        Item item = chooseItemToBuy(CATEGORY);

        if (item != null) {

            if (!isStudentContainItem(student_id, item.getID())) {
                int price = item.getPrice();

                if (isStudentAffordToBuy(price)) {
                    updateStudentBackpack(student_id, item);
                    updateStudentBalance(price, student);

                } else { view.displayNoMoney(); }
            } else { view.displayItemAlreadyContaining(); }
        }
    }

    private void buyArtifactForTeam() {
        List<StudentData> team = dbStudentDataDAO.getStudentsInSameTeamBy(student.getTeamName());
        final String CATEGORY = "advanced";
        Item item = chooseItemToBuy(CATEGORY);

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
            view.showItemsInStore(items);
            int item_id = view.askForInt();

            return dbItemDAO.getItemById(item_id);

        } else {
            view.displayOperationFailed();
            return null;
        }
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
        if (dbStudentItemDAO.add(studentId, item.getID())) {
            view.displayOperationSuccesfull();
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
