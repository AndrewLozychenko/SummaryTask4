package ua.nure.lozychenko.facultative.servlet.journal;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.constants.Pages;
import ua.nure.lozychenko.facultative.constants.Parameters;
import ua.nure.lozychenko.facultative.db.dao.JournalDao;
import ua.nure.lozychenko.facultative.db.entity.Journal;
import ua.nure.lozychenko.facultative.db.service.mysql.JournalService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/journal.save")
public class Save extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long journalId = Long.parseLong(req.getParameter(Parameters.JOURNAL));
        int mark = Integer.parseInt(req.getParameter(Parameters.MARK));

        JournalDao journalDao = new JournalService();
        try {
            Journal journal = journalDao.get(journalId);
            journal.setMark(mark);
            journalDao.update(journal, journal);

            req.setAttribute(Parameters.JOURNAL, journalDao.getByCourse(journal.getStudent().getCourse().getId()));
            req.getRequestDispatcher(Pages.JOURNAL_LIST).forward(req, resp);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
