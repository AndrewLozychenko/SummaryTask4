package ua.nure.lozychenko.facultative.db.dao;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.db.entity.Course;
import ua.nure.lozychenko.facultative.db.entity.Topic;
import ua.nure.lozychenko.facultative.db.entity.User;

import java.util.List;

public interface CourseDao {

    List<Course> getAll(User teacher, Topic topic, String sort) throws DBException;

    List<Course> getJoined(long studentId, User teacher, Topic topic, String sort) throws DBException;

    List<Course> getNotJoined(long studentId, User teacher, Topic topic, String sort) throws DBException;

    List<Course> getStarted(long studentId, User teacher, Topic topic, String sort) throws DBException;

    List<Course> getNotStarted(long studentId, User teacher, Topic topic, String sort) throws DBException;

    List<Course> getCompleted(long studentId, User teacher, Topic topic, String sort) throws DBException;

    List<Course> getTeaching(long teacherId) throws DBException;

    Course get(long id) throws DBException;

    void save(Course course) throws DBException;

    void update(Course oldCourse, Course newCourse) throws DBException;

    void delete(Course course) throws DBException;
}
