package dao;

import models.Entry;
import models.Group;

import java.util.List;

public interface GroupDAO {

    List<Entry> getAll();
    Group getByName(String name);
    boolean add(Group group);
    boolean delete(Group group);
}
