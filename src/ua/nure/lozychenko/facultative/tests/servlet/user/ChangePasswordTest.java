package ua.nure.lozychenko.facultative.tests.servlet.user;

import org.junit.jupiter.api.Test;
import ua.nure.lozychenko.facultative.db.entity.User;

import static org.junit.jupiter.api.Assertions.*;

class ChangePasswordTest {

    @Test
    void doPost() {
        User user = new User(0, "login123", "password", null, false, "andrew", "lozychenko", "");
        assertEquals(null, user.validate(), "must be null");
        user = new User(0, null, "password", null, false, "andrew", "lozychenko", "");
        assertEquals("login,empty", user.validate(), "must be login,empty");
        user = new User(0, "login123", "pass", null, false, "andrew", "lozychenko", "");
        assertEquals("password,short",user.validate(),"must be password,short");
    }
}