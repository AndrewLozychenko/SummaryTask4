package ua.nure.lozychenko.facultative.servlet.user;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.constants.Messages;
import ua.nure.lozychenko.facultative.constants.Pages;
import ua.nure.lozychenko.facultative.constants.Parameters;
import ua.nure.lozychenko.facultative.db.dao.UserDao;
import ua.nure.lozychenko.facultative.db.entity.User;
import ua.nure.lozychenko.facultative.db.service.mysql.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user.login")
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(Parameters.LOGIN);
        String password = req.getParameter(Parameters.PASSWORD);

        UserDao userDao = new UserService();
        try {
            User user = userDao.get(login);
            if (user == null) {
                req.setAttribute(Parameters.MESSAGE, Messages.ERROR_USER_NOT_EXISTS);
                req.getRequestDispatcher(Pages.USER_LOGIN).forward(req, resp);
            } else if (user.getPassword().equals(password)) {
                if (!user.isBlocked()) {
                    req.getSession().setAttribute(Parameters.USER, user);
                    req.removeAttribute(Parameters.MESSAGE);
                    resp.sendRedirect(Pages.USER_SHOW);
                } else {
                    req.setAttribute(Parameters.MESSAGE, Messages.ERROR_USER_WAS_BLOCKED);
                    req.getRequestDispatcher(Pages.USER_LOGIN).forward(req, resp);
                }
            } else {
                req.setAttribute(Parameters.LOGIN, login);
                req.setAttribute(Parameters.MESSAGE, Messages.ERROR_WRONG_PASSWORD);
                req.getRequestDispatcher(Pages.USER_LOGIN).forward(req, resp);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
