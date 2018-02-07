package com.example.queststore.controllers;


import com.example.queststore.dao.*;
import com.example.queststore.data.contracts.TaskEntry;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.models.*;
import com.example.queststore.views.MentorView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MentorController extends UserController {

    final String BASIC_TASK = "b";

    private MentorView view = new MentorView();
    private UserDAO dbUserDAO = new DbUserDAO();
    private ItemDAO dbItemDAO = new DbItemDAO();
    private StudentDataDAO dbStudentDataDAO = new DbStudentDataDAO();
    private GroupDAO dbGroupDAO = new DbGroupDAO();
    private TaskDAO dbTaskDAO = new DbTaskDAO();
    private StudentItemDAO dbStudentItemDAO = new DbStudentItemDAO();
    private StudentTaskDAO dbStudentTaskDAO = new DbStudentTaskDAO();
    private ExpLevelsDAO dbExpLevelsDAO = new DbExpLevelsDAO();
    private TeamController teamController = new TeamController();

    public void start() {
        int option;
        boolean isAppRunning = true;

        while (isAppRunning) {
            view.clearConsole();
            view.handleMentorMenu();
            option = view.askForOption();

            switch (option) {
                case 1:
                    promoteBlankUser();
                    break;
                case 2:
                    addStudentToGroup();
                    break;
                case 3:
                    addNewQuest();
                    break;
                case 4:
                    addNewItem();
                    break;
                case 5:
                    editQuest();
                    break;
                case 6:
                    editItem();
                    break;
                case 7:
                    markStudentAchievedQuest();
                    break;
                case 8:
                    markStudentUsedItem();
                    break;
                case 9:
                    showStudentSummary();
                    break;
                case 10:
                    teamController.handleRerollStudentsTeams();
                    break;
                case 11:
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

    private void showStudentSummary() {
        List<String> studentInfo = new ArrayList<>();

        List<User> students = dbUserDAO.getAllByRole(UserEntry.STUDENT_ROLE);
        if (students != null) {

            for (User user : students) {

                StudentData student = dbStudentDataDAO.getStudentDataBy(user.getId());
                studentInfo.add(user.getName());
                studentInfo.add(student.getBalance().toString());

                List<Item> studentItems = dbItemDAO.getItemsByStudentId(student.getId());
                if (studentItems != null) {
                    view.displayStudentInfo(studentInfo, studentItems);

                } else { view.displayNoItems(); }
            }
        } else { view.displayNoStudents(); }
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
            String category = categoryInput.equals(BASIC_TASK) ? TaskEntry.BASIC_TASK : TaskEntry.EXTRA_TASK;
            if (dbTaskDAO.add(new Task(questName, points, description, category))) {
                view.displayQuestSuccessfullyAdded();
            } else {
                view.displayErrorAddingQuest();
            }
        }
    }

    private void addNewItem() {
        DbItemDAO dbItemDAO = new DbItemDAO();

        view.displayCreatingItem();

        String name = view.displayGetName();
        int price = view.displayGetPrice();
        String category = view.askForItemCategory();
        String description = view.displayGetDescription();

        Item item = new Item(name, price, description, category);

        if (dbItemDAO.addItem(item)) {
            view.displayItemHasBeenAdded();
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
        final String UPDATE_POINTS = "1";
        final String UPDATE_DESCRIPTION = "2";
        final String UPDATE_CATEGORY = "3";

        switch(view.getValueToUpdate(task)) {
            case UPDATE_POINTS:
                int points = view.askForPointsInput();
                task.setPoints(points);
                showEditResultMessage(dbTaskDAO.update(task));
                break;
            case UPDATE_DESCRIPTION:
                String description = view.askForDescriptionInput();
                task.setDescription(description);
                showEditResultMessage(dbTaskDAO.update(task));
                break;
            case UPDATE_CATEGORY:
                String categoryInput = view.getQuestCategory();
                String category = categoryInput.equals(BASIC_TASK) ? TaskEntry.BASIC_TASK : TaskEntry.EXTRA_TASK;
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
            view.displayNoItems();
            return;
        }

        int id = view.getIdOfItem();
        Item item = dbItemDAO.getItemById(id);

        if (item != null) {
            int updateOption = view.askForPropertyToEdit(item);
            handleUpdateBonus(updateOption, item);
        }
    }

    private void handleUpdateBonus(int updateOption, Item item) {
        int UPDATE_NAME = 1;
        int UPDATE_PRICE = 2;
        int UPDATE_CATEGORY = 3;
        int UPDATE_DESCRIPTION = 4;

        if (updateOption == UPDATE_NAME) {
            item.setName(view.displayGetName());

        } else if (updateOption == UPDATE_PRICE) {
            item.setPrice(view.displayGetPrice());

        } else if (updateOption == UPDATE_CATEGORY) {
            item.setCategory(view.askForItemCategory());

        } else if (updateOption == UPDATE_DESCRIPTION) {
            item.setDescription(view.displayGetDescription());

        } else {
            view.displayOperationFailed();
            return;
        }

        boolean isUpdate = dbItemDAO.updateItem(item);
        if (isUpdate) {
            view.displayItemHasBeenAdded();

        } else {
            view.displayOperationFailed();
        }
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
                updateStudentBalance(student.getId(), task.getPoints());
                updateStudentExperienceAndLevel(student.getId(), task.getPoints());
            } else {
                view.displayErrorAddingTaskConnection();
            }
        } else {
            view.displayThereIsNoGroupWithThisName();
        }
    }

    private void updateStudentBalance(int studentId, int points) {
        StudentData studentData = dbStudentDataDAO.getStudentDataBy(studentId);
        studentData.setBalance(studentData.getBalance() + points);
    }

    private void updateStudentExperienceAndLevel(int studentId, int points) {
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
            view.displayStudentDataHasBeenUpdated();
        } else {
            view.displayErrorUpdatingStudentData();
        }
    }

    private void markStudentUsedItem() {
        List<Entry> students = new ArrayList<>(dbUserDAO.getAllByRole(UserEntry.STUDENT_ROLE));
        view.displayEntriesNoInput(students);
        if (students.isEmpty()) {
            view.displayPressAnyKeyToContinueMessage();
            return;
        }
        String studentLogin = view.getStudentLoginToMarkArtifact();
        if (dbUserDAO.getByLoginAndRole(studentLogin, UserEntry.STUDENT_ROLE) != null) {
            choseArtifactToMark(studentLogin);
        } else {
            view.displayThereIsNoStudentWithThisLogin();
        }
    }

    private void choseArtifactToMark(String studentLogin) {
        User student = dbUserDAO.getByLogin(studentLogin);
        List<Entry> items = new ArrayList<>(dbItemDAO.getItemsByStudentId(student.getId()));
        view.displayEntriesNoInput(items);
        if (items.isEmpty()) {
            view.displayPressAnyKeyToContinueMessage();
            return;
        }
        String itemName = view.getTaskNameInput();
        if (dbItemDAO.getItemByName(itemName) != null) {
            Item item = dbItemDAO.getItemByName(itemName);
            if (dbStudentItemDAO.markItemAsUsed(student.getId(), item.getID())) {
                view.displayItemHasBeenMarked();
            } else {
                view.displayErrorMarkingItem();
            }
        }
    }
}
