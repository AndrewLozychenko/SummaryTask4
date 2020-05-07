package ua.nure.lozychenko.facultative.db.dao;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.db.entity.Journal;

import java.util.List;

public interface JournalDao {
    List<Journal> getAll() throws DBException;

    Journal get(long id) throws DBException;

    List<Journal> getByStudent(long id) throws DBException;

    List<Journal> getByCourse(long id) throws DBException;

    void save(Journal journal) throws DBException;

    void update(Journal oldJournal, Journal newJournal) throws DBException;

    void delete(Journal journal) throws DBException;
}
