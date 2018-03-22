package com.example.queststore.services;

import com.example.queststore.dao.DbExpLevelsDAO;
import com.example.queststore.data.DbHelper;
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

import static org.mockito.Mockito.when;

class ExpLevelsServiceTest {

    @Mock
    private ExpLevelsView mockView;

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
    void testIfAddLevelOfExperienceCompletes() { // Naming convention?
        when(mockView.getLevelNameInput()).thenReturn("test");
        when(mockView.getLevelValueInput()).thenReturn(1);

        DbExpLevelsDAO testDao = new DbExpLevelsDAO();

        ExpLevelsService service = new ExpLevelsService(mockView, testDao);
        boolean actual = service.addLevelOfExperience(mockView.getLevelNameInput(), mockView.getLevelValueInput());
        Assertions.assertTrue(actual);
    }

    @Test
    void testIfRemoveLevelOfExperienceCompletes() {
        when(mockView.getLevelNameInput()).thenReturn("test");
        when(mockView.getLevelValueInput()).thenReturn(1);

        DbExpLevelsDAO testDao = new DbExpLevelsDAO();

        ExpLevelsService service = new ExpLevelsService(mockView, testDao);
        service.addLevelOfExperience("test", 1);
        boolean actual = service.removeLevelOfExperience("test");
        Assertions.assertTrue(actual);
    }
}