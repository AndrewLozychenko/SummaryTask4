package ua.nure.lozychenko.facultative.servlet.user;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.constants.Pages;
import ua.nure.lozychenko.facultative.constants.Parameters;
import ua.nure.lozychenko.facultative.constants.Types;
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
import java.util.List;

@WebServlet("/user.list")
public class GetList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter(Parameters.TYPE);

        UserDao userDao = new UserService();
        TypeDao typeDao = new TypeService();
        try {
            List<User> users = userDao.getAll(typeDao.get(type).getId());

            if (Types.ADMIN.equals(type)) {
                req.setAttribute(Parameters.ADMINS, users);
                req.getRequestDispatcher(Pages.USER_LIST_ADMINS).forward(req, resp);
            } else if (Types.TEACHER.equals(type)) {
                req.setAttribute(Parameters.TEACHERS, users);
                req.getRequestDispatcher(Pages.USER_LIST_TEACHERS).forward(req, resp);
            } else {
                req.setAttribute(Parameters.STUDENTS, users);
                req.getRequestDispatcher(Pages.USER_LIST_STUDENTS).forward(req, resp);
            }

        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
