package dao;

import model.UserModel;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Set;

public interface UserDAO {

    String[] findUserIdAndRole(String login, String password);
    void insertAllLoginData(String login, String password, String role);
    void updateUserLoginAndPassword(String login, String password, int user_id);
    void deleteAllUserLoginData(int user_id);
    ArrayList<String[]> getExistingNamesLastnamesAndEmails(String tableToGetFrom);
    ArrayList<String[]> getExistingIdsLoginAndPasswords(int roleToFind);
    String getLastId();
    void saveNewUserToDatabase(UserModel user);
    Set<String> getExistingGroups();

}
