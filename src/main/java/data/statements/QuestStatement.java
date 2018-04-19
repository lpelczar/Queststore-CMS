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

    public String updateQuestStatement() {
        return "UPDATE quests SET quest_id = ?, name = ?, description = ?, price = ? WHERE quest_id = ?; ";
    }

    public String deleteQuestStatement() {
        return "DELETE FROM quests WHERE quest_id = ?; ";
    }
}
