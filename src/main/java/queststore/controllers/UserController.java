package queststore.controllers;


import queststore.dao.StudentDataDAO;
import queststore.dao.UserDAO;
import queststore.data.contracts.UserEntry;
import queststore.models.StudentData;
import queststore.models.User;
import queststore.views.UserView;

import java.util.ArrayList;
import java.util.List;

class UserController {

    private UserDAO userDAO;
    private UserView userView;
    private StudentDataDAO studentDataDAO;

    public UserController(UserDAO userDAO, UserView userView, StudentDataDAO studentDataDAO) {
        this.userDAO = userDAO;
        this.userView = userView;
        this.studentDataDAO = studentDataDAO;
    }

    void promoteBlankUser() {

        if (userDAO.getAllByRole(UserEntry.BLANK_USER_ROLE).size() > 0) {
            List<User> users = new ArrayList<>(userDAO.getAllByRole(UserEntry.BLANK_USER_ROLE));
            userView.displayEntriesNoInput(users);
            String login = userView.askForLogin(); // Get from twig login or id of user
            User user = userDAO.getByLoginAndRole(login, UserEntry.BLANK_USER_ROLE); // find user in DB

            if (user != null) {
                promote(user);
            } else {
                userView.displayUserDoesNotExist();
            }
        } else {
            userView.displayEmptyListMsg();
        }
    }

    void promote(User user) {

        boolean isPromoteToMentor = userView.getTypeOfPromotion();
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
            userView.displayHasBeenPromoted();
        } else {
            userView.displayUserNotExists();
        }
    }

    StudentData createStudent(User user) {
        try {
            StudentData student = new StudentData();
            student.setStudentId(user.getId());
            return student;
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public StudentDataDAO getStudentDataDAO() {
        return studentDataDAO;
    }
}
