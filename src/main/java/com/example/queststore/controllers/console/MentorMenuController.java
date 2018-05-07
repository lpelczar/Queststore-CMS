package com.example.queststore.controllers.console;


import com.example.queststore.dao.*;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.models.StudentData;
import com.example.queststore.models.User;
import com.example.queststore.views.console.MentorView;
import com.example.queststore.views.console.UserView;

public class MentorMenuController extends UserController {

    private MentorView mentorView;
    private TeamController teamController;
    private GroupController groupController;
    private TaskController taskController;
    private StudentController studentController;
    private ItemController itemController;

    public MentorMenuController(UserDAO userDAO, UserView view, StudentDataDAO studentDataDAO, MentorView mentorView,
                                TeamController teamController, GroupController groupController, TaskController taskController,
                                StudentController studentController, ItemController itemController) {
        super(userDAO, view, studentDataDAO);
        this.mentorView = mentorView;
        this.teamController = teamController;
        this.groupController = groupController;
        this.taskController = taskController;
        this.studentController = studentController;
        this.itemController = itemController;
    }

    void start() {
        int option;
        boolean isAppRunning = true;

        while (isAppRunning) {
            mentorView.clearConsole();
            mentorView.handleMentorMenu();
            option = mentorView.askForOption();

            switch (option) {
                case 1:
                    promoteBlankUser();
                    break;
                case 2:
                    groupController.addStudentToGroup();
                    break;
                case 3:
                    taskController.addNewQuest();
                    break;
                case 4:
                    itemController.addNewItem();
                    break;
                case 5:
                    taskController.editQuest();
                    break;
                case 6:
                    itemController.editItem();
                    break;
                case 7:
                    taskController.markStudentAchievedQuest();
                    break;
                case 8:
                    itemController.markStudentUsedItem();
                    break;
                case 9:
                    studentController.showStudentSummary();
                    break;
                case 10:
                    teamController.handleReshuffleStudentsTeams();
                    break;
                case 11:
                    isAppRunning = false;
            }
        }
    }

    @Override
    void promote(User user) {
        user.setRole(UserEntry.STUDENT_ROLE);
        boolean isPromoted = getUserDAO().update(user);
        StudentData student = createStudent(user);
        getStudentDataDAO().add(student);
        if (isPromoted) {
            mentorView.displayHasBeenPromoted();
        } else {
            mentorView.displayUserNotExists();
        }
    }
}
