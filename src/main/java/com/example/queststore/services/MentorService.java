package com.example.queststore.services;

import com.example.queststore.dao.DbUserDAO;
import com.example.queststore.dao.UserDAO;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.models.User;
import com.example.queststore.views.MentorView;

import java.util.ArrayList;
import java.util.List;

public class MentorService {

    private MentorView mentorView = new MentorView();
    private UserDAO dbUserDAO = new DbUserDAO();
    private GroupService groupService = new GroupService();

    public void deleteMentor() {
        List<User> mentors = new ArrayList<>(dbUserDAO.getAllByRole(UserEntry.MENTOR_ROLE));
        mentorView.displayEntriesNoInput(mentors);
        if (mentors.isEmpty()) {
            mentorView.displayPressAnyKeyToContinueMessage();
            return;
        }
        String login = mentorView.getMentorLoginToDelete();
        User mentor = dbUserDAO.getByLoginAndRole(login, UserEntry.MENTOR_ROLE);
        if (mentor != null) {
            dbUserDAO.delete(mentor);
            mentorView.displayMentorDeletedMessage();
        } else {
            mentorView.displayNoMentorMessage();
        }
    }

    public void showMentorProfileAndHisGroups() {
        List<User> mentors = new ArrayList<>(dbUserDAO.getAllByRole(UserEntry.MENTOR_ROLE));
        mentorView.displayEntriesNoInput(mentors);
        if (mentors.isEmpty()) {
            mentorView.displayPressAnyKeyToContinueMessage();
            return;
        }
        String login = mentorView.getMentorLoginToShowProfile();
        User mentor = dbUserDAO.getByLoginAndRole(login, UserEntry.MENTOR_ROLE);

        if (mentor != null) {
            mentorView.displayMentorProfile(mentor);
            groupService.showMentorGroups(mentor.getId());
        } else {
            mentorView.displayNoMentorMessage();
        }
    }

    public void editMentorData() {

        final String QUIT_OPTION = "q";

        List<User> mentors = new ArrayList<>(dbUserDAO.getAllByRole(UserEntry.MENTOR_ROLE));
        mentorView.displayEntriesNoInput(mentors);
        if (dbUserDAO.getAllByRole(UserEntry.MENTOR_ROLE).isEmpty()) {
            mentorView.displayPressAnyKeyToContinueMessage();
            return;
        }
        String login = mentorView.getMentorLoginToEdit();
        if (login.equals(QUIT_OPTION)) return;
        User mentorToEdit = dbUserDAO.getByLoginAndRole(login, UserEntry.MENTOR_ROLE);
        if (mentorToEdit != null) {
            updateProfileAttribute(mentorToEdit);
        } else {
            mentorView.displayThereIsNoMentorWithThisLogin();
        }
    }

    private void updateProfileAttribute(User user) {
        final int UPDATE_NAME = 1;
        final int UPDATE_LOGIN = 2;
        final int UPDATE_EMAIL = 3;
        final int UPDATE_PHONE = 4;

        int valueToChange = mentorView.askForChangeInProfile(user);
        switch (valueToChange) {
            case UPDATE_NAME:
                String name = mentorView.askForNewValue();
                user.setName(name);
                showEditResultMessage(dbUserDAO.update(user));
                break;
            case UPDATE_LOGIN:
                String login = mentorView.askForNewValue();
                user.setLogin(login);
                showEditResultMessage(dbUserDAO.update(user));
                break;
            case UPDATE_EMAIL:
                String email = mentorView.askForNewValue();
                user.setEmail(email);
                showEditResultMessage(dbUserDAO.update(user));
                break;
            case UPDATE_PHONE:
                String phoneNumber = mentorView.askForNewValue();
                user.setPhoneNumber(phoneNumber);
                showEditResultMessage(dbUserDAO.update(user));
                break;
            default:
                mentorView.displayWrongSignError();
                break;
        }
    }

    private void showEditResultMessage(boolean isEdit) {
        if (isEdit) {
            mentorView.displayValueHasBeenChanged();
        } else {
            mentorView.displayErrorChangingTheValue();
        }
    }
}
