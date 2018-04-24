package queststore.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void createInstance() {
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
    void getIdTest() {
        assertEquals(0, user.getId());
    }

    @Test
    void getNameTest() {
        assertEquals("testName", user.getName());
    }

    @Test
    void getLoginTest() {
        assertEquals("testLogin", user.getLogin());
    }

    @Test
    void getEmailTest() {
        assertEquals("testEmail", user.getEmail());
    }

    @Test
    void getPasswordTest() {
        assertEquals("testPassword", user.getPassword());
    }

    @Test
    void getPhoneNumberTest() {
        assertEquals("testPhoneNumber", user.getPhoneNumber());
    }

    @Test
    void getRoleTest() {
        assertEquals("testRole", user.getRole());
    }

    @Test
    void setNameTest() {
        user.setName("newNameTest");
        assertEquals("newNameTest", user.getName());
    }

    @Test
    void setLoginTest() {
        user.setLogin("newLoginTest");
        assertEquals("newLoginTest", user.getLogin());
    }

    @Test
    void setEmailTest() {
        user.setEmail("newEmailTest");
        assertEquals("newEmailTest", user.getEmail());
    }

    @Test
    void setPhoneNumberTest() {
        user.setPhoneNumber("606663119");
        assertEquals("606663119", user.getPhoneNumber());
    }

    @Test
    void setRoleTest() {
        user.setRole("student");
        assertEquals("student", user.getRole());
    }

    @Test
    void toStringTest() {
        String expectedResult = "Name: testName" +
                " | Login: testLogin" +
                " | Email: testEmail" +
                " | Phone number: testPhoneNumber";

        assertEquals(expectedResult, user.toString());
    }

}