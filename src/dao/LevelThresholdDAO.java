package dao;
import java.util.*;

public class LevelThresholdDAO extends AbstractDAO {

    private static Map<String, Integer> levelThreshold = new HashMap<>();
    private final String FILE_PATH = "src/data/levelThresholds.ser";

    public void setThreshold(String level, Integer threshold) {
        levelThreshold.put(level, threshold);
        saveAllData(this.getLevelThresholds(), FILE_PATH);
    }

    public Map<String, Integer> getLevelThresholds() {
        readThreshold();
        return levelThreshold;
    }
}
