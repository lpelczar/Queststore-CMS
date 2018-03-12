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

    private StudentView studentView;
    private StudentDataDAO studentDataDAO;
    private StudentItemDAO studentItemDAO;
    private ExpLevelsDAO expLevelsDAO;
    private UserDAO userDAO;
    private ItemDAO itemDAO;

    public StudentService(StudentDataDAO studentDataDAO, StudentItemDAO studentItemDAO, ExpLevelsDAO expLevelsDAO,
                          UserDAO userDAO, ItemDAO itemDAO, StudentView studentView) {
        this.studentDataDAO = studentDataDAO;
        this.studentItemDAO = studentItemDAO;
        this.expLevelsDAO = expLevelsDAO;
        this.userDAO = userDAO;
        this.itemDAO = itemDAO;
        this.studentView = studentView;
    }

    public void updateStudentBalance(int studentId, int points) {
        StudentData studentData = studentDataDAO.getStudentDataByStudentId(studentId);
        studentData.setBalance(studentData.getBalance() + points);
    }

    public void updateStudentExperienceAndLevel(int studentId, int points) {
        StudentData studentData = studentDataDAO.getStudentDataByStudentId(studentId);
        studentData.setExperience(studentData.getExperience() + points);
        studentDataDAO.updateStudentData(studentData);
        updateStudentLevel(studentData);
    }

    private void updateStudentLevel(StudentData studentData) {
        List<ExpLevel> availableLevels = expLevelsDAO.getAll();
        availableLevels.sort(Comparator.comparing(ExpLevel::getValue));
        for (ExpLevel level : availableLevels) {
            if (studentData.getExperience() < level.getValue()) {
                studentData.setLevel(level.getName());
                break;
            }
        }
        if (studentDataDAO.updateStudentData(studentData)) {
            studentView.displayStudentDataHasBeenUpdated();
        } else {
            studentView.displayErrorUpdatingStudentData();
        }
    }

    public void showStudentSummary() {
        studentView.clearConsole();

        List<User> students = userDAO.getAllByRole(UserEntry.STUDENT_ROLE);
        if (students != null) {

            for (User user : students) {
                List<String> studentInfo = new ArrayList<>();

                StudentData student = studentDataDAO.getStudentDataByStudentId(user.getId());
                studentInfo.add(user.getName());
                studentInfo.add(student.getBalance().toString());

                List<Item> studentItems = itemDAO.getItemsByStudentId(student.getId());
                if (studentItems != null) {
                    studentView.displayStudentInfo(studentInfo, studentItems);

                }
            }
            studentView.displayPressAnyKeyToContinueMessage();
        } else { studentView.displayNoStudents(); }
    }

    public void showStudentBackPack(int studentId) {
        List<Item> backpack = itemDAO.getItemsByStudentId(studentId);
        studentView.displayStudentBackpack(backpack);
    }

    public void buyArtifact(int studentId) {
        Item item = chooseItemToBuy(ItemEntry.BASIC_ITEM);

        if (item != null) {

            if (!isStudentContainItem(studentId, item.getId())) {
                int price = item.getPrice();

                if (isStudentAffordToBuy(studentId, price)) {
                    updateStudentBackpack(studentId, item);
                    StudentData studentData = studentDataDAO.getStudentDataByStudentId(studentId);
                    updateStudentBalance(price, studentData);

                } else { studentView.displayNoMoney(); }
            } else { studentView.displayItemAlreadyContaining(); }
        }
    }

    private Item chooseItemToBuy(String category) {
        List<Item> items = itemDAO.getItemsByCategory(category);

        if (items != null) {
            int itemId = studentView.chooseItemFrom(items);

            if (checkIfIdItemInStore(itemId, items)) {
                return itemDAO.getItemById(itemId);

            } else { studentView.displayWrongId(); return null; }
        } else {
            studentView.displayOperationFailed();
            return null;
        }
    }

    private boolean checkIfIdItemInStore(int itemId, List<Item> items) {
        for (Item item : items) {
            if (itemId == item.getId()) { return true; }
            }
            return false;
        }

    private boolean isStudentAffordToBuy(int studentId, int price) {
        StudentData studentData = studentDataDAO.getStudentDataByStudentId(studentId);
        int studentBalance = studentData.getBalance();
        return studentBalance > price;
    }

    public void buyArtifactForTeam(int studentId) {
        StudentData studentData = studentDataDAO.getStudentDataByStudentId(studentId);
        if (studentData.getTeamName().isEmpty()) {
            studentView.displayStudentHaveNoTeamAssignedMessage();
            return;
        }
        List<StudentData> team = studentDataDAO.getStudentsDataByTeamName(studentData.getTeamName());
        Item item = chooseItemToBuy(ItemEntry.EXTRA_ITEM);

        if (item != null && team != null) {

            if (!isTeamMemberContainItem(team, item.getId())) {
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
        if (studentItemDAO.add(studentId, item.getId(), StudentItemEntry.IS_NOT_USED_VALUE)) {
            studentView.displayOperationSuccessfullyCompleted();
        } else {
            studentView.displayOperationFailed();
        }
    }

    private void updateStudentBalance(int price, StudentData student) {
        int transactionBalance = student.getBalance() - price;
        student.setBalance(transactionBalance);
        studentDataDAO.updateStudentData(student);
    }

    private boolean isStudentContainItem(int studentId, int itemID) {
        List<Integer> studentsItems = studentItemDAO.getStudentItemsIdsBy(studentId);
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
