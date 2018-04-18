package dao;

import model.Group;
import model.GroupModel;

import java.util.List;

public interface GroupDAO {
    void add(GroupModel groupModel);
    List<Group> getAllGroups();
}
