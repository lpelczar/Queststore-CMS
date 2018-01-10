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

    private boolean checkTreshold() {
        ;
    }

    private void updateRank() {
        ;
    }
}
