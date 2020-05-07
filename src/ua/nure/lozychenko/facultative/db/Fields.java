package ua.nure.lozychenko.facultative.db;

public final class Fields {
    public class User {
        public static final String ID = "id";
        public static final String LOGIN = "login";
        public static final String PASSWORD = "password";
        public static final String TYPE = "type_id";
        public static final String BLOCKED = "blocked";
        public static final String NAME = "name";
        public static final String SURNAME = "surname";
        public static final String CONTACTS = "contacts";
    }

    public class Course {
        public static final String ID = "course.id";
        public static final String CID = "c.id";
        public static final String NAME = "name";
        public static final String TOPIC = "topic_id";
        public static final String BEGIN = "begin";
        public static final String END = "end";
        public static final String TEACHER = "teacher_id";
        public static final String PARTY_LIMIT = "party_limit";
    }

    public class Type {
        public static final String ID = "id";
        public static final String NAME = "name";
    }

    public class Topic {
        public static final String ID = "id";
        public static final String NAME = "name";
    }

    public class Student {
        public static final String ID = "student.id";
        public static final String STUDENT_ID = "user_id";
        public static final String COURSE_ID = "course_id";
    }

    public class Journal {
        public static final String ID = "id";
        public static final String STUDENT_ID = "student_id";
        public static final String MARK = "mark";
    }
}
