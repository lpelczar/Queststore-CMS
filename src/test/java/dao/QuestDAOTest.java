package dao;

import model.QuestModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.DatabaseCreator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestDAOTest {

    private QuestDAO questDAO;

    @BeforeAll
    static void beforeAll() throws IOException {
        String testDbPath = "testDb.db";
        Files.deleteIfExists(new File(testDbPath).toPath());
        OpenCloseConnectionWithDB.setDatabasePath(testDbPath);
        DatabaseCreator.setDatabasePath(testDbPath);
        new DatabaseCreator().createDatabaseFromScript("CreateTables.sql");
    }

    @BeforeEach
    void beforeEach() {
        truncateAllTables();
        questDAO = new SqliteQuestDAO();
    }

    @Test
    void savingNewQuestToDbAndGettingQuestByIdTest() {
        QuestModel expected = new QuestModel("1", "TestQuest1", "TestDesc1", 15);
        questDAO.saveNewQuestToDatabase(expected);
        QuestModel result = questDAO.getQuestById(1);
        assertEquals(expected, result);
    }

    @Test
    void updatingQuestTest() {
        QuestModel questModel1 = new QuestModel("1", "TestQuest1", "TestDesc1", 15);
        questDAO.saveNewQuestToDatabase(questModel1);
        questModel1.setDescription("NewDescription");
        questModel1.setName("NewName");
        questModel1.setPrice(150);
        questDAO.updateEditedQuestInDatabase(questModel1);
        assertEquals(questModel1, QuestModel.getQuests().get(0));
    }

    @Test
    void gettingLastIdIfNoEntitiesInDbTest() {
        Integer result = questDAO.getLastId();
        assertEquals(new Integer(0), result);
    }

    @Test
    void gettingLastIdTest() {
        QuestModel questModel1 = new QuestModel("1", "TestQuest1", "TestDesc1", 15);
        QuestModel questModel2 = new QuestModel("2", "TestQuest2", "TestDesc2", 15);
        questDAO.saveNewQuestToDatabase(questModel1);
        questDAO.saveNewQuestToDatabase(questModel2);
        Integer expected = 2;
        Integer result = questDAO.getLastId();
        assertEquals(expected, result);
    }

    private void truncateAllTables() {
        String truncateQuests = "DELETE FROM quests;";
        String resetRowIdQuests = "DELETE FROM sqlite_sequence WHERE name= 'quests';";
        executeStatement(truncateQuests);
        executeStatement(resetRowIdQuests);
    }

    private void executeStatement(String sqlStatement) {
        try (PreparedStatement ps = new OpenCloseConnectionWithDB().getConnection().prepareStatement(sqlStatement)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
