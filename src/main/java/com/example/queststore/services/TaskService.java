package com.example.queststore.services;

import com.example.queststore.dao.*;
import com.example.queststore.data.contracts.TaskEntry;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.models.Entry;
import com.example.queststore.models.Task;
import com.example.queststore.models.User;
import com.example.queststore.views.TaskView;

import java.util.ArrayList;
import java.util.List;

public class TaskService {

    private final String BASIC_TASK = "b";
    private TaskView taskView = new TaskView();
    private TaskDAO dbTaskDAO = new DbTaskDAO();
    private UserDAO dbUserDAO = new DbUserDAO();
    private StudentTaskDAO dbStudentTaskDAO = new DbStudentTaskDAO();
    private StudentService studentService = new StudentService();

    public void addNewQuest() {
        String questName = taskView.getQuestNameInput();
        if (dbTaskDAO.getByName(questName) != null) {
            taskView.displayQuestAlreadyExists();
        } else {
            int points = taskView.getQuestPointsInput();
            String description = taskView.getQuestDescriptionInput();
            String categoryInput = taskView.getQuestCategory();
            String category = categoryInput.equals(BASIC_TASK) ? TaskEntry.BASIC_TASK : TaskEntry.EXTRA_TASK;
            if (dbTaskDAO.add(new Task(questName, points, description, category))) {
                taskView.displayQuestSuccessfullyAdded();
            } else {
                taskView.displayErrorAddingQuest();
            }
        }
    }

    public void editQuest() {

        List<Entry> quests = new ArrayList<>(dbTaskDAO.getAll());
        taskView.displayEntriesNoInput(quests);
        if (quests.isEmpty()) {
            taskView.displayPressAnyKeyToContinueMessage();
            return;
        }
        String taskName = taskView.getQuestNameInput();
        if (dbTaskDAO.getByName(taskName) != null) {
            updateQuest(dbTaskDAO.getByName(taskName));
        } else {
            taskView.displayThereIsNoTaskWithThisName();
        }
    }

    private void updateQuest(Task task) {
        final String UPDATE_POINTS = "1";
        final String UPDATE_DESCRIPTION = "2";
        final String UPDATE_CATEGORY = "3";

        switch(taskView.getValueToUpdate(task)) {
            case UPDATE_POINTS:
                int points = taskView.askForPointsInput();
                task.setPoints(points);
                showEditResultMessage(dbTaskDAO.update(task));
                break;
            case UPDATE_DESCRIPTION:
                String description = taskView.askForDescriptionInput();
                task.setDescription(description);
                showEditResultMessage(dbTaskDAO.update(task));
                break;
            case UPDATE_CATEGORY:
                String categoryInput = taskView.getQuestCategory();
                String category = categoryInput.equals(BASIC_TASK) ? TaskEntry.BASIC_TASK : TaskEntry.EXTRA_TASK;
                task.setCategory(category);
                showEditResultMessage(dbTaskDAO.update(task));
                break;
            default:
                taskView.displayWrongOptionMessage();
        }
    }

    private void showEditResultMessage(boolean isEdit) {
        if (isEdit) {
            taskView.displayValueHasBeenChanged();
        } else {
            taskView.displayErrorChangingTheValue();
        }
    }

    public void markStudentAchievedQuest() {

        List<Entry> students = new ArrayList<>(dbUserDAO.getAllByRole(UserEntry.STUDENT_ROLE));
        taskView.displayEntriesNoInput(students);
        if (students.isEmpty()) {
            taskView.displayPressAnyKeyToContinueMessage();
            return;
        }
        String studentLogin = taskView.getStudentLoginToMarkQuest();
        if (dbUserDAO.getByLoginAndRole(studentLogin, UserEntry.STUDENT_ROLE) != null) {
            choseQuestToMark(studentLogin);
        } else {
            taskView.displayThereIsNoStudentWithThisLogin();
        }
    }

    private void choseQuestToMark(String studentLogin) {

        List<Entry> quests = new ArrayList<>(dbTaskDAO.getAll());
        taskView.displayEntriesNoInput(quests);
        if (quests.isEmpty()) {
            taskView.displayPressAnyKeyToContinueMessage();
            return;
        }
        String taskName = taskView.getTaskNameInput();
        if (dbTaskDAO.getByName(taskName) != null) {
            Task task = dbTaskDAO.getByName(taskName);
            User student = dbUserDAO.getByLogin(studentLogin);
            boolean isAdded = dbStudentTaskDAO.add(student.getId(), task.getID());
            if (isAdded) {
                taskView.displayTaskConnectionAdded();
                studentService.updateStudentBalance(student.getId(), task.getPoints());
                studentService.updateStudentExperienceAndLevel(student.getId(), task.getPoints());
            } else {
                taskView.displayErrorAddingTaskConnection();
            }
        } else {
            taskView.displayThereIsNoTaskWithThisName();
        }
    }
}
