package com.example.queststore.services;

import com.example.queststore.dao.*;
import com.example.queststore.data.contracts.ItemEntry;
import com.example.queststore.data.contracts.StudentItemEntry;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.models.ExpLevel;
import com.example.queststore.models.Item;
import com.example.queststore.models.StudentData;
import com.example.queststore.models.User;
import com.example.queststore.views.StudentView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentService {

    private StudentData student;
    private StudentView studentView = new StudentView();
    private StudentDataDAO dbStudentDataDAO = new DbStudentDataDAO();
    private StudentItemDAO dbStudentItemDAO = new DbStudentItemDAO();
    private ExpLevelsDAO dbExpLevelsDAO = new DbExpLevelsDAO();
    private UserDAO dbUserDAO = new DbUserDAO();
    private ItemDAO dbItemDAO = new DbItemDAO();

    public StudentService(){}

    public StudentService(StudentData studentData) {
        this.student = studentData;
    }

    public void updateStudentBalance(int studentId, int points) {
        StudentData studentData = dbStudentDataDAO.getStudentDataBy(studentId);
        studentData.setBalance(studentData.getBalance() + points);
    }

    public void updateStudentExperienceAndLevel(int studentId, int points) {
        StudentData studentData = dbStudentDataDAO.getStudentDataBy(studentId);
        studentData.setExperience(studentData.getExperience() + points);
        dbStudentDataDAO.updateStudentData(studentData);
        updateStudentLevel(studentData);
    }

    private void updateStudentLevel(StudentData studentData) {
        List<ExpLevel> availableLevels = dbExpLevelsDAO.getAll();
        availableLevels.sort(Comparator.comparing(ExpLevel::getValue));
        for (ExpLevel level : availableLevels) {
            if (studentData.getExperience() < level.getValue()) {
                studentData.setLevel(level.getName());
                break;
            }
        }
        if (dbStudentDataDAO.updateStudentData(studentData)) {
            studentView.displayStudentDataHasBeenUpdated();
        } else {
            studentView.displayErrorUpdatingStudentData();
        }
    }

    public void showStudentSummary() {
        List<String> studentInfo = new ArrayList<>();

        List<User> students = dbUserDAO.getAllByRole(UserEntry.STUDENT_ROLE);
        if (students != null) {

            for (User user : students) {

                StudentData student = dbStudentDataDAO.getStudentDataBy(user.getId());
                studentInfo.add(user.getName());
                studentInfo.add(student.getBalance().toString());

                List<Item> studentItems = dbItemDAO.getItemsByStudentId(student.getId());
                if (studentItems != null) {
                    studentView.displayStudentInfo(studentInfo, studentItems);

                } else { studentView.displayNoItems(); }
            }
        } else { studentView.displayNoStudents(); }
    }

    public void showStudentBackPack(int studentId) {
        List<Item> backpack = dbItemDAO.getItemsByStudentId(studentId);
        studentView.displayStudentBackpack(backpack);
    }

    public void buyArtifact(int studentId) {
        Item item = chooseItemToBuy(ItemEntry.BASIC_ITEM);

        if (item != null) {

            if (!isStudentContainItem(studentId, item.getID())) {
                int price = item.getPrice();

                if (isStudentAffordToBuy(price)) {
                    updateStudentBackpack(studentId, item);
                    updateStudentBalance(price, student);

                } else { studentView.displayNoMoney(); }
            } else { studentView.displayItemAlreadyContaining(); }
        }
    }

    private Item chooseItemToBuy(String category) {
        List<Item> items = dbItemDAO.getItemsByCategory(category);

        if (items != null) {
            studentView.showItemsInStore(items);
            int item_id = studentView.askForInt();

            return dbItemDAO.getItemById(item_id);

        } else {
            studentView.displayOperationFailed();
            return null;
        }
    }

    private boolean isStudentAffordToBuy(int price) {
        int studentBalance = student.getBalance();
        return studentBalance > price;
    }

    public void buyArtifactForTeam() {
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

                } else { studentView.displayNoMoney(); }
            } else { studentView.displayItemAlreadyContaining(); }
        }
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
            studentView.displayOperationSuccessfull();
        } else {
            studentView.displayOperationFailed();
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
}
