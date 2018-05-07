package com.example.queststore.app;

import com.example.queststore.controllers.AdminController;
import com.example.queststore.controllers.MentorController;
import com.example.queststore.controllers.RootController;
import com.example.queststore.controllers.StudentController;
import com.example.queststore.dao.*;
import com.example.queststore.dao.sqlite.*;
import com.example.queststore.services.*;
import com.example.queststore.views.*;

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

        ExpLevelsService expLevelsService = new ExpLevelsService(expLevelsView, expLevelsDAO);
        GroupService groupService = new GroupService(groupView, mentorView, groupDAO, userDAO,
                mentorGroupDAO, studentDataDAO);
        ItemService itemService = new ItemService(itemDAO, userDAO, studentItemDAO, itemView);
        MentorService mentorService = new MentorService(userDAO, groupService, mentorView);
        StudentService studentService = new StudentService(studentDataDAO, studentItemDAO, expLevelsDAO, userDAO,
                itemDAO, studentView);
        TaskService taskService = new TaskService(taskDAO, userDAO, studentTaskDAO, studentService, taskView);
        TeamService teamService = new TeamService(studentDataDAO, studentItemDAO, userView);

        AdminController adminController = new AdminController(userDAO, userView, studentDataDAO, adminView,
                groupService, expLevelsService, mentorService);
        MentorController mentorController = new MentorController(userDAO, userView, studentDataDAO, mentorView,
                teamService, groupService, taskService, studentService, itemService);
        StudentController studentController = new StudentController(studentView, studentDataDAO, studentService);
        RootController rootController = new RootController(userDAO, rootView, adminController, studentController,
                mentorController);
        rootController.start();
    }
}
