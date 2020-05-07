package ua.nure.lozychenko.facultative.servlet.course;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.constants.Filters;
import ua.nure.lozychenko.facultative.constants.Pages;
import ua.nure.lozychenko.facultative.constants.Parameters;
import ua.nure.lozychenko.facultative.constants.Types;
import ua.nure.lozychenko.facultative.db.dao.CourseDao;
import ua.nure.lozychenko.facultative.db.dao.TopicDao;
import ua.nure.lozychenko.facultative.db.dao.TypeDao;
import ua.nure.lozychenko.facultative.db.dao.UserDao;
import ua.nure.lozychenko.facultative.db.entity.Course;
import ua.nure.lozychenko.facultative.db.entity.User;
import ua.nure.lozychenko.facultative.db.service.mysql.CourseService;
import ua.nure.lozychenko.facultative.db.service.mysql.TopicService;
import ua.nure.lozychenko.facultative.db.service.mysql.TypeService;
import ua.nure.lozychenko.facultative.db.service.mysql.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/course.list")
public class GetList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filter = req.getParameter(Parameters.FILTER);
        req.getSession().setAttribute(Parameters.CURRENT_FILTER, filter);

        long teacherId = 0;
        if (req.getParameter(Parameters.TEACHER) != null) {
            teacherId = Long.parseLong(req.getParameter(Parameters.TEACHER));
            req.getSession().setAttribute(Parameters.CURRENT_TEACHER, req.getParameter(Parameters.TEACHER));
        }

        long topicId = 0;
        if (req.getParameter(Parameters.TOPIC) != null) {
            topicId = Long.parseLong(req.getParameter(Parameters.TOPIC));
            req.getSession().setAttribute(Parameters.CURRENT_TOPIC, req.getParameter(Parameters.TOPIC));
        }

        String sort = req.getParameter(Parameters.SORT);
        req.getSession().setAttribute(Parameters.SORT, sort);

        User user = (User) req.getSession().getAttribute(Parameters.USER);

        CourseDao courseDao = new CourseService();
        UserDao userDao = new UserService();
        TopicDao topicDao = new TopicService();
        TypeDao typeDao = new TypeService();

        List<Course> courses = null;
        try {
            if (Types.STUDENT.equals(user.getType().getName())) {
                if (Filters.ALL.equals(filter)) {
                    courses = courseDao.getNotJoined(user.getId(), userDao.get(teacherId), topicDao.get(topicId), sort);
                    if (courses.size() == 0) {
                        if (courseDao.getAll(userDao.get(teacherId), topicDao.get(topicId), sort).size() != courseDao.getJoined(user.getId(), userDao.get(teacherId), topicDao.get(topicId), sort).size()) {
                            courses = courseDao.getAll(userDao.get(teacherId), topicDao.get(topicId), sort);
                        } else {
                            courses = null;
                        }
                    }
                } else if (Filters.JOINED.equals(filter)) {
                    courses = courseDao.getJoined(user.getId(), userDao.get(teacherId), topicDao.get(topicId), sort);
                } else if (Filters.JOINED_STARTED.equals(filter)) {
                    courses = courseDao.getStarted(user.getId(), userDao.get(teacherId), topicDao.get(topicId), sort);
                } else if (Filters.JOINED_NOT_STARTED.equals(filter)) {
                    courses = courseDao.getNotStarted(user.getId(), userDao.get(teacherId), topicDao.get(topicId), sort);
                }
            } else if (Types.TEACHER.equals(user.getType().getName())) {
                courses = courseDao.getTeaching(user.getId());
            } else if (Types.ADMIN.equals(user.getType().getName())) {
                courses = courseDao.getAll(userDao.get(teacherId), topicDao.get(topicId), sort);
            }

            req.setAttribute(Parameters.COURSES, courses);
            req.setAttribute(Parameters.FILTERS, Filters.getFilters());
            req.setAttribute(Parameters.TEACHERS, userDao.getAll(typeDao.get(Types.TEACHER).getId()));
            req.setAttribute(Parameters.TOPICS, topicDao.getAll());

            req.getRequestDispatcher(Pages.COURSE_LIST).forward(req, resp);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}