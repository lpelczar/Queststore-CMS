package com.example.queststore.controllers;


import com.example.queststore.dao.*;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.models.StudentData;
import com.example.queststore.models.User;
import com.example.queststore.services.*;
import com.example.queststore.views.MentorView;

public class MentorController extends UserController {

    private MentorView mentorView = new MentorView();
    private UserDAO dbUserDAO = new DbUserDAO();
    private StudentDataDAO dbStudentDataDAO = new DbStudentDataDAO();
    private TeamService teamService = new TeamService();
    private GroupService groupService = new GroupService();
    private TaskService taskService = new TaskService();
    private StudentService studentService = new StudentService();
    private ItemService itemService = new ItemService();

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
                    teamService.handleRerollStudentsTeams();
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
