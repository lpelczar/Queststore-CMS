package models;

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

    private void updateRank(Map<String, Integer> levelTrehsold) {
        for (Integer value : levelTrehsold.values()) {
            if value.equals(experience) {
                String newRank = getKeyFromValue(levelTrehsold, value);
                setRank(newRank);

            }
        }
    }

    public String getKeyFromValue(Map<String, Integer>levelTrehsold, String value) {
        for (String element : levelTrehsold.keySet()) {
            if (levelTrehsold.get(element).equals(value)) {
                return element;
            }
        }
        return null;
    }

    private void setRank(String newRank) {
        this.rank = newRank;
    }
}
