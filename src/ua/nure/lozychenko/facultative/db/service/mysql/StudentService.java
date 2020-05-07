package ua.nure.lozychenko.facultative.db.service.mysql;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.db.Fields;
import ua.nure.lozychenko.facultative.db.dao.StudentDao;
import ua.nure.lozychenko.facultative.db.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentService extends Service<Student> implements StudentDao {
    private static final String SQL_GET_ALL = "SELECT * FROM student";

    private static final String SQL_GET_BY_ID = "SELECT * FROM student " +
            "WHERE id = ?";

    private static final String SQL_GET = "SELECT * FROM student " +
            "WHERE user_id = ? " +
            "AND course_id = ?";

    private static final String SQL_SAVE = "INSERT INTO student" +
            "(user_id, course_id) " +
            "VALUES(?, ?)";

    private static final String SQL_DELETE = "DELETE FROM student " +
            "WHERE id = ?";

    @Override
    public List<Student> getAll() throws DBException {
        return queryList(SQL_GET_ALL, this::unpack);
    }

    @Override
    public Student get(long id) throws DBException {
        return query(SQL_GET_BY_ID, this::unpack, id);
    }

    @Override
    public Student get(long userId, long courseId) throws DBException {
        return query(SQL_GET, this::unpack, userId, courseId);
    }

    @Override
    public void save(Student student) throws DBException {
        query(SQL_SAVE,
                student.getUser().getId(),
                student.getCourse().getId()
        );
    }

    @Override
    public void delete(Student student) throws DBException {
        query(SQL_DELETE,
                student.getId()
        );
    }

    private Student unpack(ResultSet resultSet) throws SQLException, DBException {
        return new Student(
                resultSet.getLong(Fields.Student.ID),
                new UserService().get(resultSet.getLong(Fields.Student.STUDENT_ID)),
                new CourseService().get(resultSet.getLong(Fields.Student.COURSE_ID))
        );
    }
}
