package queststore.dao;


import queststore.models.Task;

import java.util.List;

public interface TaskDAO {

    List<Task> getAll();
    Task getByName(String name);
    boolean add(Task task);
    boolean update(Task task);
}
