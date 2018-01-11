package dao;

import models.Admin;

import java.util.*;

public class AdminDAO extends AbstractDAO {

    private List<Admin> adminList = new ArrayList<>();
    private final String FILE_PATH = "src/data/admins.ser";

    public AdminDAO() { readAllAdmins();}

    private void saveAllAdmins() {

        saveAllData(this.adminList, FILE_PATH);
    }

    public void addAdmin(Admin admin) {

        readAllAdmins();
        if(!adminList.contains(admin)) {
            adminList.add(admin);
            saveAllAdmins();
        }
    }

}