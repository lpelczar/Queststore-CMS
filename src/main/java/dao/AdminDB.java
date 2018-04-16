package dao;

import model.AdminModel;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Set;

public interface AdminDB {

    void findAllDataOfAdmin(int admin_id);
    void insertAdminData(String name, String lastname, String email);
    ArrayList<String[]> getMentorsDataFromDatabase(int roleToFind);
    void updateUserLogin(String login, String user_id);
    void updateUserPassword(String newPassword, String user_id);
    void updateMentorsName(String newName, String user_id);
    void updateMentorsLastName(String newLastName, String user_id);
    void updateMentorsEmail(String newEmail, String user_id);
    AdminModel loadAdmin(int id);
    void createNewGroupAndAssignMentorToIt(String newGroup, String mentorId);
    ArrayList<String> getIdsOfMentorsHavingGroupsAlready();
    void exportAdmin(AdminModel admin);
}
