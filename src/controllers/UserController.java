package controllers;

import dao.DbUserDAO;
import dao.UserDAO;
import data.contracts.UserContract;
import models.Entry;
import models.User;
import views.UserView;
import models.StudentData;
import dao.DbStudentDataDAO;

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

    private StudentData createStudent(User user) {
        StudentData student = new StudentData();
        student.setStudentId(user.getId());
        return student;

    }
}
