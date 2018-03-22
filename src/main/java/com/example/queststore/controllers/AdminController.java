package com.example.queststore.controllers;

import com.example.queststore.dao.*;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.models.Group;
import com.example.queststore.models.User;
import com.example.queststore.services.ExpLevelsService;
import com.example.queststore.services.GroupService;
import com.example.queststore.services.MentorService;
import com.example.queststore.views.*;

import java.util.ArrayList;
import java.util.List;

public class AdminController extends UserController {

    private AdminView adminView;
    private GroupService groupService;
    private ExpLevelsService expLevelsService;
    private MentorService mentorService;
    // fields below need to be included in AdminController object creation
    private ExpLevelsView expLevelsView;
    private GroupView groupView;
    private MentorView mentorView;
    private UserDAO userDAO;
    private GroupDAO groupDAO;
    private MentorGroupDAO mentorGroupDAO;

    public AdminController(UserDAO userDAO, UserView userView, StudentDataDAO studentDataDAO, AdminView adminView,
                           GroupService groupService, ExpLevelsService expLevelsService, MentorService mentorService) {
        super(userDAO, userView, studentDataDAO);
        this.adminView = adminView;
        this.groupService = groupService;
        this.expLevelsService = expLevelsService;
        this.mentorService = mentorService;
        this.expLevelsView = new ExpLevelsView();
        this.groupView = new GroupView();
        this.mentorView = new MentorView();
        this.userDAO = userDAO;
        this.groupDAO = new DbGroupDAO();
        this.mentorGroupDAO = new DbMentorGroupDAO();

    }

    public void start() {

        int option;
        boolean isAppRunning = true;

        while (isAppRunning) {
            adminView.clearConsole();
            adminView.handleAdminMenu();
            option = adminView.askForOption();

            switch (option) {
                case 1:
                    promoteBlankUser();
                    break;
                case 2:
                    createGroup();
                    break;
                case 3:
                    chooseMentorForGroupAssignment();
                    break;
                case 4:
                    groupService.revokeMentorFromGroup();
                    break;
                case 5:
                    groupService.deleteGroup();
                    break;
                case 6:
                    mentorService.deleteMentor();
                    break;
                case 7:
                    mentorService.editMentorData();
                    break;
                case 8:
                    mentorService.showMentorProfileAndHisGroups();
                    break;
                case 9:
                    createNewLevelOfExperience();
                    break;
                case 10:
                    deleteLevelOfExperience();
                    break;
                case 11:
                    showLevelsOfExperience();
                    break;
                case 12:
                    isAppRunning = false;
            }
        }
    }
//  2
    private void createGroup() {
        String name = groupView.getGroupNameInput();
        if (groupService.createGroup(name)) {
            groupView.displayGroupAdded();
        } else {
            groupView.displayGroupWithThisNameAlreadyExists();
        }
    }
//  3
    private void chooseMentorForGroupAssignment() {
        List<User> mentors = new ArrayList<>(userDAO.getAllByRole(UserEntry.MENTOR_ROLE));
        groupView.displayEntriesNoInput(mentors);
        if (mentors.isEmpty()) {
            groupView.displayPressAnyKeyToContinueMessage();
            return;
        }

        String mentorLogin = mentorView.getMentorLoginToAssignGroup();
        if (groupService.verifyMentorExists(mentorLogin)) {
            chooseGroupAndAssignToMentor(mentorLogin);
        } else {
            mentorView.displayThereIsNoMentorWithThisLogin();
        }
    }

    private void chooseGroupAndAssignToMentor(String mentorLogin) {
        List<Group> groups = groupService.getAllGroups();
        groupView.displayEntriesNoInput(groups);
        if (groups.isEmpty()) {
            groupView.displayPressAnyKeyToContinueMessage();
            return;
        }

        String groupName = groupView.getGroupNameInput();
        if (groupDAO.getByName(groupName) != null) {
            Group group = groupDAO.getByName(groupName);
            User mentor = userDAO.getByLogin(mentorLogin);
            boolean isAdded = mentorGroupDAO.add(group.getId(), mentor.getId());
            if (isAdded) {
                groupView.displayGroupConnectionAdded();
            } else {
                groupView.displayErrorAddingGroupConnection();
            }
        } else {
            groupView.displayThereIsNoGroupWithThisName();
        }
    }

//  9
    private void createNewLevelOfExperience() {
        String levelName = expLevelsView.getLevelNameInput();
        int levelValue = expLevelsView.getLevelValueInput();
        expLevelsService.addLevelOfExperience(levelName, levelValue);
    }

//  10
    private void deleteLevelOfExperience() {
        expLevelsView.displayEntriesNoInput(expLevelsService.getAllLevelsOfExperience());
        if (expLevelsService.getAllLevelsOfExperience().isEmpty()) {
            expLevelsView.displayPressAnyKeyToContinueMessage();
            return;
        }
        String levelName = expLevelsView.getLevelNameInput();
        if (levelName != null){
            if (expLevelsService.removeLevelOfExperience(levelName)) {
                expLevelsView.displayLevelDeletedMessage();
            } else {
                expLevelsView.displayDeleteErrorMessage();
            }
        } else {
            expLevelsView.displayThereIsNoLevelWithThisNameMessage();
        }
    }

//  11
    private void showLevelsOfExperience() {
        expLevelsView.displayEntries(expLevelsService.getAllLevelsOfExperience());
        expLevelsService.getAllLevelsOfExperience();
    }

}


