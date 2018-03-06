package com.example.queststore.controllers;


import com.example.queststore.dao.*;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.models.StudentData;
import com.example.queststore.models.User;
import com.example.queststore.services.*;
import com.example.queststore.views.MentorView;

public class MentorController extends UserController {

    private MentorView mentorView;
    private UserDAO dbUserDAO;
    private StudentDataDAO dbStudentDataDAO;
    private TeamService teamService;
    private GroupService groupService;
    private TaskService taskService;
    private StudentService studentService;
    private ItemService itemService;

    public MentorController(MentorView mentorView, UserDAO dbUserDAO, StudentDataDAO dbStudentDataDAO,
                            TeamService teamService, GroupService groupService, TaskService taskService,
                            StudentService studentService, ItemService itemService) {
        this.mentorView = mentorView;
        this.dbUserDAO = dbUserDAO;
        this.dbStudentDataDAO = dbStudentDataDAO;
        this.teamService = teamService;
        this.groupService = groupService;
        this.taskService = taskService;
        this.studentService = studentService;
        this.itemService = itemService;
    }

    public void start() {
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
                    groupService.addStudentToGroup();
                    break;
                case 3:
                    taskService.addNewQuest();
                    break;
                case 4:
                    itemService.addNewItem();
                    break;
                case 5:
                    taskService.editQuest();
                    break;
                case 6:
                    itemService.editItem();
                    break;
                case 7:
                    taskService.markStudentAchievedQuest();
                    break;
                case 8:
                    itemService.markStudentUsedItem();
                    break;
                case 9:
                    studentService.showStudentSummary();
                    break;
                case 10:
                    teamService.handleReshuffleStudentsTeams();
                    break;
                case 11:
                    isAppRunning = false;
            }
        }
    }

    @Override
    void promote(User user) {
        user.setRole(UserEntry.STUDENT_ROLE);
        boolean isPromoted = dbUserDAO.update(user);
        StudentData student = createStudent(user);
        dbStudentDataDAO.add(student);
        if (isPromoted) {
            mentorView.displayHasBeenPromoted();
        } else {
            mentorView.displayUserNotExists();
        }
    }
}
