package ua.nure.lozychenko.facultative.constants;

public final class Requests {
    public static final String USER_LIST = "user.list";
    public static final String USER_LIST_ADMINS_FILTERED = "user.list?type=admin";
    public static final String USER_LIST_TEACHERS_FILTERED = "user.list?type=teacher";
    public static final String USER_LIST_STUDENTS_FILTERED = "user.list?type=student";
    public static final String USER_LOGIN = "user.login";
    public static final String USER_CREATE = "user.create";
    public static final String USER_BLOCK = "user.block";
    public static final String USER_REMOVE = "user.remove";

    public static final String TOPIC_LIST = "topic.list";
    public static final String TOPIC_CREATE = "topic.create";
    public static final String TOPIC_EDIT = "topic.edit";
    public static final String TOPIC_REMOVE = "topic.remove";

    public static final String COURSE_LIST = "course.list";
    public static final String COURSE_CREATE = "course.create";
    public static final String COURSE_EDIT = "course.edit";
    public static final String COURSE_REMOVE = "course.remove";
    public static final String COURSE_JOIN = "course.join";
    public static final String COURSE_LEAVE = "course.leave";

    public static final String JOURNAL_SAVE = "journal.save";
    public static final String JOURNAL_LIST = "journal.list";
}
