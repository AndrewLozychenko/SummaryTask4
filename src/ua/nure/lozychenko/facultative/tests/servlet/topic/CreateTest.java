package ua.nure.lozychenko.facultative.tests.servlet.topic;

import org.junit.jupiter.api.Test;
import ua.nure.lozychenko.facultative.db.entity.Topic;

import static org.junit.jupiter.api.Assertions.*;

class CreateTest {

    @Test
    void doPost() {
        Topic topic = new Topic(0, "topic");
        assertEquals(null, topic.validate(), "must be null");

        topic = new Topic(0, null);
        assertEquals("name", topic.validate(), "must be name");
    }
}