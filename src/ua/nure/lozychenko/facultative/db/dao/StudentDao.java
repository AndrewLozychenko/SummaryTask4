package ua.nure.lozychenko.facultative.db.dao;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.db.entity.Student;

import java.util.List;

public interface StudentDao {
    List<Student> getAll() throws DBException;

    Student get(long id) throws DBException;

    Student get(long userId, long courseId) throws DBException;

    void save(Student student) throws DBException;

    void delete(Student student) throws DBException;
}
