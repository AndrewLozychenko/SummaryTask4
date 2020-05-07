package ua.nure.lozychenko.facultative.db.service.mysql;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.db.Fields;
import ua.nure.lozychenko.facultative.db.dao.JournalDao;
import ua.nure.lozychenko.facultative.db.entity.Journal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JournalService extends Service<Journal> implements JournalDao {
    private static final String SQL_GET_ALL = "SELECT * FROM journal";

    private static final String SQL_GET = "SELECT * FROM journal " +
            "WHERE id = ?";

    private static final String SQL_GET_BY_STUDENT = "SELECT journal.* FROM journal, student " +
            "WHERE student.id = student_id " +
            "AND user_id = ?";

    private static final String SQL_GET_BY_COURSE = "SELECT journal.* FROM journal, student " +
            "WHERE student_id = student.id " +
            "AND course_id = ?";

    private static final String SQL_SAVE = "INSERT INTO journal(student_id, mark) " +
            "VALUES(?, ?)";

    private static final String SQL_UPDATE = "UPDATE journal " +
            "SET student_id = ?, mark = ? " +
            "WHERE id = ?";

    private static final String SQL_DELETE = "DELETE FROM journal " +
            "WHERE id = ?";

    @Override
    public List<Journal> getAll() throws DBException {
        return queryList(SQL_GET_ALL, this::unpack);
    }

    @Override
    public Journal get(long id) throws DBException {
        return query(SQL_GET, this::unpack, id);
    }

    @Override
    public List<Journal> getByStudent(long id) throws DBException {
        return queryList(SQL_GET_BY_STUDENT, this::unpack, id);
    }

    @Override
    public List<Journal> getByCourse(long id) throws DBException {
        return queryList(SQL_GET_BY_COURSE, this::unpack, id);
    }

    @Override
    public void save(Journal journal) throws DBException {
        query(SQL_SAVE,
                journal.getStudent().getId(),
                journal.getMark());
    }

    @Override
    public void update(Journal oldJournal, Journal newJournal) throws DBException {
        query(SQL_UPDATE,
                newJournal.getStudent().getId(),
                newJournal.getMark(),
                oldJournal.getId()
        );
    }

    @Override
    public void delete(Journal journal) throws DBException {
        query(SQL_DELETE,
                journal.getId());
    }

    private Journal unpack(ResultSet resultSet) throws SQLException, DBException {
        return new Journal(
                resultSet.getLong(Fields.Journal.ID),
                new StudentService().get(resultSet.getLong(Fields.Journal.STUDENT_ID)),
                resultSet.getInt(Fields.Journal.MARK)
        );
    }
}
