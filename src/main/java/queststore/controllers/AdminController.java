package queststore.controllers;

import queststore.dao.StudentDataDAO;
import queststore.dao.UserDAO;
import queststore.services.ExpLevelsService;
import queststore.services.GroupService;
import queststore.services.MentorService;
import queststore.views.AdminView;
import queststore.views.UserView;

public class AdminController extends UserController {

    private AdminView adminView;
    private GroupService groupService;
    private ExpLevelsService expLevelsService;
    private MentorService mentorService;

    public AdminController(UserDAO userDAO, UserView userView, StudentDataDAO studentDataDAO, AdminView adminView,
                           GroupService groupService, ExpLevelsService expLevelsService, MentorService mentorService) {
        super(userDAO, userView, studentDataDAO);
        this.adminView = adminView;
        this.groupService = groupService;
        this.expLevelsService = expLevelsService;
        this.mentorService = mentorService;
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
