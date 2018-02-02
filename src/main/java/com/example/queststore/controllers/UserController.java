package com.example.queststore.controllers;


import com.example.queststore.dao.DbStudentDataDAO;
import com.example.queststore.dao.DbUserDAO;
import com.example.queststore.dao.UserDAO;
import com.example.queststore.data.contracts.UserContract;
import com.example.queststore.models.Entry;
import com.example.queststore.models.StudentData;
import com.example.queststore.models.User;
import com.example.queststore.views.UserView;

import java.util.ArrayList;
import java.util.List;

class UserController {

    private UserDAO dbUserDAO = new DbUserDAO();
    private UserView view = new UserView();
    private DbStudentDataDAO dbStudentDataDAO = new DbStudentDataDAO();

    void promoteBlankUser() {

        if (dbUserDAO.getAllByRole(UserContract.UserEntry.BLANK_USER_ROLE).size() > 0) {
            List<Entry> users = new ArrayList<>(dbUserDAO.getAllByRole(UserContract.UserEntry.BLANK_USER_ROLE));
            view.displayEntriesNoInput(users);
            String login = view.askForLogin();
            User user = dbUserDAO.getByLoginAndRole(login, UserContract.UserEntry.BLANK_USER_ROLE);

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
            user.setRole(UserContract.UserEntry.MENTOR_ROLE);
            isPromoted = dbUserDAO.update(user);
        } else {
            user.setRole(UserContract.UserEntry.STUDENT_ROLE);
            isPromoted = dbUserDAO.update(user);

            StudentData student = createStudent(user);
            dbStudentDataDAO.add(student);
        }
        if (isPromoted) {
            view.displayHasBeenPromoted();
        } else {
            view.displayUserNotExists();
        }
    }

    protected StudentData createStudent(User user) {
        StudentData student = new StudentData();
        student.setStudentId(user.getId());
        return student;

    }
}
