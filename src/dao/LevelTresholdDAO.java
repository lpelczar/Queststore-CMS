package dao;
import java.util.*;

public class LevelTresholdDAO {
    private static Map<String, Integer> levelTrehsold;

    public void setTreshold(String level, Integer treshold) {
        levelTrehsold.put(level, treshold);
    }

    public Map<String, Integer> getLevelTresholds() {
        return levelTrehsold;
    }
}
