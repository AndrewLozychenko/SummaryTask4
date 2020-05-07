package ua.nure.lozychenko.facultative.servlet.course;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.constants.Parameters;
import ua.nure.lozychenko.facultative.constants.Requests;
import ua.nure.lozychenko.facultative.db.dao.CourseDao;
import ua.nure.lozychenko.facultative.db.service.mysql.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/course.remove")
public class Remove extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter(Parameters.ID));

        CourseDao courseDao = new CourseService();
        try {
            courseDao.delete(courseDao.get(id));

            resp.sendRedirect(Requests.COURSE_LIST);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
