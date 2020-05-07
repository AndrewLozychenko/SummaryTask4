package ua.nure.lozychenko.facultative.db.service.mysql;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.constants.Sort;
import ua.nure.lozychenko.facultative.db.Fields;
import ua.nure.lozychenko.facultative.db.dao.CourseDao;
import ua.nure.lozychenko.facultative.db.entity.Course;
import ua.nure.lozychenko.facultative.db.entity.Topic;
import ua.nure.lozychenko.facultative.db.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseService extends Service<Course> implements CourseDao {
    private static final String OUTER_SELECT = "SELECT c.* FROM course, student, (";
    private static final String OUTER_SELECT_END = ") AS c  ";
    private static final String WHERE = " WHERE ";
    private static final String AND = " AND ";
    private static final String FILTER_BY_TEACHER = " c.teacher_id = ? ";
    private static final String FILTER_BY_TOPIC = " c.topic_id = ? ";
    private static final String GROUP_BY_ID = " GROUP BY c.id";

    private static final String SQL_GET_ALL =
            "SELECT c.*,(SELECT COUNT(student.id) FROM student WHERE course_id = c.id) AS cur_stud_count FROM course AS c " +
                    "WHERE c.id NOT IN " +
                    "(SELECT course_id FROM student) " +
                    "OR c.id IN " +
                    "(SELECT course_id FROM student) " +
                    "AND " +
                    "(SELECT COUNT(student.id) FROM student " +
                    "WHERE course_id = c.id) < party_limit";

    private static final String SQL_GET_JOINED = "SELECT DISTINCT course.* FROM course, student " +
            "WHERE course.id = course_id " +
            "AND user_id = ?";

    private static final String SQL_GET_NOT_JOINED =
            "SELECT DISTINCT course.*, (SELECT COUNT(student.id) FROM student WHERE course_id = course.id) AS cur_stud_count FROM course, student " +
                    "WHERE course.id NOT IN " +
                    "(SELECT course.id FROM course, student " +
                    "WHERE course.id = course_id " +
                    "AND user_id = ?) " +
                    "AND (course.id NOT IN" +
                    "(SELECT course_id FROM student) " +
                    "OR course.id IN " +
                    "(SELECT course_id FROM student) " +
                    "AND " +
                    "(SELECT COUNT(student.id) FROM student " +
                    "WHERE course_id = course.id) < party_limit)";

    private static final String SQL_GET_STARTED = SQL_GET_JOINED +
            " AND YEAR(begin) <= YEAR(CURDATE()) " +
            "AND YEAR(end) >= YEAR(CURDATE()) " +
            "AND MONTH(begin) <= MONTH(CURDATE()) " +
            "AND MONTH(end) >= MONTH(CURDATE()) " +
            "AND DAY(begin) < DAY(CURDATE()) " +
            "AND DAY(end) > DAY(CURDATE())";

    private static final String SQL_GET_NOT_STARTED = SQL_GET_JOINED +
            " AND YEAR(begin) >= YEAR(CURDATE()) " +
            "AND MONTH(begin) >= MONTH(CURDATE()) " +
            "AND DAY(begin) > DAY(CURDATE())";

    private static final String SQL_GET_COMPLETED = SQL_GET_JOINED +
            " AND YEAR(end) <= YEAR(CURDATE()) " +
            "AND MONTH(end) <= MONTH(CURDATE()) " +
            "AND DAY(end) < DAY(CURDATE())";

    private static final String SQL_GET_TEACHING = "SELECT * FROM course AS c " +
            "WHERE teacher_id = ?";

    private static final String SQL_GET = "SELECT * FROM course AS c " +
            "WHERE id = ?";

    private static final String SQL_SAVE = "INSERT INTO course" +
            "(name, topic_id, teacher_id, begin, end, party_limit) " +
            "VALUES(?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE course " +
            "SET name = ?, teacher_id = ?, topic_id = ?, begin = ?, end = ?, party_limit = ? " +
            "WHERE id = ?";

    private static final String SQL_DELETE = "DELETE FROM course " +
            "WHERE id = ?";

    public static final String BY_NAME_ASC = " ORDER BY name";
    public static final String BY_NAME_DESC = "ORDER BY name DESC";

    public static final String BY_DURATION_ASC = " ORDER BY DAY(c.end) - DAY(c.begin) + " +
            "(MONTH(c.end) - MONTH(c.begin) * 30) + " +
            "(YEAR(c.end) - YEAR(c.begin) * 365)";
    public static final String BY_DURATION_DESC = " ORDER BY DAY(c.end) - DAY(c.begin) + " +
            "(MONTH(c.end) - MONTH(c.begin) * 30) + " +
            "(YEAR(c.end) - YEAR(c.begin) * 365) DESC";

    public static final String BY_STUDENT_COUNT_ASC = " ORDER BY COUNT" +
            "((SELECT user_id FROM student WHERE course_id = c.id))";
    public static final String BY_STUDENT_COUNT_DESC = " ORDER BY COUNT" +
            "((SELECT user_id FROM student WHERE course_id = c.id)) DESC";

    private String setFilters(String query, User teacher, Topic topic, String sort, boolean withOuterSelect) {
        StringBuilder res = new StringBuilder();
        if (withOuterSelect) {
            res.append(OUTER_SELECT)
                    .append(query)
                    .append(OUTER_SELECT_END);
        } else {
            res.append(query);
        }
        if ((teacher != null && teacher.getId() > 0) && (topic != null && topic.getId() > 0)) {
            res.append(WHERE)
                    .append(FILTER_BY_TEACHER)
                    .append(AND)
                    .append(FILTER_BY_TOPIC);
        } else if ((teacher != null && teacher.getId() > 0) && topic == null) {
            res.append(WHERE)
                    .append(FILTER_BY_TEACHER);
        } else if (teacher == null && (topic != null && topic.getId() > 0)) {
            res.append(WHERE)
                    .append(FILTER_BY_TOPIC);
        }
        if (withOuterSelect) {
            res.append(GROUP_BY_ID);
        }
        if (sort != null) {
            switch (sort) {
                case Sort.BY_NAME_ASC:
                    res.append(BY_NAME_ASC);
                    break;
                case Sort.BY_NAME_DESC:
                    res.append(BY_NAME_DESC);
                    break;
                case Sort.BY_DURATION_ASC:
                    res.append(BY_DURATION_ASC);
                    break;
                case Sort.BY_DURATION_DESC:
                    res.append(BY_DURATION_DESC);
                    break;
                case Sort.BY_STUDENT_COUNT_ASC:
                    res.append(BY_STUDENT_COUNT_ASC);
                    break;
                case Sort.BY_STUDENT_COUNT_DESC:
                    res.append(BY_STUDENT_COUNT_DESC);
                    break;
            }
        }
        System.out.println(res.toString());
        return res.toString();
    }

    private Object[] prepareArgs(User teacher, Topic topic, Object... other) {
        List<Object> args = new ArrayList<>();
        for (int i = 0; i < other.length; i++) {
            args.add(other[i]);
        }
        if (teacher != null) {
            args.add(teacher.getId());
        }
        if (topic != null) {
            args.add(topic.getId());
        }
        return args.toArray();
    }

    @Override
    public List<Course> getAll(User teacher, Topic topic, String sort) throws DBException {
        return queryList(setFilters(SQL_GET_ALL, teacher, topic, sort, false),
                this::unpack,
                prepareArgs(teacher, topic)
        );
    }

    @Override
    public List<Course> getJoined(long studentId, User teacher, Topic topic, String sort) throws DBException {
        return queryList(setFilters(SQL_GET_JOINED, teacher, topic, sort, true),
                this::unpack,
                prepareArgs(teacher, topic, studentId)
        );
    }

    @Override
    public List<Course> getNotJoined(long studentId, User teacher, Topic topic, String sort) throws DBException {
        return queryList(setFilters(SQL_GET_NOT_JOINED, teacher, topic, sort, true),
                this::unpack,
                prepareArgs(teacher, topic, studentId)
        );
    }

    @Override
    public List<Course> getStarted(long studentId, User teacher, Topic topic, String sort) throws DBException {
        return queryList(setFilters(SQL_GET_STARTED, teacher, topic, sort, true),
                this::unpack,
                prepareArgs(teacher, topic, studentId)
        );
    }

    @Override
    public List<Course> getNotStarted(long studentId, User teacher, Topic topic, String sort) throws DBException {
        return queryList(setFilters(SQL_GET_NOT_STARTED, teacher, topic, sort, true),
                this::unpack,
                prepareArgs(teacher, topic, studentId)
        );
    }

    @Override
    public List<Course> getCompleted(long studentId, User teacher, Topic topic, String sort) throws DBException {
        return queryList(setFilters(SQL_GET_COMPLETED, teacher, topic, sort, true),
                this::unpack,
                prepareArgs(teacher, topic, studentId)
        );
    }

    @Override
    public List<Course> getTeaching(long teacherId) throws DBException {
        return queryList(SQL_GET_TEACHING,
                this::unpack,
                teacherId
        );
    }

    @Override
    public Course get(long id) throws DBException {
        return query(SQL_GET,
                this::unpack,
                id
        );
    }

    @Override
    public void save(Course course) throws DBException {
        query(SQL_SAVE,
                course.getName(),
                course.getTopic().getId(),
                course.getTeacher().getId(),
                course.getBegin(),
                course.getEnd(),
                course.getPartyLimit()
        );
    }

    @Override
    public void update(Course oldCourse, Course newCourse) throws DBException {
        query(SQL_UPDATE,
                newCourse.getName(),
                newCourse.getTeacher().getId(),
                newCourse.getTopic().getId(),
                newCourse.getBegin(),
                newCourse.getEnd(),
                newCourse.getPartyLimit(),
                oldCourse.getId()
        );
    }

    @Override
    public void delete(Course course) throws DBException {
        query(SQL_DELETE,
                course.getId()
        );
    }

    private Course unpack(ResultSet resultSet) throws SQLException, DBException {
        try {
            resultSet.findColumn(Fields.Course.CURRENT_STUDENT_COUNT);
            return new Course(
                    resultSet.getLong(Fields.Course.CID),
                    resultSet.getString(Fields.Course.NAME),
                    new UserService().get(resultSet.getLong(Fields.Course.TEACHER)),
                    new TopicService().get(resultSet.getLong(Fields.Course.TOPIC)),
                    resultSet.getString(Fields.Course.BEGIN),
                    resultSet.getString(Fields.Course.END),
                    resultSet.getInt(Fields.Course.PARTY_LIMIT),
                    resultSet.getInt(Fields.Course.CURRENT_STUDENT_COUNT)
            );
        } catch (SQLException e) {
            return new Course(
                    resultSet.getLong(Fields.Course.CID),
                    resultSet.getString(Fields.Course.NAME),
                    new UserService().get(resultSet.getLong(Fields.Course.TEACHER)),
                    new TopicService().get(resultSet.getLong(Fields.Course.TOPIC)),
                    resultSet.getString(Fields.Course.BEGIN),
                    resultSet.getString(Fields.Course.END),
                    resultSet.getInt(Fields.Course.PARTY_LIMIT),
                    0
            );
        }
    }
}
