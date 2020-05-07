package ua.nure.lozychenko.facultative.servlet.course;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.constants.Pages;
import ua.nure.lozychenko.facultative.constants.Parameters;
import ua.nure.lozychenko.facultative.constants.Requests;
import ua.nure.lozychenko.facultative.constants.Types;
import ua.nure.lozychenko.facultative.db.dao.CourseDao;
import ua.nure.lozychenko.facultative.db.dao.TopicDao;
import ua.nure.lozychenko.facultative.db.dao.TypeDao;
import ua.nure.lozychenko.facultative.db.dao.UserDao;
import ua.nure.lozychenko.facultative.db.entity.Course;
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

@WebServlet("/course.edit")
public class Edit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter(Parameters.ID));

        CourseDao courseDao = new CourseService();
        TopicDao topicDao = new TopicService();
        UserDao userDao = new UserService();
        TypeDao typeDao = new TypeService();
        try {
            req.getSession().setAttribute(Parameters.COURSE, courseDao.get(id));
            req.getSession().setAttribute(Parameters.TOPICS, topicDao.getAll());
            req.getSession().setAttribute(Parameters.TEACHERS, userDao.getAll(typeDao.get(Types.TEACHER).getId()));

            req.getRequestDispatcher(Pages.COURSE_EDIT).forward(req, resp);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        long id = Long.parseLong(req.getParameter(Parameters.ID));
        String name = req.getParameter(Parameters.NAME);
        long topic = Long.parseLong(req.getParameter(Parameters.TOPIC));
        long teacher = Long.parseLong(req.getParameter(Parameters.TEACHER));
        String begin = req.getParameter(Parameters.BEGIN);
        String end = req.getParameter(Parameters.END);
        int partyLimit = Integer.parseInt(req.getParameter(Parameters.PARTY_LIMIT));

        String message;

        CourseDao courseDao = new CourseService();
        TopicDao topicDao = new TopicService();
        UserDao userDao = new UserService();
        try {
            Course course = new Course(id, name, userDao.get(teacher), topicDao.get(topic), begin, end, partyLimit);

            if ((message = course.validate()) == null) {
                courseDao.update(course, course);
                resp.sendRedirect(Requests.COURSE_LIST);
            } else {
                req.setAttribute(Parameters.MESSAGE, message);
                req.setAttribute(Parameters.COURSE, course);

                req.getRequestDispatcher(Pages.COURSE_EDIT).forward(req, resp);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
