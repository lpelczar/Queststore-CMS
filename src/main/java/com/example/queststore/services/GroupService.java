package com.example.queststore.services;

import com.example.queststore.dao.*;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.models.Entry;
import com.example.queststore.models.Group;
import com.example.queststore.models.StudentData;
import com.example.queststore.models.User;
import com.example.queststore.views.GroupView;
import com.example.queststore.views.MentorView;

import java.util.ArrayList;
import java.util.List;

public class GroupService {

    private GroupView groupView = new GroupView();
    private MentorView mentorView = new MentorView();
    private GroupDAO dbGroupDAO = new DbGroupDAO();
    private UserDAO dbUserDAO = new DbUserDAO();
    private MentorGroupDAO dbMentorGroupDAO = new DbMentorGroupDAO();
    private StudentDataDAO dbStudentDataDAO = new DbStudentDataDAO();

    public void createGroup() {

        String name = groupView.getGroupNameInput();
        Group group = new Group(name);
        if (dbGroupDAO.add(group)) {
            groupView.displayGroupAdded();
        } else {
            groupView.displayGroupWithThisNameAlreadyExists();
        }
    }

    public void assignMentorToGroup() {
        List<Entry> mentors = new ArrayList<>(dbUserDAO.getAllByRole(UserEntry.MENTOR_ROLE));
        groupView.displayEntriesNoInput(mentors);
        if (mentors.isEmpty()) {
            groupView.displayPressAnyKeyToContinueMessage();
            return;
        }
        String mentorLogin = mentorView.getMentorLoginToAssignGroup();
        if (dbUserDAO.getByLoginAndRole(mentorLogin, UserEntry.MENTOR_ROLE) != null) {
            choseGroupAndAssignToMentor(mentorLogin);
        } else {
            mentorView.displayThereIsNoMentorWithThisLogin();
        }
    }

    private void choseGroupAndAssignToMentor(String mentorLogin) {
        List<Entry> groups = new ArrayList<>(dbGroupDAO.getAll());
        groupView.displayEntriesNoInput(groups);
        if (groups.isEmpty()) {
            groupView.displayPressAnyKeyToContinueMessage();
            return;
        }
        String groupName = groupView.getGroupNameInput();
        if (dbGroupDAO.getByName(groupName) != null) {
            Group group = dbGroupDAO.getByName(groupName);
            User mentor = dbUserDAO.getByLogin(mentorLogin);
            boolean isAdded = dbMentorGroupDAO.add(group.getId(), mentor.getId());
            if (isAdded) {
                groupView.displayGroupConnectionAdded();
            } else {
                groupView.displayErrorAddingGroupConnection();
            }
        } else {
            groupView.displayThereIsNoGroupWithThisName();
        }
    }

    public void revokeMentorFromGroup() {
        List<Entry> mentors = new ArrayList<>(dbUserDAO.getAllByRole(UserEntry.MENTOR_ROLE));
        groupView.displayEntriesNoInput(mentors);
        if (mentors.isEmpty()) {
            groupView.displayPressAnyKeyToContinueMessage();
            return;
        }
        String mentorLogin = mentorView.getMentorLoginToRevokeFromGroup();
        if (dbUserDAO.getByLoginAndRole(mentorLogin, UserEntry.MENTOR_ROLE) != null) {
            choseGroupAndRevokeMentor(mentorLogin);
        } else {
            mentorView.displayThereIsNoMentorWithThisLogin();
        }
    }

    private void choseGroupAndRevokeMentor(String mentorLogin) {
        List<Entry> groups = new ArrayList<>(dbGroupDAO.getAll());
        groupView.displayEntriesNoInput(groups);
        if (groups.isEmpty()) {
            groupView.displayPressAnyKeyToContinueMessage();
            return;
        }
        String groupName = groupView.getGroupNameInput();
        if (dbGroupDAO.getByName(groupName) != null) {
            Group group = dbGroupDAO.getByName(groupName);
            User mentor = dbUserDAO.getByLogin(mentorLogin);
            boolean isRemoved = dbMentorGroupDAO.delete(group.getId(), mentor.getId());
            if (isRemoved) {
                groupView.displayGroupConnectionRemoved();
            } else {
                groupView.displayErrorRemovingGroupConnection();
            }
        } else {
            groupView.displayThereIsNoGroupWithThisName();
        }
    }

    public void deleteGroup() {
        List<Entry> groups = new ArrayList<>(dbGroupDAO.getAll());
        groupView.displayEntriesNoInput(groups);
        if (groups.isEmpty()) {
            groupView.displayPressAnyKeyToContinueMessage();
            return;
        }
        String groupName = groupView.getGroupNameInput();
        Group group = dbGroupDAO.getByName(groupName);
        if (group != null) {
            dbGroupDAO.delete(group);
            groupView.displayGroupDeleted();
        } else {
            groupView.displayThereIsNoGroupWithThisName();
        }
    }

    public void showMentorGroups(int mentorID) {
        List<String> groupsNames = new ArrayList<>(dbGroupDAO.getGroupsNamesByMentorId(mentorID));
        if (!groupsNames.isEmpty()) {
            for (String groupName : groupsNames) {
                groupView.displayGroupName(groupName);
                Group group = dbGroupDAO.getByName(groupName);
                if (!dbUserDAO.getStudentsByGroupId(group.getId()).isEmpty()) {
                    List<Entry> students = new ArrayList<>(dbUserDAO.getStudentsByGroupId(group.getId()));
                    groupView.displayEntriesNoInput(students);
                } else {
                    groupView.displayThisGroupHasNoStudentsAssigned();
                }
            }
        } else {
            mentorView.displayMentorHasNoGroupsAssigned();
        }
        mentorView.displayPressAnyKeyToContinueMessage();
    }

    public void addStudentToGroup() {

        List<Entry> students = new ArrayList<>(dbUserDAO.getAllByRole(UserEntry.STUDENT_ROLE));
        mentorView.displayEntriesNoInput(students);
        if (students.isEmpty()) {
            mentorView.displayPressAnyKeyToContinueMessage();
            return;
        }
        String studentLogin = mentorView.getStudentLoginToAssignGroup();
        if (dbUserDAO.getByLoginAndRole(studentLogin, UserEntry.STUDENT_ROLE) != null) {
            choseGroupAndAssignToStudent(studentLogin);
        } else {
            mentorView.displayThereIsNoStudentWithThisLogin();
        }
    }

    private void choseGroupAndAssignToStudent(String studentLogin) {

        List<Entry> groups = new ArrayList<>(dbGroupDAO.getAll());
        mentorView.displayEntriesNoInput(groups);
        if (groups.isEmpty()) {
            mentorView.displayPressAnyKeyToContinueMessage();
            return;
        }
        String groupName = mentorView.getGroupNameInput();
        if (dbGroupDAO.getByName(groupName) != null) {
            Group group = dbGroupDAO.getByName(groupName);
            User student = dbUserDAO.getByLogin(studentLogin);
            StudentData studentData = dbStudentDataDAO.getStudentDataBy(student.getId());
            studentData.setGroupId(group.getId());
            boolean isUpdated = dbStudentDataDAO.updateStudentData(studentData);
            if (isUpdated) {
                mentorView.displayGroupUpdated();
            } else {
                mentorView.displayErrorUpdatingGroup();
            }
        } else {
            mentorView.displayThereIsNoGroupWithThisName();
        }
    }
}
