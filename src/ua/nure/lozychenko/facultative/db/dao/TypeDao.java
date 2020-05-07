package ua.nure.lozychenko.facultative.db.dao;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.db.entity.Type;

import java.util.List;

public interface TypeDao {
    List<Type> getAll() throws DBException;

    Type get(long id) throws DBException;

    Type get(String name) throws DBException;

    void save(Type type) throws DBException;

    void update(Type oldType, Type newType) throws DBException;

    void delete(Type type) throws DBException;
}
