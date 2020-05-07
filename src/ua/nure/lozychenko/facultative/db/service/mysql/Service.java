package ua.nure.lozychenko.facultative.db.service.mysql;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.db.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Service<T> {

    public List<T> queryList(String query, Unpacker<T> unpacker) throws DBException {
        AutoCloseable[] toClose = null;

        List<T> list = new ArrayList<>();

        ResultSet resultSet = null;
        try {
            resultSet = DBManager.query(toClose, query);

            while (resultSet.next()) {
                list.add(unpacker.unpack(resultSet));
            }
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            DBManager.close(resultSet);
            DBManager.close(toClose);
        }

        return list;
    }

    public List<T> queryList(String query, Unpacker<T> unpacker, Object... params) throws DBException {
        AutoCloseable[] toClose = null;

        List<T> list = new ArrayList<>();

        ResultSet resultSet = null;
        try {
            resultSet = DBManager.query(toClose, query, params);

            while (resultSet.next()) {
                list.add(unpacker.unpack(resultSet));
            }
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            DBManager.close(resultSet);
            DBManager.close(toClose);
        }

        return list;
    }

    public T query(String query, Unpacker<T> unpacker, Object... params) throws DBException {
        AutoCloseable[] toClose = null;

        ResultSet resultSet = null;
        try {
            resultSet = DBManager.query(toClose, query, params);

            if (resultSet.next()) {
                return unpacker.unpack(resultSet);
            }
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            DBManager.close(resultSet);
            DBManager.close(toClose);
        }
        return null;
    }


    public void query(String query, Object... params) throws DBException {
        DBManager.execute(query, params);
    }
}
