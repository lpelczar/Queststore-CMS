package com.example.queststore.controllers.web;

import com.example.queststore.dao.GroupDAO;
import com.example.queststore.dao.UserDAO;
import com.example.queststore.dao.sqlite.SqliteGroupDAO;
import com.example.queststore.dao.sqlite.SqliteUserDAO;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.models.Group;
import com.example.queststore.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileHandler {
    UserDAO userDAO = new SqliteUserDAO();
    GroupDAO groupDAO = new SqliteGroupDAO();

    public User findUserBy(Integer userId) {
        User user = userDAO.getById(userId);
        if (user != null) {
            return user;
        }
        throw new IllegalArgumentException("There is no user with that's ID!");
    }

    public int getUserIdFrom(Map<String, String> profileData) {
        for (String key : profileData.keySet()) {
            if (profileData.get(key).equals("Accept")) {
                return Integer.valueOf(key);
            }
        }
        throw new IllegalArgumentException("There is no ID of user!");
    }

    public List<User> getAllBlankUsers() {
        return userDAO.getAllByRole(UserEntry.BLANK_USER_ROLE);
    }

    public List<User> getAllMentors() {
        return userDAO.getAllByRole(UserEntry.MENTOR_ROLE);
    }

    public List<String> getGroupsBy(int id) { return groupDAO.getGroupsNamesByMentorId(id); }

    public List<User> getStudentsBy(int groupId) { return userDAO.getStudentsByGroupId(groupId); }

    public List<Group> getAllGroups() {
        GroupDAO groupDAO = new SqliteGroupDAO();
        return groupDAO.getAll();
    }

    public Map<String, List<User>> getStudentsGroups(int mentorId) {
        Map<String, List<User>> groups = new HashMap<>();
        List<String> groupNames = getGroupsBy(mentorId);

        for (String name : groupNames) {
            Group group = groupDAO.getByName(name);
            List<User> students = getStudentsBy(group.getId());

            groups.put(name, students);
        }
        return groups;
    }

    public Map<Group, User> getAllGroupsAssignMentors() {
        Map<Group, User> assignMentorsToGroups = prepareMentorsAssignToGroup();
        List<Group> groups = getAllGroups();

        for (Group group : groups) {
            if (!assignMentorsToGroups.containsKey(group)) {
                assignMentorsToGroups.put(
                        group,
                        null
                );
            }
        }
        return assignMentorsToGroups;
    }

    private Map<Group, User> prepareMentorsAssignToGroup() {
        Map<Integer, Integer> groupMentorsId = groupDAO.getMentorAssignedToGroups();
        Map<Group, User> groupsMentorsNames = new HashMap<>();

        for (Integer groupId : groupMentorsId.keySet()) {
            int mentorId = groupMentorsId.get(groupId);

            Group group = groupDAO.getById(groupId);
            User mentor = userDAO.getById(mentorId);

            groupsMentorsNames.put(
                    group,
                    mentor
            );
        }
        return groupsMentorsNames;
    }

    public User update(Map<String, String> userProfile, User user) {
        for (String key : userProfile.keySet()) {

            switch (key) {
                case "login":
                    user.setLogin(userProfile.get("login"));
                    break;
                case "password":
                    user.setPassword(userProfile.get("password"));
                    break;
                case "name":
                    user.setName(userProfile.get("name"));
                    break;
                case "phone-number":
                    user.setPhoneNumber(userProfile.get("phone-number"));
                    break;
                case "email":
                    user.setEmail(userProfile.get("email"));
                    break;
            }
        }
        return user;
    }

    public void updateDb(User user) {
        UserDAO userDAO = new SqliteUserDAO();
        userDAO.update(user);
    }
}
