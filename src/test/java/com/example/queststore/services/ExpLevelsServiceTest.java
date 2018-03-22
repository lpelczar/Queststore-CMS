package com.example.queststore.services;

import com.example.queststore.dao.DbExpLevelsDAO;
import com.example.queststore.data.DbHelper;
import com.example.queststore.models.ExpLevel;
import com.example.queststore.views.ExpLevelsView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

class ExpLevelsServiceTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(new File(DbHelper.getDatabasePath()).toPath());
    }

    @BeforeEach
    void dbSetup() throws IOException {
        DbHelper.setDatabasePath("test.db");

        Files.deleteIfExists(new File(DbHelper.getDatabasePath()).toPath());
        DbHelper dbHelper = new DbHelper();
        dbHelper.createDatabase();
    }

    @Test
    void testIfAddLevelOfExperienceCompletes() {
        DbExpLevelsDAO testDao = new DbExpLevelsDAO();

        ExpLevelsService service = new ExpLevelsService(testDao);
        boolean actual = service.addLevelOfExperience("test", 1);
        Assertions.assertTrue(actual);
    }

    @Test
    void testIfRemoveLevelOfExperienceCompletes() {
        DbExpLevelsDAO testDao = new DbExpLevelsDAO();
        ExpLevelsService service = new ExpLevelsService(testDao);

        service.addLevelOfExperience("test", 1);
        boolean actual = service.removeLevelOfExperience("test");
        Assertions.assertTrue(actual);
    }


    @Test
    void testGetSingleLevelOfExperience() {
        DbExpLevelsDAO testDao = new DbExpLevelsDAO();
        ExpLevelsService service = new ExpLevelsService(testDao);

        service.addLevelOfExperience("test1", 1);
        service.addLevelOfExperience("test3", 3);

        List<ExpLevel> levels = service.getAllLevelsOfExperience();
        Assertions.assertEquals("test3", levels.get(1).getName());
    }

    @Test
    void testGetAllLevelsOfExperience() {
        DbExpLevelsDAO testDao = new DbExpLevelsDAO();
        ExpLevelsService service = new ExpLevelsService(testDao);

        service.addLevelOfExperience("test1", 1);
        service.addLevelOfExperience("test2", 2);
        service.addLevelOfExperience("test3", 3);

        List<ExpLevel> actualLevels = service.getAllLevelsOfExperience();
        List<ExpLevel> expectedLevels = new ArrayList<>();

        ExpLevel lvl1 = new ExpLevel("test1", 1);
        ExpLevel lvl2 = new ExpLevel("test2", 2);
        ExpLevel lvl3 = new ExpLevel("test3", 3);

        expectedLevels.add(lvl1);
        expectedLevels.add(lvl2);
        expectedLevels.add(lvl3);

        Assertions.assertArrayEquals(new List[]{expectedLevels}, new List[]{actualLevels});
    }

}