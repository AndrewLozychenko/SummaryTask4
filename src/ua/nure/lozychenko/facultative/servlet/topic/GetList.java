package ua.nure.lozychenko.facultative.servlet.topic;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.constants.Pages;
import ua.nure.lozychenko.facultative.constants.Parameters;
import ua.nure.lozychenko.facultative.db.dao.TopicDao;
import ua.nure.lozychenko.facultative.db.service.mysql.TopicService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/topic.list")
public class GetList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TopicDao topicDao = new TopicService();

        try {
            req.setAttribute(Parameters.TOPICS, topicDao.getAll());
            req.getRequestDispatcher(Pages.TOPIC_LIST).forward(req, resp);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
