package ua.nure.lozychenko.facultative.servlet.course;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.constants.*;
import ua.nure.lozychenko.facultative.db.dao.*;
import ua.nure.lozychenko.facultative.db.entity.Course;
import ua.nure.lozychenko.facultative.db.service.mysql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/course.create")
public class Create extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TopicDao topicDao = new TopicService();
        UserDao userDao = new UserService();
        TypeDao typeDao = new TypeService();

        try {
            req.getSession().setAttribute(Parameters.TOPICS, topicDao.getAll());
            req.getSession().setAttribute(Parameters.TEACHERS, userDao.getAll(typeDao.get(Types.TEACHER).getId()));

            req.getRequestDispatcher(Pages.COURSE_CREATE).forward(req, resp);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter(Parameters.NAME);
        long topic = Long.parseLong(req.getParameter(Parameters.TOPIC));
        long teacher = Long.parseLong(req.getParameter(Parameters.TEACHER));
        String begin = req.getParameter(Parameters.BEGIN);
        String end = req.getParameter(Parameters.END);
        int partyLimit = Integer.parseInt(req.getParameter(Parameters.PARTY_LIMIT));

        String message;

        TopicDao topicDao = new TopicService();
        UserDao userDao = new UserService();
        CourseDao courseDao = new CourseService();
        try {
            Course course = Course.createCourse(
                    name,
                    topicDao.get(topic),
                    userDao.get(teacher),
                    begin,
                    end,
                    partyLimit
            );
            if ((message = course.validate()) == null) {
                courseDao.save(course);
                resp.sendRedirect(Requests.COURSE_LIST);
            } else {
                req.setAttribute(Parameters.MESSAGE, message);
                req.setAttribute(Parameters.COURSE, course);

                req.getRequestDispatcher(Pages.COURSE_CREATE).forward(req, resp);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
