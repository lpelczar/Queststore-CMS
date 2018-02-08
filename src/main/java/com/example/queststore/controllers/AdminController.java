package com.example.queststore.controllers;

import com.example.queststore.services.ExpLevelsService;
import com.example.queststore.services.GroupService;
import com.example.queststore.services.MentorService;
import com.example.queststore.views.AdminView;

public class AdminController extends UserController {

    private AdminView adminView = new AdminView();
    private GroupService groupService = new GroupService();
    private ExpLevelsService expLevelsService = new ExpLevelsService();
    private MentorService mentorService = new MentorService();

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
                    groupService.createGroup();
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
                    expLevelsService.addLevelOfExperience();
                    break;
                case 10:
                    expLevelsService.removeLevelOfExperience();
                    break;
                case 11:
                    expLevelsService.showAllLevelsOfExperience();
                    break;
                case 12:
                    isAppRunning = false;
            }
        }
    }
}
