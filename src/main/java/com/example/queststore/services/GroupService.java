package com.example.queststore.services;

import com.example.queststore.dao.*;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.models.Group;
import com.example.queststore.models.StudentData;
import com.example.queststore.models.User;
import com.example.queststore.views.GroupView;
import com.example.queststore.views.MentorView;

import java.util.ArrayList;
import java.util.List;

public class GroupService {

    private GroupView groupView;
    private MentorView mentorView;
    private GroupDAO groupDAO;
    private UserDAO userDAO;
    private MentorGroupDAO mentorGroupDAO;
    private StudentDataDAO studentDataDAO;

    public GroupService(GroupView groupView, MentorView mentorView, GroupDAO groupDAO, UserDAO userDAO,
                        MentorGroupDAO mentorGroupDAO, StudentDataDAO studentDataDAO) {
        this.groupView = groupView;
        this.mentorView = mentorView;
        this.groupDAO = groupDAO;
        this.userDAO = userDAO;
        this.mentorGroupDAO = mentorGroupDAO;
        this.studentDataDAO = studentDataDAO;
    }

    public void createGroup() {

        String name = groupView.getGroupNameInput();
        Group group = new Group(name);
        if (groupDAO.add(group)) {
            groupView.displayGroupAdded();
        } else {
            groupView.displayGroupWithThisNameAlreadyExists();
        }
    }

    public void assignMentorToGroup() {
        List<User> mentors = new ArrayList<>(userDAO.getAllByRole(UserEntry.MENTOR_ROLE));
        groupView.displayEntriesNoInput(mentors);
        if (mentors.isEmpty()) {
            groupView.displayPressAnyKeyToContinueMessage();
            return;
        }
        String mentorLogin = mentorView.getMentorLoginToAssignGroup();
        if (userDAO.getByLoginAndRole(mentorLogin, UserEntry.MENTOR_ROLE) != null) {
            choseGroupAndAssignToMentor(mentorLogin);
        } else {
            mentorView.displayThereIsNoMentorWithThisLogin();
        }
    }

    private void choseGroupAndAssignToMentor(String mentorLogin) {
        List<Group> groups = new ArrayList<>(groupDAO.getAll());
        groupView.displayEntriesNoInput(groups);
        if (groups.isEmpty()) {
            groupView.displayPressAnyKeyToContinueMessage();
            return;
        }
        String groupName = groupView.getGroupNameInput();
        if (groupDAO.getByName(groupName) != null) {
            Group group = groupDAO.getByName(groupName);
            User mentor = userDAO.getByLogin(mentorLogin);
            boolean isAdded = mentorGroupDAO.add(group.getId(), mentor.getId());
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
        List<User> mentors = new ArrayList<>(userDAO.getAllByRole(UserEntry.MENTOR_ROLE));
        groupView.displayEntriesNoInput(mentors);
        if (mentors.isEmpty()) {
            groupView.displayPressAnyKeyToContinueMessage();
            return;
        }
        String mentorLogin = mentorView.getMentorLoginToRevokeFromGroup();
        if (userDAO.getByLoginAndRole(mentorLogin, UserEntry.MENTOR_ROLE) != null) {
            choseGroupAndRevokeMentor(mentorLogin);
        } else {
            mentorView.displayThereIsNoMentorWithThisLogin();
        }
    }

    private void choseGroupAndRevokeMentor(String mentorLogin) {
        List<Group> groups = new ArrayList<>(groupDAO.getAll());
        groupView.displayEntriesNoInput(groups);
        if (groups.isEmpty()) {
            groupView.displayPressAnyKeyToContinueMessage();
            return;
        }
        String groupName = groupView.getGroupNameInput();
        if (groupDAO.getByName(groupName) != null) {
            Group group = groupDAO.getByName(groupName);
            User mentor = userDAO.getByLogin(mentorLogin);
            boolean isRemoved = mentorGroupDAO.delete(group.getId(), mentor.getId());
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
        List<Group> groups = new ArrayList<>(groupDAO.getAll());
        groupView.displayEntriesNoInput(groups);
        if (groups.isEmpty()) {
            groupView.displayPressAnyKeyToContinueMessage();
            return;
        }
        String groupName = groupView.getGroupNameInput();
        Group group = groupDAO.getByName(groupName);
        if (group != null) {
            groupDAO.delete(group);
            groupView.displayGroupDeleted();
        } else {
            groupView.displayThereIsNoGroupWithThisName();
        }
    }

    public void showMentorGroups(int mentorID) {
        List<String> groupsNames = new ArrayList<>(groupDAO.getGroupsNamesByMentorId(mentorID));
        if (!groupsNames.isEmpty()) {
            for (String groupName : groupsNames) {
                groupView.displayGroupName(groupName);
                Group group = groupDAO.getByName(groupName);
                if (!userDAO.getStudentsByGroupId(group.getId()).isEmpty()) {
                    List<User> students = new ArrayList<>(userDAO.getStudentsByGroupId(group.getId()));
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

        List<User> students = new ArrayList<>(userDAO.getAllByRole(UserEntry.STUDENT_ROLE));
        mentorView.displayEntriesNoInput(students);
        if (students.isEmpty()) {
            mentorView.displayPressAnyKeyToContinueMessage();
            return;
        }
        String studentLogin = mentorView.getStudentLoginToAssignGroup();
        if (userDAO.getByLoginAndRole(studentLogin, UserEntry.STUDENT_ROLE) != null) {
            choseGroupAndAssignToStudent(studentLogin);
        } else {
            mentorView.displayThereIsNoStudentWithThisLogin();
        }
    }

    private void choseGroupAndAssignToStudent(String studentLogin) {

        List<Group> groups = new ArrayList<>(groupDAO.getAll());
        mentorView.displayEntriesNoInput(groups);
        if (groups.isEmpty()) {
            mentorView.displayPressAnyKeyToContinueMessage();
            return;
        }
        String groupName = mentorView.getGroupNameInput();
        if (groupDAO.getByName(groupName) != null) {
            Group group = groupDAO.getByName(groupName);
            User student = userDAO.getByLogin(studentLogin);
            StudentData studentData = studentDataDAO.getStudentDataBy(student.getId());
            studentData.setGroupId(group.getId());
            boolean isUpdated = studentDataDAO.updateStudentData(studentData);
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
