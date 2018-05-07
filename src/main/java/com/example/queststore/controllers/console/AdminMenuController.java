package com.example.queststore.controllers.console;

import com.example.queststore.dao.StudentDataDAO;
import com.example.queststore.dao.UserDAO;
import com.example.queststore.views.console.AdminView;
import com.example.queststore.views.console.UserView;

public class AdminMenuController extends UserController {

    private AdminView adminView;
    private GroupController groupController;
    private ExpLevelsController expLevelsController;
    private MentorController mentorController;

    public AdminMenuController(UserDAO userDAO, UserView userView, StudentDataDAO studentDataDAO, AdminView adminView,
                               GroupController groupController, ExpLevelsController expLevelsController, MentorController mentorController) {
        super(userDAO, userView, studentDataDAO);
        this.adminView = adminView;
        this.groupController = groupController;
        this.expLevelsController = expLevelsController;
        this.mentorController = mentorController;
    }

    void start() {

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
                    groupController.createGroup();
                    break;
                case 3:
                    groupController.assignMentorToGroup();
                    break;
                case 4:
                    groupController.revokeMentorFromGroup();
                    break;
                case 5:
                    groupController.deleteGroup();
                    break;
                case 6:
                    mentorController.deleteMentor();
                    break;
                case 7:
                    mentorController.editMentorData();
                    break;
                case 8:
                    mentorController.showMentorProfileAndHisGroups();
                    break;
                case 9:
                    expLevelsController.addLevelOfExperience();
                    break;
                case 10:
                    expLevelsController.removeLevelOfExperience();
                    break;
                case 11:
                    expLevelsController.showAllLevelsOfExperience();
                    break;
                case 12:
                    isAppRunning = false;
            }
        }
    }
}
