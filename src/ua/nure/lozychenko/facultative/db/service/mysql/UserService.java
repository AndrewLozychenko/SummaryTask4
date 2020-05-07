package ua.nure.lozychenko.facultative.db.service.mysql;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.db.Fields;
import ua.nure.lozychenko.facultative.db.dao.UserDao;
import ua.nure.lozychenko.facultative.db.entity.User;

import java.util.List;

public class UserService extends Service<User> implements UserDao {

    private static final String SQL_GET_ALL = "SELECT * FROM user";

    private static final String SQL_GET_ALL_BY_TYPE = "SELECT * FROM user " +
            "WHERE type_id = ?";

    private static final String SQL_GET_BY_ID = "SELECT * FROM user " +
            "WHERE id = ?";

    private static final String SQL_GET_BY_LOGIN = "SELECT * FROM user " +
            "WHERE login = ?";

    private static final String SQL_SAVE = "INSERT INTO user" +
            "(login, password, type_id, name, surname, contacts) " +
            "VALUES(?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE user " +
            "SET login = ?, password = ?, type_id = ?, blocked = ?, name = ?, surname = ?, contacts = ? " +
            "WHERE id = ?";

    private static final String SQL_DELETE = "DELETE FROM user " +
            "WHERE id = ?";

    @Override
    public List<User> getAll() throws DBException {
        return queryList(SQL_GET_ALL, resultSet ->
                new User(
                        resultSet.getLong(Fields.User.ID),
                        resultSet.getString(Fields.User.LOGIN),
                        resultSet.getString(Fields.User.PASSWORD),
                        new TypeService().get(resultSet.getLong(Fields.User.TYPE)),
                        resultSet.getBoolean(Fields.User.BLOCKED),
                        resultSet.getString(Fields.User.NAME),
                        resultSet.getString(Fields.User.SURNAME),
                        resultSet.getString(Fields.User.CONTACTS)
                )
        );
    }

    @Override
    public List<User> getAll(long type_id) throws DBException {
        return queryList(SQL_GET_ALL_BY_TYPE, resultSet ->
                new User(
                        resultSet.getLong(Fields.User.ID),
                        resultSet.getString(Fields.User.LOGIN),
                        resultSet.getString(Fields.User.PASSWORD),
                        new TypeService().get(resultSet.getLong(Fields.User.TYPE)),
                        resultSet.getBoolean(Fields.User.BLOCKED),
                        resultSet.getString(Fields.User.NAME),
                        resultSet.getString(Fields.User.SURNAME),
                        resultSet.getString(Fields.User.CONTACTS)
                ), type_id
        );
    }

    @Override
    public User get(long id) throws DBException {
        return query(SQL_GET_BY_ID, resultSet ->
                new User(
                        resultSet.getLong(Fields.User.ID),
                        resultSet.getString(Fields.User.LOGIN),
                        resultSet.getString(Fields.User.PASSWORD),
                        new TypeService().get(resultSet.getLong(Fields.User.TYPE)),
                        resultSet.getBoolean(Fields.User.BLOCKED),
                        resultSet.getString(Fields.User.NAME),
                        resultSet.getString(Fields.User.SURNAME),
                        resultSet.getString(Fields.User.CONTACTS)
                ), id
        );
    }

    @Override
    public User get(String login) throws DBException {
        return query(SQL_GET_BY_LOGIN, resultSet ->
                new User(
                        resultSet.getLong(Fields.User.ID),
                        resultSet.getString(Fields.User.LOGIN),
                        resultSet.getString(Fields.User.PASSWORD),
                        new TypeService().get(resultSet.getLong(Fields.User.TYPE)),
                        resultSet.getBoolean(Fields.User.BLOCKED),
                        resultSet.getString(Fields.User.NAME),
                        resultSet.getString(Fields.User.SURNAME),
                        resultSet.getString(Fields.User.CONTACTS)
                ), login
        );
    }

    @Override
    public void save(User user) throws DBException {
        query(SQL_SAVE,
                user.getLogin(),
                user.getPassword(),
                user.getType().getId(),
                user.getName(),
                user.getSurname(),
                user.getContacts()
        );
    }

    @Override
    public void update(User oldUser, User newUser) throws DBException {
        query(SQL_UPDATE,
                newUser.getLogin(),
                newUser.getPassword(),
                newUser.getType().getId(),
                newUser.isBlocked(),
                newUser.getName(),
                newUser.getSurname(),
                newUser.getContacts(),
                oldUser.getId()
        );
    }

    @Override
    public void delete(User user) throws DBException {
        query(SQL_DELETE,
                user.getId()
        );
    }
}
