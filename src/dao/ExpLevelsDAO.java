package dao;

import models.ExpLevel;

import java.util.List;

public interface ExpLevelsDAO {

    List<ExpLevel> getAll();
    boolean add(ExpLevel expLevel);
}
