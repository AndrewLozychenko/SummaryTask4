package ua.nure.lozychenko.facultative.db.dao;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.db.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAll() throws DBException;

    List<User> getAll(long typeId) throws DBException;

    User get(long id) throws DBException;

    User get(String login) throws DBException;

    void save(User user) throws DBException;

    void update(User oldUser, User newUser) throws DBException;

    void delete(User user) throws DBException;
}
