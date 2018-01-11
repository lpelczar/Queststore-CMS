package dao;
import java.util.*;

public class LevelThresholdDAO extends AbstractDAO {

    private static Map<String, Integer> levelThreshold = new HashMap<>();
    private final String FILE_PATH = "src/data/levelThresholds.ser";

    public void setThreshold(String level, Integer threshold) {
        levelThreshold.put(level, threshold);
        saveAllData(LevelThresholdDAO.levelThreshold, FILE_PATH);
    }

    public Map<String, Integer> getLevelThresholds() {
        readThreshold();
        return levelThreshold;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Integer> readThreshold() {

        if (readAllData(FILE_PATH) != null) {
            LevelThresholdDAO.levelThreshold = (Map<String, Integer>) readAllData(FILE_PATH);
        } else {
            LevelThresholdDAO.levelThreshold = new HashMap<>();
        }
    }
}
