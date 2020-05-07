package ua.nure.lozychenko.facultative.servlet.journal;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.constants.Pages;
import ua.nure.lozychenko.facultative.constants.Parameters;
import ua.nure.lozychenko.facultative.constants.Types;
import ua.nure.lozychenko.facultative.db.dao.JournalDao;
import ua.nure.lozychenko.facultative.db.entity.User;
import ua.nure.lozychenko.facultative.db.service.mysql.JournalService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/journal.list")
public class GetList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(Parameters.USER);
        JournalDao journalDao = new JournalService();
        try {
            if (Types.STUDENT.equals(user.getType().getName())) {
                req.setAttribute(Parameters.JOURNAL, journalDao.getByStudent(user.getId()));
                req.getRequestDispatcher(Pages.JOURNAL_LIST).forward(req, resp);
            } else if (Types.TEACHER.equals(user.getType().getName())) {
                long courseId = Long.parseLong(req.getParameter(Parameters.COURSE));

                req.setAttribute(Parameters.JOURNAL, journalDao.getByCourse(courseId));
                req.getRequestDispatcher(Pages.JOURNAL_LIST).forward(req, resp);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
