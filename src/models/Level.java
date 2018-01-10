package models;

import java.util.Map;

public class Level {

    private String rank;
    private Integer experience;

    public Level() {
        this.rank = "beginner";
        this.experience = 0;
    }

    public void gainExp(Integer taskExp) {
        this.experience += taskExp;
    }

    private void updateRank(Map<String, Integer> levelThreshold) {
        for (Integer value : levelThreshold.values()) {
            if (value.equals(experience)) {
                String newRank = getKeyFromValue(levelThreshold, value);
                setRank(newRank);

            }
        }
    }

    private String getKeyFromValue(Map<String, Integer> levelThreshold, Integer value) {
        for (String element : levelThreshold.keySet()) {
            if (levelThreshold.get(element).equals(value)) {
                return element;
            }
        }
        return null;
    }

    private void setRank(String newRank) {
        this.rank = newRank;
    }
}
