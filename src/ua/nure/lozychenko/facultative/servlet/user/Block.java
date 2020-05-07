package ua.nure.lozychenko.facultative.servlet.user;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.constants.Parameters;
import ua.nure.lozychenko.facultative.constants.Requests;
import ua.nure.lozychenko.facultative.db.dao.UserDao;
import ua.nure.lozychenko.facultative.db.entity.User;
import ua.nure.lozychenko.facultative.db.service.mysql.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user.block")
public class Block extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(Parameters.LOGIN);

        UserDao userDao = new UserService();

        try {
            User user = userDao.get(login);

            user.setBlocked(!user.isBlocked());
            userDao.update(user, user);

            resp.sendRedirect(Requests.USER_LIST_STUDENTS_FILTERED);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
