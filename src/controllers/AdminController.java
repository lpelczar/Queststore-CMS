package controllers;


import data.contracts.UserContract.UserEntry;
import views.AdminView;
import models.*;
import dao.*;

public class AdminController {

    private AdminView view = new AdminView();
    private UserDAO dbUserDAO = new DbUserDAO();
    private GroupDAO dbGroupDAO = new DbGroupDAO();

    public void start() {

        int option;
        boolean isRunning = true;

        while (isRunning) {
            view.clearConsole();
            view.handleAdminMenu();
            option = view.askForOption();

            if (option == 1) {
                promoteBlankUser();
            } else if (option == 2) {
                createGroup();
            } else if (option == 3) {
//                assignMentorToGroup();
            } else if (option == 4) {
                editMentorData();
            } else if (option == 5) {
//                editMentorGroups();
            } else if (option == 6) {
//                showMentorProfileWithAllHisGroups();
            } else if (option == 7) {
//                addLevelOfExperience();
            } else if (option == 8) {
//                showAllLevelsOfExperience();
            } else if (option == 9) {
                isRunning = false;
            }
        }
    }

    private void promoteBlankUser() {

        if (dbUserDAO.getAllByRole(UserEntry.BLANK_USER_ROLE).size() > 0) {
            view.displayUsers(dbUserDAO.getAllByRole(UserEntry.BLANK_USER_ROLE));
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

    private void createGroup() {

        String name = view.getGroupNameInput();
        Group group = new Group(name);
        if (dbGroupDAO.add(group)) {
            view.displayGroupAdded();
        } else {
            view.displayGroupWithThisNameAlreadyExists();
        }
    }

//    private void addGroupToMentor(Group group) {
//        view.displayMentors(mentorDAO.getMentors());
//        String mentorLogin = view.getMentorLoginToAssignGroup();
//        if (mentorDAO.getMentorBy(mentorLogin) != null) {
//            mentorDAO.getMentorBy(mentorLogin).addGroup(group.getID());
//            view.displayMentorAssignedToThisGroup();
//        } else {
//            view.displayThereIsNoMentorWithThisLogin();
//        }
//    }
//
//    private void handleShowingMentorProfile() {
//
//        view.displayMentors(mentorDAO.getMentors());
//        if (mentorDAO.getMentors().isEmpty()) {
//            view.displayPressAnyKeyToContinueMessage();
//            return;
//        }
//        String login = view.getMentorLoginToShow();
//        Mentor mentor = mentorDAO.getMentorBy(login);
//
//        if (mentor != null) {
//            view.displayMentorProfile(mentor);
//            showMentorGroups(mentor);
//        } else {
//            view.displayNoMentorMessage();
//        }
//    }
//
//    private void showMentorGroups(Mentor mentor) {
//
//        List<Integer> groupsIDs = mentor.getGroupsIDs();
//        if (groupsIDs.isEmpty()) {
//            view.displayMentorHasNoGroupsAssigned();
//        }
//    }
//
    private void editMentorData() {

        final String QUIT_OPTION = "q";

        view.displayUsers(dbUserDAO.getAllByRole(UserEntry.MENTOR_ROLE));
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
//
//    private void handleCreateLevel() {
//
//        String levelName = view.getLevelNameInput();
//        int value = view.getLevelValueInput();
//
//        levelDAO.setThreshold(levelName, value);
//        view.displayLevelSetMessage();
//
//    }
}
