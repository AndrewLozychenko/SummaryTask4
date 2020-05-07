package ua.nure.lozychenko.facultative.servlet.user;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.constants.Pages;
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

import static ua.nure.lozychenko.facultative.constants.Types.*;

@WebServlet("/user.remove")
public class Remove extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(Parameters.USER);
        String targetLogin = req.getParameter(Parameters.TARGET);

        UserDao userDao = new UserService();
        try {
            if (ADMIN.equals(user.getType().getName())) {
                User target = userDao.get(targetLogin);
                if (target != null) {
                    String targetType = target.getType().getName();
                    userDao.delete(target);

                    if (TEACHER.equals(targetType)) {
                        resp.sendRedirect(Requests.USER_LIST_TEACHERS_FILTERED);
                    } else if (STUDENT.equals(targetType)) {
                        resp.sendRedirect(Requests.USER_LIST_STUDENTS_FILTERED);
                    } else {
                        resp.sendRedirect(Requests.USER_LIST_ADMINS_FILTERED);
                    }
                }
            } else {
                userDao.delete(user);
                req.removeAttribute(Parameters.USER);
                resp.sendRedirect(Pages.USER_LOGIN);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
