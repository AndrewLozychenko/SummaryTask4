package ua.nure.lozychenko.facultative.db.service.mysql;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.db.Fields;
import ua.nure.lozychenko.facultative.db.dao.TopicDao;
import ua.nure.lozychenko.facultative.db.entity.Topic;

import java.util.List;

public class TopicService extends Service<Topic> implements TopicDao {
    private static final String SQL_GET_ALL = "SELECT * FROM topic";
    private static final String SQL_GET = "SELECT * FROM topic WHERE id = ?";
    private static final String SQL_SAVE = "INSERT INTO topic(name) VALUES(?)";
    private static final String SQL_UPDATE = "UPDATE topic SET name = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM topic WHERE id = ?";

    @Override
    public List<Topic> getAll() throws DBException {
        return queryList(SQL_GET_ALL, resultSet ->
                new Topic(
                        resultSet.getLong(Fields.Topic.ID),
                        resultSet.getString(Fields.Topic.NAME)
                )
        );
    }

    @Override
    public Topic get(long id) throws DBException {
        return query(SQL_GET, resultSet ->
                new Topic(
                        resultSet.getLong(Fields.Topic.ID),
                        resultSet.getString(Fields.Topic.NAME)
                ), id
        );
    }

    @Override
    public void save(Topic topic) throws DBException {
        query(SQL_SAVE, topic.getName());
    }

    @Override
    public void update(Topic oldTopic, Topic newTopic) throws DBException {
        query(SQL_UPDATE, newTopic.getName(), oldTopic.getId());
    }

    @Override
    public void delete(Topic topic) throws DBException {
        query(SQL_DELETE, topic.getId());
    }
}
