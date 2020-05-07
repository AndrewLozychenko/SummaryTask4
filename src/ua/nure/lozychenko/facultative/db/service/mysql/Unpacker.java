package ua.nure.lozychenko.facultative.db.service.mysql;

import ua.nure.lozychenko.facultative.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Unpacker<T> {
    T unpack(ResultSet resultSet) throws SQLException, DBException;
}
