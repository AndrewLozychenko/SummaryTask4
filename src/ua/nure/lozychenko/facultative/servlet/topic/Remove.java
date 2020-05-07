package ua.nure.lozychenko.facultative.servlet.topic;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.constants.Parameters;
import ua.nure.lozychenko.facultative.constants.Requests;
import ua.nure.lozychenko.facultative.db.dao.TopicDao;
import ua.nure.lozychenko.facultative.db.entity.Topic;
import ua.nure.lozychenko.facultative.db.service.mysql.TopicService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/topic.remove")
public class Remove extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter(Parameters.ID));

        TopicDao topicDao = new TopicService();
        try {
            Topic topic = topicDao.get(id);
            topicDao.delete(topic);

            resp.sendRedirect(Requests.TOPIC_LIST);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
