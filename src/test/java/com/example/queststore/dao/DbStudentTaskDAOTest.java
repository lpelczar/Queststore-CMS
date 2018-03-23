package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class DbStudentTaskDAOTest {

    @BeforeEach
    public void dbSetup() throws IOException {

        DbHelper.setDatabasePath("test.db");

        Files.deleteIfExists(new File(DbHelper.getDatabasePath()).toPath());

        DbHelper dbHelper = new DbHelper();
        dbHelper.createDatabase();

    }

    @Test
    public void addNonExistentItemOfNonExistentStudentShouldFail() {

        StudentTaskDAO studentTaskDao = new DbStudentTaskDAO();
        assertFalse(studentTaskDao.add(-1,-1));
    }


}