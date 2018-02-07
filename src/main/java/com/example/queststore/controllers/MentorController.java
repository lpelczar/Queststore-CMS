package com.example.queststore.controllers;


import com.example.queststore.dao.*;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.models.*;
import com.example.queststore.utils.InputGetter;
import com.example.queststore.views.MentorView;
import jdk.internal.util.xml.impl.Input;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class MentorController extends UserController {

    private MentorView view = new MentorView();
    private UserDAO dbUserDAO = new DbUserDAO();
    private ItemDAO dbItemDAO = new DbItemDAO();
    private StudentDataDAO dbStudentDataDAO = new DbStudentDataDAO();
    private GroupDAO dbGroupDAO = new DbGroupDAO();
    private TaskDAO dbTaskDAO = new DbTaskDAO();
    private StudentTaskDAO dbStudentTaskDAO = new DbStudentTaskDAO();
    private TeamController teamController = new TeamController();

    public void start() {
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
                addNewQuest();
            } else if (option == 4) {
                addNewItem();
            } else if (option == 5) {
                editQuest();
            } else if (option == 6) {
                editItem();
            } else if (option == 7) {
                markStudentAchievedQuest();
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

    private void addNewQuest() {
        String questName = view.getQuestNameInput();
        if (dbTaskDAO.getByName(questName) != null) {
            view.displayQuestAlreadyExists();
        } else {
            int points = view.getQuestPointsInput();
            String description = view.getQuestDescriptionInput();
            String categoryInput = view.getQuestCategory();
            String category = categoryInput.equals("b") ? "Basic" : "Extra";
            if (dbTaskDAO.add(new Task(questName, points, description, category))) {
                view.displayQuestSuccessfullyAdded();
            } else {
                view.displayErrorAddingQuest();
            }
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
        } else {
            view.displayOperationFailed();
        }
    }

    private void editQuest() {

        List<Entry> quests = new ArrayList<>(dbTaskDAO.getAll());
        view.displayEntriesNoInput(quests);
        if (quests.isEmpty()) {
            view.displayPressAnyKeyToContinueMessage();
            return;
        }
        String taskName = view.getQuestNameInput();
        if (dbTaskDAO.getByName(taskName) != null) {
            updateQuest(dbTaskDAO.getByName(taskName));
        } else {
            view.displayThereIsNoTaskWithThisName();
        }
    }

    private void updateQuest(Task task) {

        switch(view.getValueToUpdate(task)) {
            case "1":
                int points = view.askForPointsInput();
                task.setPoints(points);
                showEditResultMessage(dbTaskDAO.update(task));
                break;
            case "2":
                String description = view.askForDescriptionInput();
                task.setDescription(description);
                showEditResultMessage(dbTaskDAO.update(task));
                break;
            case "3":
                String categoryInput = view.getQuestCategory();
                String category = categoryInput.equals("b") ? "Basic" : "Extra";
                task.setCategory(category);
                showEditResultMessage(dbTaskDAO.update(task));
                break;
            default:
                view.displayWrongOptionMessage();
        }
    }

    private void showEditResultMessage(boolean isEdit) {
        if (isEdit) {
            view.displayValueHasBeenChanged();
        } else {
            view.displayErrorChangingTheValue();
        }
    }

    private void editItem() {
        view.clearConsole();
        List<Entry> items = new ArrayList<>(dbItemDAO.getAllItems());

        view.displayEntriesNoInput(items);
        if (items.isEmpty()) {
            view.displayPressAnyKeyToContinueMessage();
            return;
        }

        int id = view.getIdOfItem();
        Item item = dbItemDAO.getItemById(id);

        if (item != null) {
            int updateOption = view.askForPropetyToEdit(item);
            handleUpdateBonus(updateOption, item);
        }
    }

    private void handleUpdateBonus(int updateOption, Item item) {
        int UPDATE_NAME = 1;
        int UPDATE_PRICE = 2;
        int UPDATE_CATEGORY = 3;
        int UPDATE_DESCRIPTION = 4;

        if (updateOption == UPDATE_NAME) {
            view.displayUpdateName();
            item.setName(view.askForString());
        } else if (updateOption == UPDATE_PRICE) {
            view.displayUpdatePrice();
            item.setPrice(view.askForInt());
        } else if (updateOption == UPDATE_CATEGORY) {
            item.setCategory(view.askForItemCategory());
        } else if (updateOption == UPDATE_DESCRIPTION) {
            view.displayUpdateDescription();
            item.setDescription(view.askForString());
        } else {
            view.displayOperationFailed();
        }

        boolean isUpdate = dbItemDAO.updateItem(item);
        if (isUpdate) {
            view.displayOperationSuccessful();
        } else { view.displayOperationFailed();
    }

    private Integer priceCheck() {
        Integer price = 0;
        boolean incorrect = true;

        try {
            while (incorrect) {
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

    private void markStudentAchievedQuest() {

        List<Entry> students = new ArrayList<>(dbUserDAO.getAllByRole(UserEntry.STUDENT_ROLE));
        view.displayEntriesNoInput(students);
        if (students.isEmpty()) {
            view.displayPressAnyKeyToContinueMessage();
            return;
        }
        String studentLogin = view.getStudentLoginToMarkQuest();
        if (dbUserDAO.getByLoginAndRole(studentLogin, UserEntry.STUDENT_ROLE) != null) {
            choseQuestToMark(studentLogin);
        } else {
            view.displayThereIsNoStudentWithThisLogin();
        }
    }

    private void choseQuestToMark(String studentLogin) {

        List<Entry> quests = new ArrayList<>(dbTaskDAO.getAll());
        view.displayEntriesNoInput(quests);
        if (quests.isEmpty()) {
            view.displayPressAnyKeyToContinueMessage();
            return;
        }
        String taskName = view.getTaskNameInput();
        if (dbTaskDAO.getByName(taskName) != null) {
            Task task = dbTaskDAO.getByName(taskName);
            User student = dbUserDAO.getByLogin(studentLogin);
            boolean isAdded = dbStudentTaskDAO.add(student.getId(), task.getID());
            if (isAdded) {
                view.displayTaskConnectionAdded();
            } else {
                view.displayErrorAddingTaskConnection();
            }
        } else {
            view.displayThereIsNoGroupWithThisName();
        }

    }
}
