package controllers;


import data.contracts.UserContract.UserEntry;
import views.AdminView;
import models.*;
import dao.*;

import java.util.ArrayList;
import java.util.List;

public class AdminController {

    private AdminView view = new AdminView();
    private UserDAO dbUserDAO = new DbUserDAO();
    private GroupDAO dbGroupDAO = new DbGroupDAO();
    private ExpLevelsDAO dbExpLevelsDAO = new DbExpLevelsDAO();
    private MentorGroupDAO dbMentorGroupDAO = new DbMentorGroupDAO();
    private DbStudentDataDAO dbStudentDataDAO = new DbStudentDataDAO();

    public void start() {

        int option;
        boolean isAppRunning = true;

        while (isAppRunning) {
            view.clearConsole();
            view.handleAdminMenu();
            option = view.askForOption();

            if (option == 1) {
                promoteBlankUser();
            } else if (option == 2) {
                createGroup();
            } else if (option == 3) {
                assignMentorToGroup();
            } else if (option == 4) {
                revokeMentorFromGroup();
            } else if (option == 5) {
                deleteGroup();
            } else if (option == 6) {
                deleteMentor();
            } else if (option == 7) {
                editMentorData();
            } else if (option == 8) {
                showMentorProfileAndHisGroups();
            } else if (option == 9) {
                addLevelOfExperience();
            } else if (option == 10) {
                showAllLevelsOfExperience();
            } else if (option == 11) {
                isAppRunning = false;
            }
        }
    }

    private void promoteBlankUser() {

        if (dbUserDAO.getAllByRole(UserEntry.BLANK_USER_ROLE).size() > 0) {
            List<Entry> users = new ArrayList<>(dbUserDAO.getAllByRole(UserEntry.BLANK_USER_ROLE));
            view.displayEntries(users);
            String login = view.askForLogin();
            User user = dbUserDAO.getByLoginAndRole(login, UserEntry.BLANK_USER_ROLE);

            if (user != null) {
                promote(user);
            } else {
                view.displayUserDoesNotExist();
            }
        } else {
            view.displayEmptyListMsg();
        }
    }

    private void promote(User user) {

        boolean isPromoteToMentor = view.getTypeOfPromotion();
        boolean isPromoted;

        if (isPromoteToMentor) {
            user.setRole(UserEntry.MENTOR_ROLE);
            isPromoted = dbUserDAO.update(user);
        } else {
            user.setRole(UserEntry.STUDENT_ROLE);
            isPromoted = dbUserDAO.update(user);

            StudentData student = new StudentData();
            dbStudentDataDAO.add(student, user);
        }
        if (isPromoted) {
            view.displayHasBeenPromoted();
        } else {
            view.displayUserNotExists();
        }
    }

    private void createGroup() {

        String name = view.getGroupNameInput();
        Group group = new Group(name);
        if (dbGroupDAO.add(group)) {
            view.displayGroupAdded();
        } else {
            view.displayGroupWithThisNameAlreadyExists();
        }
    }

    private void assignMentorToGroup() {
        List<Entry> mentors = new ArrayList<>(dbUserDAO.getAllByRole(UserEntry.MENTOR_ROLE));
        view.displayEntriesNoInput(mentors);

        String mentorLogin = view.getMentorLoginToAssignGroup();
        if (dbUserDAO.getByLogin(mentorLogin) != null) {
            choseGroupAndAssignToMentor(mentorLogin);
        } else {
            view.displayThereIsNoMentorWithThisLogin();
        }
    }

    private void choseGroupAndAssignToMentor(String mentorLogin) {
        List<Entry> groups = new ArrayList<>(dbGroupDAO.getAll());
        view.displayEntriesNoInput(groups);

        String groupName = view.getGroupNameInput();
        if (dbGroupDAO.getByName(groupName) != null) {
            Group group = dbGroupDAO.getByName(groupName);
            User mentor = dbUserDAO.getByLogin(mentorLogin);
            boolean isAdded = dbMentorGroupDAO.add(group.getId(), mentor.getId());
            if (isAdded) {
                view.displayGroupConnectionAdded();
            } else {
                view.displayErrorAddingGroupConnection();
            }
        } else {
            view.displayThereIsNoGroupWithThisName();
        }
    }

    private void revokeMentorFromGroup() {
        List<Entry> mentors = new ArrayList<>(dbUserDAO.getAllByRole(UserEntry.MENTOR_ROLE));
        view.displayEntriesNoInput(mentors);

        String mentorLogin = view.getMentorLoginToRevokeFromGroup();
        if (dbUserDAO.getByLogin(mentorLogin) != null) {
            choseGroupAndRevokeMentor(mentorLogin);
        } else {
            view.displayThereIsNoMentorWithThisLogin();
        }
    }

    private void choseGroupAndRevokeMentor(String mentorLogin) {
        List<Entry> groups = new ArrayList<>(dbGroupDAO.getAll());
        view.displayEntriesNoInput(groups);

        String groupName = view.getGroupNameInput();
        if (dbGroupDAO.getByName(groupName) != null) {
            Group group = dbGroupDAO.getByName(groupName);
            User mentor = dbUserDAO.getByLogin(mentorLogin);
            boolean isRemoved = dbMentorGroupDAO.delete(group.getId(), mentor.getId());
            if (isRemoved) {
                view.displayGroupConnectionRemoved();
            } else {
                view.displayErrorRemovingGroupConnection();
            }
        } else {
            view.displayThereIsNoGroupWithThisName();
        }
    }

    private void deleteGroup() {
        List<Entry> groups = new ArrayList<>(dbGroupDAO.getAll());
        view.displayEntriesNoInput(groups);
        if (groups.isEmpty()) {
            view.displayPressAnyKeyToContinueMessage();
            return;
        }

        String groupName = view.getGroupNameInput();
        Group group = dbGroupDAO.getByName(groupName);

        if (group != null) {
            dbGroupDAO.delete(group);
            view.displayGroupDeleted();
        } else {
            view.displayThereIsNoGroupWithThisName();
        }
    }

    private void deleteMentor() {
        List<Entry> mentors = new ArrayList<>(dbUserDAO.getAllByRole(UserEntry.MENTOR_ROLE));
        view.displayEntriesNoInput(mentors);
        if (mentors.isEmpty()) {
            view.displayPressAnyKeyToContinueMessage();
            return;
        }
        String login = view.getMentorLoginToDelete();
        User mentor = dbUserDAO.getByLoginAndRole(login, UserEntry.MENTOR_ROLE);

        if (mentor != null) {
            dbUserDAO.delete(mentor);
            view.displayMentorDeletedMessage();
        } else {
            view.displayNoMentorMessage();
        }
    }

    private void showMentorProfileAndHisGroups() {
        List<Entry> mentors = new ArrayList<>(dbUserDAO.getAllByRole(UserEntry.MENTOR_ROLE));
        view.displayEntriesNoInput(mentors);
        if (mentors.isEmpty()) {
            view.displayPressAnyKeyToContinueMessage();
            return;
        }
        String login = view.getMentorLoginToShow();
        User mentor = dbUserDAO.getByLoginAndRole(login, UserEntry.MENTOR_ROLE);

        if (mentor != null) {
            view.displayMentorProfile(mentor);
            showMentorGroups(mentor.getId());
        } else {
            view.displayNoMentorMessage();
        }
    }

    private void showMentorGroups(int mentorID) {

        //Get all groups by from mentor ID
    }

    private void editMentorData() {

        final String QUIT_OPTION = "q";

        List<Entry> mentors = new ArrayList<>(dbUserDAO.getAllByRole(UserEntry.MENTOR_ROLE));
        view.displayEntries(mentors);
        if (dbUserDAO.getAllByRole(UserEntry.MENTOR_ROLE).isEmpty()) {
            view.displayPressAnyKeyToContinueMessage();
            return;
        }
        String login = view.getMentorLoginToEdit();
        if (login.equals(QUIT_OPTION)) return;
        User mentorToEdit = dbUserDAO.getByLoginAndRole(login, UserEntry.MENTOR_ROLE);
        if (mentorToEdit != null) {
            updateProfileAttribute(mentorToEdit);
        } else {
            view.displayThereIsNoMentorWithThisLogin();
        }
    }

    private void updateProfileAttribute(User user) {

        int toChange = view.askForChangeInProfile(user);
        if (toChange == 1) {
            String name = view.askForNewValue();
            user.setName(name);
            showEditResultMessage(dbUserDAO.update(user));
        } else if (toChange == 2) {
            String login = view.askForNewValue();
            user.setLogin(login);
            showEditResultMessage(dbUserDAO.update(user));
        } else if (toChange == 3) {
            String email = view.askForNewValue();
            user.setEmail(email);
            showEditResultMessage(dbUserDAO.update(user));
        } else if (toChange == 4) {
            String phoneNumber = view.askForNewValue();
            user.setPhoneNumber(phoneNumber);
            showEditResultMessage(dbUserDAO.update(user));
        } else {
            view.displayWrongSignError();
        }
    }

    private void showEditResultMessage(boolean isEdit) {
        if (isEdit) {
            view.displayValueHasBeenChanged();
        } else {
            view.displayErrorChangingTheValue();
        }
    }

    private void addLevelOfExperience() {

        String levelName = view.getLevelNameInput();
        int value = view.getLevelValueInput();

        if (dbExpLevelsDAO.add(new ExpLevel(levelName, value))) {
            view.displayLevelSetMessage();
        } else {
            view.displayErrorChangingTheValue();
        }
    }

    private void showAllLevelsOfExperience() {

        List<Entry> expLevels = new ArrayList<>(dbExpLevelsDAO.getAll());
        view.displayEntries(expLevels);
    }
}
