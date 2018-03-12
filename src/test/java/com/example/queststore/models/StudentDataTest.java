package com.example.queststore.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentDataTest {
    private StudentData studentData;

    @BeforeEach
    void createInstance() {
        studentData = new StudentData(
                1,
                1,
                "teamName",
                "beginner",
                1500,
                100
        );
    }

    @Test
    void getLevelTest() {
        assertEquals("beginner", studentData.getLevel());
    }

    @Test
    void getBalanceTest() {
        Integer balanceExpected = 1500;
        assertEquals(balanceExpected, studentData.getBalance());
    }

    @Test
    void getTeamNameTest() {
        assertEquals("teamName", studentData.getTeamName());
    }

    @Test
    void setLevelTest() {
        studentData.setLevel("newLevel");
        assertEquals("newLevel", studentData.getLevel());
    }

    @Test
    void setBalanceTest() {
        Integer newBalance = 2300;
        studentData.setBalance(newBalance);
        assertEquals(newBalance, studentData.getBalance());
    }

    @Test
    void getExperienceTest() {
        assertEquals(100, studentData.getExperience());
    }

    @Test
    void getIdTest() {
        assertEquals(1, studentData.getId());
    }

    @Test
    void getGroupIdTest() {
        Integer groupId = 1;
        assertEquals(groupId, studentData.getGroupId());
    }

    @Test
    void setStudentIdTest() {
        studentData.setStudentId(8);
        assertEquals(8, studentData.getId());
    }

    @Test
    void setNegativeIdTest() {
        assertThrows(IllegalArgumentException.class, () -> studentData.setStudentId(-2));
    }

    @Test
    void setTeamNameTest() {
        studentData.setTeamName("newTeamName");
        assertEquals("newTeamName", studentData.getTeamName());
    }

    @Test
    void setGroupIdTest() {
        Integer groupId = 5;
        studentData.setGroupId(groupId);
        assertEquals(groupId, studentData.getGroupId());
    }

    @Test
    void setExperienceTest() {
        studentData.setExperience(850);
        assertEquals(850, studentData.getExperience());
    }
}