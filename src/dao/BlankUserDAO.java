package dao;

import models.*;

import java.util.ArrayList;
import java.util.List;

public class BlankUserDAO extends AbstractDAO {

    private List<BlankUser> blankUserList = new ArrayList<>();
    private final String FILE_PATH = "src/data/blankUsers.ser";

    public BlankUserDAO() {}

    public void addBlankUser(BlankUser user) {

        readAllBlankUsers();
        if(!blankUserList.contains(user)) {
            blankUserList.add(user);
            saveAllBlankUsers();
        }
    }

}
