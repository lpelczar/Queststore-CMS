package com.example.queststore.controllers.web;

import com.example.queststore.dao.GroupDAO;
import com.example.queststore.dao.UserDAO;
import com.example.queststore.dao.sqlite.SqliteGroupDAO;
import com.example.queststore.dao.sqlite.SqliteUserDAO;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.models.Group;
import com.example.queststore.models.User;

import java.util.List;
import java.util.Map;

public class ProfileEditorHandler {
    UserDAO userDAO = new SqliteUserDAO();

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

    public List<Group> getAllGroups() {
        GroupDAO groupDAO = new SqliteGroupDAO();
        return groupDAO.getAll();
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
