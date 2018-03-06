package com.example.queststore.controllers;


import com.example.queststore.dao.StudentDataDAO;
import com.example.queststore.dao.UserDAO;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.models.StudentData;
import com.example.queststore.models.User;
import com.example.queststore.views.UserView;

import java.util.ArrayList;
import java.util.List;

class UserController {

    private UserDAO userDAO;
    private UserView view;
    private StudentDataDAO studentDataDAO;

    public UserController(UserDAO userDAO, UserView view, StudentDataDAO studentDataDAO) {
        this.userDAO = userDAO;
        this.view = view;
        this.studentDataDAO = studentDataDAO;
    }

    void promoteBlankUser() {

        if (userDAO.getAllByRole(UserEntry.BLANK_USER_ROLE).size() > 0) {
            List<User> users = new ArrayList<>(userDAO.getAllByRole(UserEntry.BLANK_USER_ROLE));
            view.displayEntriesNoInput(users);
            String login = view.askForLogin();
            User user = userDAO.getByLoginAndRole(login, UserEntry.BLANK_USER_ROLE);

            if (user != null) {
                promote(user);
            } else {
                view.displayUserDoesNotExist();
            }
        } else {
            view.displayEmptyListMsg();
        }
    }

    void promote(User user) {

        boolean isPromoteToMentor = view.getTypeOfPromotion();
        boolean isPromoted;

        if (isPromoteToMentor) {
            user.setRole(UserEntry.MENTOR_ROLE);
            isPromoted = userDAO.update(user);
        } else {
            user.setRole(UserEntry.STUDENT_ROLE);
            isPromoted = userDAO.update(user);

            StudentData student = createStudent(user);
            studentDataDAO.add(student);
        }
        if (isPromoted) {
            view.displayHasBeenPromoted();
        } else {
            view.displayUserNotExists();
        }
    }

    StudentData createStudent(User user) {
        StudentData student = new StudentData();
        student.setStudentId(user.getId());
        return student;

    }
}
