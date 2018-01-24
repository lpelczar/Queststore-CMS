package controllers;

import java.util.List;

import data.contracts.UserContract.UserEntry;
import views.AdminView;
import models.*;
import dao.*;

public class AdminController {

    private AdminView view = new AdminView();
    private UserDAO dbUserDAO = new DbUserDAO();

    public void start() {

        int option;
        boolean isRunning = true;

        while (isRunning) {
            view.clearConsole();
            view.handleAdminMenu();
            option = view.askForOption();

            if (option == 1) {
                handlePromoteBlankUser();
            } else if (option == 2) {
                handleCreatingGroup();
            } else if (option == 3) {
                handleEditProfile();
            } else if (option == 4) {
                handleShowingMentorProfile();
            } else if (option == 5) {
                handleCreateLevel();
            } else if (option == 6) {
                isRunning = false;
            }
        }
    }

    private void handlePromoteBlankUser() {

        if (dbUserDAO.getAllByRole(UserEntry.BLANK_USER_ROLE).size() > 0) {
            view.displayBlankUsers(dbUserDAO.getAllByRole(UserEntry.BLANK_USER_ROLE));
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
        }
        if (isPromoted) {
            view.displayHasBeenPromoted();
        } else {
            view.displayUserNotExists();
        }
    }

    private void handleCreatingGroup() {

        String name = view.getGroupNameInput();
        Group group = new Group(name);
        if (groupDAO.addGroup(group)) {
            view.displayGroupAdded();
            if (mentorDAO.getMentors().size() > 0) {
                addGroupToMentor(group);
            } else {
                view.displayThereIsNoMentorsMessage();
            }
        } else {
            view.displayGroupWithThisNameAlreadyExists();
        }
    }

    private void addGroupToMentor(Group group) {
        view.displayMentors(mentorDAO.getMentors());
        String mentorLogin = view.getMentorLoginToAssignGroup();
        if (mentorDAO.getMentorBy(mentorLogin) != null) {
            mentorDAO.getMentorBy(mentorLogin).addGroup(group.getID());
            view.displayMentorAssignedToThisGroup();
        } else {
            view.displayThereIsNoMentorWithThisLogin();
        }
    }

    private void handleShowingMentorProfile() {

        view.displayMentors(mentorDAO.getMentors());
        if (mentorDAO.getMentors().isEmpty()) {
            view.displayPressAnyKeyToContinueMessage();
            return;
        }
        String login = view.getMentorLoginToShow();
        Mentor mentor = mentorDAO.getMentorBy(login);

        if (mentor != null) {
            view.displayMentorProfile(mentor);
            showMentorGroups(mentor);
        } else {
            view.displayNoMentorMessage();
        }
    }

    private void showMentorGroups(Mentor mentor) {

        List<Integer> groupsIDs = mentor.getGroupsIDs();
        if (groupsIDs.isEmpty()) {
            view.displayMentorHasNoGroupsAssigned();
        }
    }

    private void handleEditProfile() {

        final String QUIT_OPTION = "q";

        view.displayMentors(mentorDAO.getMentors());
        if (mentorDAO.getMentors().isEmpty()) {
            view.displayPressAnyKeyToContinueMessage();
            return;
        }
        String login = view.getMentorLoginToEdit();
        if (login.equals(QUIT_OPTION)) return;
        Mentor profileToEdit = mentorDAO.getMentorBy(login);
        if (profileToEdit != null) {
            updateProfileAttribute(profileToEdit);
            mentorDAO.saveAllMentors();
        } else {
            view.displayThereIsNoMentorWithThisLogin();
        }
    }

    private void updateProfileAttribute(Mentor profile) {

        int toChange = view.askForChangeInProfile(profile);
        if (toChange == 1) {
            String name = view.askForNewValue();
            profile.setName(name);
            view.displayValueHasBeenChanged();
        } else if (toChange == 2) {
            String login = view.askForNewValue();
            profile.setLogin(login);
            view.displayValueHasBeenChanged();
        } else if (toChange == 3) {
            String email = view.askForNewValue();
            profile.setEmail(email);
            view.displayValueHasBeenChanged();
        } else if (toChange == 4) {
            String phoneNumber = view.askForNewValue();
            profile.setPhoneNumber(phoneNumber);
            view.displayValueHasBeenChanged();
        } else {
            view.displayWrongSignError();
        }
    }

    private void handleCreateLevel() {

        String levelName = view.getLevelNameInput();
        int value = view.getLevelValueInput();

        levelDAO.setThreshold(levelName, value);
        view.displayLevelSetMessage();

    }
}
