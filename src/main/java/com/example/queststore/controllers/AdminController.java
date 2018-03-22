package com.example.queststore.controllers;

import com.example.queststore.dao.StudentDataDAO;
import com.example.queststore.dao.UserDAO;
import com.example.queststore.models.Group;
import com.example.queststore.services.ExpLevelsService;
import com.example.queststore.services.GroupService;
import com.example.queststore.services.MentorService;
import com.example.queststore.views.AdminView;
import com.example.queststore.views.ExpLevelsView;
import com.example.queststore.views.GroupView;
import com.example.queststore.views.UserView;

public class AdminController extends UserController {

    private AdminView adminView;
    private GroupService groupService;
    private ExpLevelsService expLevelsService;
    private MentorService mentorService;
    // fields below need to be refactored
    private ExpLevelsView expLevelsView;
    private GroupView groupView;

    public AdminController(UserDAO userDAO, UserView userView, StudentDataDAO studentDataDAO, AdminView adminView,
                           GroupService groupService, ExpLevelsService expLevelsService, MentorService mentorService) {
        super(userDAO, userView, studentDataDAO);
        this.adminView = adminView;
        this.groupService = groupService;
        this.expLevelsService = expLevelsService;
        this.mentorService = mentorService;
        this.expLevelsView = new ExpLevelsView();
        this.groupView = new GroupView();

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
                    String name = groupView.getGroupNameInput();
                    if (groupService.createGroup(name)) {
                        groupView.displayGroupAdded();
                    } else {
                        groupView.displayGroupWithThisNameAlreadyExists();
                    }
                    break;
                case 3:
                    groupService.assignMentorToGroup();
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
                    String levelName = expLevelsView.getLevelNameInput();
                    int levelValue = expLevelsView.getLevelValueInput();
                    expLevelsService.addLevelOfExperience(levelName, levelValue);
                    break;
                case 10:
                    expLevelsView.displayEntriesNoInput(expLevelsService.getAllLevelsOfExperience());
                    if (expLevelsService.getAllLevelsOfExperience().isEmpty()) {
                        expLevelsView.displayPressAnyKeyToContinueMessage();
                        break;
                    }
                    levelName = expLevelsView.getLevelNameInput();
                    if (levelName != null){
                        if (expLevelsService.removeLevelOfExperience(levelName)) {
                            expLevelsView.displayLevelDeletedMessage();
                        } else {
                            expLevelsView.displayDeleteErrorMessage();
                        }
                    } else {
                        expLevelsView.displayThereIsNoLevelWithThisNameMessage();
                    }
                case 11:
                    expLevelsView.displayEntries(expLevelsService.getAllLevelsOfExperience());
                    expLevelsService.getAllLevelsOfExperience();
                    break;
                case 12:
                    isAppRunning = false;
            }
        }
    }
}
