package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;


class DbStudentItemDAOTest {

    @BeforeEach
    public void dbSetup() throws IOException {

        DbHelper.setDatabasePath("test.db");

        Files.deleteIfExists(new File(DbHelper.getDatabasePath()).toPath());

        DbHelper dbHelper = new DbHelper();
        dbHelper.createDatabase();

    }

    @Test
    public void addNonExistentItemOfNonExistentStudentShouldFail() {

        StudentItemDAO studentItemDao = new DbStudentItemDAO();
        assertFalse(studentItemDao.add(-1,-1, 0));
    }

    @Test
    public void markNonExistentItemOfNonExistentStudentShouldFail() {

        StudentItemDAO studentItemDao = new DbStudentItemDAO();
        assertFalse(studentItemDao.markItemAsUsed(-1,-1));
    }


}