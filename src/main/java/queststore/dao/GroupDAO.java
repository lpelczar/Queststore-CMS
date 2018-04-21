package queststore.dao;


import queststore.models.Group;

import java.util.List;

public interface GroupDAO {

    List<Group> getAll();
    Group getByName(String name);
    boolean add(Group group);
    boolean delete(Group group);
    List<String> getGroupsNamesByMentorId(int mentorID);
}
