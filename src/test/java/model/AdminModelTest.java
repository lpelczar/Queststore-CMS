package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AdminModelTest {

    private AdminModel admin;

    @BeforeEach
    void createAdmin() {
        admin = new AdminModel(
                "ID", "login", "password",
                "name", "lastName", "email");
    }

    @Test
    void addMentorTest() {
        MentorModel mentor = mock(MentorModel.class);
        admin.addMentor(mentor);
        assertEquals(1, admin.getMentors().size());
    }

    @Test
    void getTwoMentorsTest() {
        MentorModel mentor1 = mock(MentorModel.class);
        MentorModel mentor2 = mock(MentorModel.class);

        admin.addMentor(mentor1);
        admin.addMentor(mentor2);

        assertEquals(2, admin.getMentors().size());
    }
}