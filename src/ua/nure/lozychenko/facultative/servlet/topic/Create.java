package ua.nure.lozychenko.facultative.servlet.topic;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.constants.Pages;
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

@WebServlet("/topic.create")
public class Create extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter(Parameters.NAME);

        String message;

        TopicDao topicDao = new TopicService();
        try {
            Topic topic = Topic.createTopic(name);
            if ((message = topic.validate()) == null) {
                topicDao.save(topic);
                resp.sendRedirect(Requests.TOPIC_LIST);
            } else {
                req.setAttribute(Parameters.TOPIC, topic);
                req.setAttribute(Parameters.MESSAGE, message);
                req.getRequestDispatcher(Pages.TOPIC_CREATE).forward(req, resp);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
