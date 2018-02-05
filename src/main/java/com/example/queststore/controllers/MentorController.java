package com.example.queststore.controllers;


import com.example.queststore.dao.*;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.models.*;
import com.example.queststore.views.MentorView;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class MentorController extends UserController {

    private MentorView view = new MentorView();
    private UserDAO dbUserDAO = new DbUserDAO();
    private ItemDAO dbItemDAO = new DbItemDAO();
    private StudentDataDAO dbStudentDataDAO = new DbStudentDataDAO();
    private GroupDAO dbGroupDAO = new DbGroupDAO();
    private TeamController teamController = new TeamController();

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
                addStudentToGroup();
            } else if (option == 3) {
//                addNewQuest();
            } else if (option == 4) {
                addNewItem();
            } else if (option == 5) {
//                editQuest();
            } else if (option == 6) {
                editItem();
            } else if (option == 7) {
//                markStudentQuest();
            } else if (option == 8) {
//                markStudentItem();
            } else if (option == 9) {
//                showStudentSummary();
            } else if (option == 10) {
                teamController.hadnleRerollStudentsTeams();
            } else if (option == 11) {
                isAppRunning = false;
            }
        }
    }

    @Override
    void promote(User user) {

        user.setRole(UserEntry.STUDENT_ROLE);
        boolean isPromoted = dbUserDAO.update(user);
        StudentData student = createStudent(user);
        dbStudentDataDAO.add(student);
        if (isPromoted) {
            view.displayHasBeenPromoted();
        } else {
            view.displayUserNotExists();
        }
    }

    private void addStudentToGroup() {

        List<Entry> students = new ArrayList<>(dbUserDAO.getAllByRole(UserEntry.STUDENT_ROLE));
        view.displayEntriesNoInput(students);
        if (students.isEmpty()) {
            view.displayPressAnyKeyToContinueMessage();
            return;
        }
        String studentLogin = view.getStudentLoginToAssignGroup();
        if (dbUserDAO.getByLoginAndRole(studentLogin, UserEntry.STUDENT_ROLE) != null) {
            choseGroupAndAssignToStudent(studentLogin);
        } else {
            view.displayThereIsNoStudentWithThisLogin();
        }
    }

    private void choseGroupAndAssignToStudent(String studentLogin) {

        List<Entry> groups = new ArrayList<>(dbGroupDAO.getAll());
        view.displayEntriesNoInput(groups);
        if (groups.isEmpty()) {
            view.displayPressAnyKeyToContinueMessage();
            return;
        }
        String groupName = view.getGroupNameInput();
        if (dbGroupDAO.getByName(groupName) != null) {
            Group group = dbGroupDAO.getByName(groupName);
            User student = dbUserDAO.getByLogin(studentLogin);
            StudentData studentData = dbStudentDataDAO.getStudentDataBy(student.getId());
            studentData.setGroupId(group.getId());
            boolean isUpdated = dbStudentDataDAO.updateStudentData(studentData);
            if (isUpdated) {
                view.displayGroupUpdated();
            } else {
                view.displayErrorUpdatingGroup();
            }
        } else {
            view.displayThereIsNoGroupWithThisName();
        }
    }

    private void addNewItem() {
        DbItemDAO dbItemDAO = new DbItemDAO();
    
        view.clearConsole();
        view.displayCreatingItem();
        String name = view.askForString();

        int price = priceCheck();

        String category = view.askForItemCategory();

        view.displayUpdateDescription();
        String description = view.askForString();

        Item item = new Item(name, price, description, category);

        if (dbItemDAO.addItem(item)) {
            view.displayOperationSuccessful();
        }
        else {
            view.displayOperationFailed();
        }
    }

  private void editItem() {
        view.clearConsole();

        List<Item> items = dbItemDAO.getAllItems();
        view.displayItemsInStore(items);
        int id = view.askForInt();

        view.clearConsole();

        Item item = dbItemDAO.getItemById(id);
        view.displayItemInfo(item);

        int updateOption = view.askForChange(item);
        handleUpdateBonus(updateOption, item);

    }

    private void handleUpdateBonus(int updateOption, Item item) {
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
            view.displayOperationSuccessful();
        }
    }

    private Integer priceCheck() {
        Integer price = 0;
        boolean incorrect = true;

        try {
            while(incorrect) {
                price = view.askForPrice();
                if (price != null) {
                    incorrect = false;
                }
            }
        } catch (InputMismatchException e) {
            System.err.println("You type wrong sign!");
        }
        return price;
    }
}
