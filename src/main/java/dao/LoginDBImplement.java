package dao;

import model.AdminModel;
import model.MentorModel;
import model.StudentModel;
import model.UserModel;
import utils.ProcessManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class LoginDBImplement extends OpenCloseConnectionWithDB implements LoginDB {

    private ProcessManager processManager = new ProcessManager();

    public String[] findUserIdAndRole(String login, String password) {

        String sqlStatement = "SELECT user_id, role FROM logins WHERE login = ? AND password = ?;";
        int idColumn = 0;
        int roleColumn = 1;

        int arrayCapacity = 2;
        String[] idAndRole = new String[arrayCapacity];

        try {
            openConnection();
            PreparedStatement pstmt = processManager.getPreparedStatement(sqlStatement, login, password);

            ResultSet rs  = pstmt.executeQuery();

            while (rs.next()) {
                String userId = rs.getString("user_id");
                String userRole = rs.getString("role");
                idAndRole[idColumn] = userId;
                idAndRole[roleColumn] = userRole;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            closeConnection(connection);
        }

        return idAndRole;
    }

    public ArrayList<String[]> getExistingIdsLoginAndPasswords(int roleToFind) {
        String sql = "SELECT * FROM logins WHERE role = "+roleToFind;
        int idColumn = 0;
        int loginColumn = 1;
        int passwordColumn = 2;
        String[] idLoginAndPassword = new String[3];
        ArrayList<String[]> allIdsLoginsAndPasswords = new ArrayList<>();


        try {
            openConnection();

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                idLoginAndPassword[idColumn] = rs.getString("user_id");
                idLoginAndPassword[loginColumn] = rs.getString("login");
                idLoginAndPassword[passwordColumn] = rs.getString("password");
                allIdsLoginsAndPasswords.add(idLoginAndPassword);
            }
        }

        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        } finally {
            closeConnection(connection);
        }

        return allIdsLoginsAndPasswords;
    }

    public ArrayList<String[]> getExistingNamesLastnamesAndEmails(String tableToGetFrom){
        String sql = "SELECT * FROM "+tableToGetFrom;
        int id = 0;
        int name = 1;
        int lastname = 2;
        int email = 3;
        String[] nameLastnameAndEmail = new String[4];
        ArrayList<String[]> allNamesLastnamesAndEmails = new ArrayList<>();
        String columnWithId = null;

        if (tableToGetFrom.equals("admins")){
            columnWithId = "admin_id";
        }
        else if (tableToGetFrom.equals("mentors")){
            columnWithId = "mentor_id";
        }
        else if (tableToGetFrom.equals("students")){
            columnWithId = "student_id";
        }

        openConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                nameLastnameAndEmail[id] = rs.getString(columnWithId);
                nameLastnameAndEmail[name] = rs.getString("name");
                nameLastnameAndEmail[lastname] = rs.getString("lastname");
                nameLastnameAndEmail[email] = rs.getString("email");
                allNamesLastnamesAndEmails.add(nameLastnameAndEmail);
            }
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        } finally {
            closeConnection(connection);
        }

        return allNamesLastnamesAndEmails;
    }


    public void insertAllLoginData(String login, String password, String role){
        String sql = "INSERT INTO logins(login, password, role) VALUES(?, ?, ?);";

        openConnection();

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            pstmt.setString(3, role);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            closeConnection(connection);
        }
    }

    public void saveNewUserToDatabase(UserModel user){
        String Id = user.getId();
        String login = user.getLogin();
        String password = user.getPassword();
        String name = user.getName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        String role = null;
        String sqlQuerry2 = "";

        if (user instanceof MentorModel) {
            role = "2";
            sqlQuerry2 = "INSERT INTO mentors(mentor_id, name, lastname, email) VALUES(?, ?, ?, ?);";
        } else if (user instanceof AdminModel) {
            role = "1";
            sqlQuerry2 = "INSERT INTO admins(admin_id, name, lastname, email) VALUES(?, ?, ?, ?);";
        } else if (user instanceof StudentModel) {
            role = "3";
            sqlQuerry2 = "INSERT INTO students(student_id, name, lastname, email) VALUES(?, ?, ?, ?);";
        }

        String sqlQuerry1 = "INSERT INTO logins(user_id, login, password, role) VALUES(?, ?, ?, ?);";

        openConnection();

        try (PreparedStatement pstmt1 = connection.prepareStatement(sqlQuerry1)) {
            pstmt1.setString(1, Id);
            pstmt1.setString(2, login);
            pstmt1.setString(3, password);
            pstmt1.setString(4, role);
            pstmt1.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try (PreparedStatement pstmt2 = connection.prepareStatement(sqlQuerry2)) {
            pstmt2.setString(1, Id);
            pstmt2.setString(2, name);
            pstmt2.setString(3, lastName);
            pstmt2.setString(4, email);
            pstmt2.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public void updateUserLoginAndPassword(String login, String password, int user_id) {

        String sql = "UPDATE logins SET login=?, password=? WHERE user_id=?;";
        openConnection();

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            pstmt.setInt(3,user_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public void deleteAllUserLoginData(int user_id) {

        String sql = "DELETE FROM logins WHERE user_id= ? ;";
        openConnection();


        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, user_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public String getLastId() {
        String sql = "SELECT user_id FROM logins ORDER BY user_id ASC;";
        int idColumn = 0;
        String lastId = null;

        openConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                lastId = rs.getString("user_id");
            }
        }

        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        finally {
            closeConnection(connection);
        }
        return lastId;
    }

    public Set<String> getExistingGroups() {
        String sql = "SELECT signature FROM group_names";
        Set<String> existingGroups = new TreeSet<>();

        openConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                existingGroups.add(rs.getString("signature"));
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            closeConnection(connection);
        }
        return existingGroups;
    }
}
