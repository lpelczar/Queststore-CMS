package queststore.dao;


import queststore.models.ExpLevel;

import java.util.List;

public interface ExpLevelsDAO {

    List<ExpLevel> getAll();
    ExpLevel getByName(String levelName);
    boolean add(ExpLevel expLevel);
    boolean delete(String levelName);
}
