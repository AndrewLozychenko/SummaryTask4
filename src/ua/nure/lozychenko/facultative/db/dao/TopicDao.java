package ua.nure.lozychenko.facultative.db.dao;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.db.entity.Topic;

import java.util.List;

public interface TopicDao {
    List<Topic> getAll() throws DBException;

    Topic get(long id) throws DBException;

    void save(Topic topic) throws DBException;

    void update(Topic oldTopic, Topic newTopic) throws DBException;

    void delete(Topic topic) throws DBException;
}
