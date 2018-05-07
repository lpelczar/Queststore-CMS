package com.example.queststore.app;

import com.example.queststore.controllers.console.*;
import com.example.queststore.dao.*;
import com.example.queststore.dao.sqlite.*;
import com.example.queststore.views.console.*;

public class ConsoleApp {

    public static void main(String... args) {

        AdminView adminView = new AdminView();
        ExpLevelsView expLevelsView = new ExpLevelsView();
        GroupView groupView = new GroupView();
        ItemView itemView = new ItemView();
        MentorView mentorView = new MentorView();
        RootView rootView = new RootView();
        StudentView studentView = new StudentView();
        TaskView taskView = new TaskView();
        UserView userView = new UserView();

        ExpLevelsDAO expLevelsDAO = new SqliteExpLevelsDAO();
        GroupDAO groupDAO = new SqliteGroupDAO();
        ItemDAO itemDAO = new SqliteItemDAO();
        MentorGroupDAO mentorGroupDAO = new SqliteMentorGroupDAO();
        StudentDataDAO studentDataDAO = new SqliteStudentDataDAO();
        StudentItemDAO studentItemDAO = new SqliteStudentItemDAO();
        StudentTaskDAO studentTaskDAO = new SqliteStudentTaskDAO();
        TaskDAO taskDAO = new SqliteTaskDAO();
        UserDAO userDAO = new SqliteUserDAO();

        ExpLevelsController expLevelsController = new ExpLevelsController(expLevelsView, expLevelsDAO);
        GroupController groupController = new GroupController(groupView, mentorView, groupDAO, userDAO,
                mentorGroupDAO, studentDataDAO);
        ItemController itemController = new ItemController(itemDAO, userDAO, studentItemDAO, itemView);
        MentorController mentorController = new MentorController(userDAO, groupController, mentorView);
        StudentController studentController = new StudentController(studentDataDAO, studentItemDAO, expLevelsDAO, userDAO,
                itemDAO, studentView);
        TaskController taskController = new TaskController(taskDAO, userDAO, studentTaskDAO, studentController, taskView);
        TeamController teamController = new TeamController(studentDataDAO, studentItemDAO, userView);

        AdminMenuController adminMenuController = new AdminMenuController(userDAO, userView, studentDataDAO, adminView,
                groupController, expLevelsController, mentorController);
        MentorMenuController mentorMenuController = new MentorMenuController(userDAO, userView, studentDataDAO, mentorView,
                teamController, groupController, taskController, studentController, itemController);
        StudentMenuController studentMenuController = new StudentMenuController(studentView, studentDataDAO, studentController);
        RootController rootController = new RootController(userDAO, rootView, adminMenuController, studentMenuController,
                mentorMenuController);
        rootController.start();
    }
}
