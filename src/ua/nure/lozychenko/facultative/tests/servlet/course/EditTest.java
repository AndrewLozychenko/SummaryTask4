package ua.nure.lozychenko.facultative.tests.servlet.course;

import org.junit.jupiter.api.Test;
import ua.nure.lozychenko.facultative.db.entity.Course;
import ua.nure.lozychenko.facultative.db.entity.Topic;
import ua.nure.lozychenko.facultative.db.entity.User;

import static org.junit.jupiter.api.Assertions.*;

class EditTest {

    @Test
    void doPost() {
        Course course = new Course(0,
                null,
                new User(0, "", "", null, false, "", "", ""),
                new Topic(0, ""),
                "2020-01-01",
                "2020-12-12",
                50,
                0);
        assertEquals("name", course.validate(), "must be name");

        course = new Course(0,
                "course",
                new User(0, "", "", null, false, "", "", ""),
                new Topic(0, ""),
                null,
                "2020-12-12",
                50,
                0);
        assertEquals("begin", course.validate(), "must be begin");

        course = new Course(0,
                "course",
                new User(0, "", "", null, false, "", "", ""),
                new Topic(0, ""),
                "2020-01-01",
                null,
                50,
                0);
        assertEquals("end", course.validate(), "must be end");

        course = new Course(0,
                "course",
                new User(0, "", "", null, false, "", "", ""),
                new Topic(0, ""),
                "2020-10-10",
                "2020-01-01",
                50,
                0);
        assertEquals("wrong_date", course.validate(), "must be wrong_date");

        course = new Course(0,
                "course",
                new User(0, "", "", null, false, "", "", ""),
                new Topic(0, ""),
                "2020-01-01",
                "2020-12-12",
                50,
                0);

        assertEquals(null, course.validate(), "must be null");
    }
}