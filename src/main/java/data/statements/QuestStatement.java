package data.statements;

public class QuestStatement {

    public String selectAllQuests() {
        return "SELECT * FROM quests;";
    }

    public String selectQuestById() {
        return "SELECT * FROM quests WHERE quest_id = ?; ";
    }

    public String insertQuestStatement() {
        return "INSERT INTO quests VALUES (?,?,?,?); ";
    }
}
