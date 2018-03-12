package com.example.queststore.app;

import com.example.queststore.controllers.AdminController;
import com.example.queststore.controllers.MentorController;
import com.example.queststore.controllers.RootController;
import com.example.queststore.controllers.StudentController;
import com.example.queststore.dao.*;
import com.example.queststore.services.*;
import com.example.queststore.views.*;

public class Application {

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

        ExpLevelsDAO expLevelsDAO = new DbExpLevelsDAO();
        GroupDAO groupDAO = new DbGroupDAO();
        ItemDAO itemDAO = new DbItemDAO();
        MentorGroupDAO mentorGroupDAO = new DbMentorGroupDAO();
        StudentDataDAO studentDataDAO = new DbStudentDataDAO();
        StudentItemDAO studentItemDAO = new DbStudentItemDAO();
        StudentTaskDAO studentTaskDAO = new DbStudentTaskDAO();
        TaskDAO taskDAO = new DbTaskDAO();
        UserDAO userDAO = new DbUserDAO();

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
