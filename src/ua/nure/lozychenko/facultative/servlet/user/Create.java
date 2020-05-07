package ua.nure.lozychenko.facultative.servlet.user;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.constants.*;
import ua.nure.lozychenko.facultative.db.dao.TypeDao;
import ua.nure.lozychenko.facultative.db.dao.UserDao;
import ua.nure.lozychenko.facultative.db.entity.User;
import ua.nure.lozychenko.facultative.db.service.mysql.TypeService;
import ua.nure.lozychenko.facultative.db.service.mysql.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user.create")
public class Create extends HttpServlet {
    private static final String DUPLICATE = "Duplicate";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter(Parameters.LOGIN);
        String password = req.getParameter(Parameters.PASSWORD);
        String name = req.getParameter(Parameters.NAME);
        String surname = req.getParameter(Parameters.SURNAME);
        String type = req.getParameter(Parameters.TYPE);
        String contacts = req.getParameter(Parameters.CONTACTS);

        String message;

        UserDao userDao = new UserService();
        TypeDao typeDao = new TypeService();
        try {
            User user = User.createUser(
                    login,
                    password,
                    typeDao.get(type),
                    name,
                    surname,
                    contacts
            );
            if ((message = user.validate()) == null) {
                userDao.save(user);

                if (Types.STUDENT.equals(type)) {
                    req.getSession().setAttribute(Parameters.USER, user);
                    req.getRequestDispatcher(Pages.USER_SHOW).forward(req, resp);
                } else if (Types.TEACHER.equals(type)) {
                    resp.sendRedirect(Requests.USER_LIST_TEACHERS_FILTERED);
                } else {
                    resp.sendRedirect(Requests.USER_LIST_ADMINS_FILTERED);
                }
            } else {
                req.setAttribute(Parameters.MESSAGE, message);
                req.setAttribute(Parameters.USER, user);
                req.getRequestDispatcher(Pages.USER_CREATE).forward(req, resp);
            }
        } catch (DBException e) {
            if (e.getMessage().contains(DUPLICATE)) {
                req.setAttribute(Parameters.MESSAGE, name + Messages.ERROR_USER_ALREADY_EXISTS);
                req.getRequestDispatcher(Pages.USER_CREATE).forward(req, resp);
            } else {
                e.printStackTrace();
            }
        }
    }
}
