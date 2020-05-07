package ua.nure.lozychenko.facultative.db.service.mysql;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.db.Fields;
import ua.nure.lozychenko.facultative.db.dao.TypeDao;
import ua.nure.lozychenko.facultative.db.entity.Type;

import java.util.List;

public class TypeService extends Service<Type> implements TypeDao {

    private static final String SQL_GET_ALL = "SELECT * FROM type";
    private static final String SQL_GET_BY_ID = "SELECT * FROM type WHERE id = ?";
    private static final String SQL_GET_BY_NAME = "SELECT * FROM type WHERE name = ?";
    private static final String SQL_SAVE = "INSERT INTO type(name) VALUES(?)";
    private static final String SQL_UPDATE = "UPDATE type SET name = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM type WHERE id = ?";

    @Override
    public List<Type> getAll() throws DBException {
        return queryList(SQL_GET_ALL, resultSet ->
                new Type(
                        resultSet.getLong(Fields.Type.ID),
                        resultSet.getString(Fields.Type.NAME)
                )
        );
    }

    @Override
    public Type get(long id) throws DBException {
        return query(SQL_GET_BY_ID, resultSet ->
                new Type(
                        resultSet.getLong(Fields.Type.ID),
                        resultSet.getString(Fields.Type.NAME)
                ), id
        );
    }

    @Override
    public Type get(String name) throws DBException {
        return query(SQL_GET_BY_NAME, resultSet ->
                new Type(
                        resultSet.getLong(Fields.Type.ID),
                        resultSet.getString(Fields.Type.NAME)
                ), name
        );
    }

    @Override
    public void save(Type type) throws DBException {
        query(SQL_SAVE, type.getName());
    }

    @Override
    public void update(Type oldType, Type newType) throws DBException {
        query(SQL_UPDATE, newType.getName(), oldType.getId());
    }

    @Override
    public void delete(Type type) throws DBException {
        query(SQL_DELETE, type.getId());
    }
}
