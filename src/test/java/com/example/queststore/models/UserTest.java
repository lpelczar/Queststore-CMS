package com.example.queststore.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user;

    @BeforeEach
    public void createInstance() {
        user = new User(
                0,
                "testName",
                "testLogin",
                "testEmail",
                "testPassword",
                "testPhoneNumber",
                "testRole"
        );
    }

    @Test
    public void getIdTest() {
        assertEquals(0, user.getId());
    }

    @Test
    public void getNameTest() {
        assertEquals("testName", user.getName());
    }

    @Test
    public void getLoginTest() {
        assertEquals("testLogin", user.getLogin());
    }

    @Test
    public void getEmailTest() {
        assertEquals("testEmail", user.getEmail());
    }

    @Test
    public void getPasswordTest() {
        assertEquals("testPassword", user.getPassword());
    }

    @Test
    public void getPhoneNumberTest() {
        assertEquals("testPhoneNumber", user.getPhoneNumber());
    }

    @Test
    public void getRoleTest() {
        assertEquals("testRole", user.getRole());
    }

    @Test
    public void setNameTest() {
        user.setName("newNameTest");
        assertEquals("newNameTest", user.getName());
    }

    @Test
    public void setLoginTest() {
        user.setLogin("newLoginTest");
        assertEquals("newLoginTest", user.getLogin());
    }

    @Test
    public void setEmailTest() {
        user.setEmail("newEmailTest");
        assertEquals("newEmailTest", user.getEmail());
    }

    @Test
    public void setPhoneNumberTest() {
        user.setPhoneNumber("606663119");
        assertEquals("606663119", user.getPhoneNumber());
    }

    @Test
    public void setRoleTest() {
        user.setRole("student");
        assertEquals("student", user.getRole());
    }

    @Test
    public void toStringTest() {
        String expectedResult = "Name: testName" +
                " | Login: testLogin" +
                " | Email: testEmail" +
                " | Phone number: testPhoneNumber";

        assertEquals(expectedResult, user.toString());
    }

}