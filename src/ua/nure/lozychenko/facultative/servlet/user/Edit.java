package ua.nure.lozychenko.facultative.servlet.user;

import ua.nure.lozychenko.facultative.DBException;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebServlet("/user.edit")
public class Edit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter(Parameters.LOGIN);
        String name = req.getParameter(Parameters.NAME);
        String surname = req.getParameter(Parameters.SURNAME);
        String contacts = req.getParameter(Parameters.CONTACTS);
        String message;

        UserDao userDao = new UserService();
        try {
            User user = (User) req.getSession().getAttribute(Parameters.USER);
            user.setLogin(login);
            user.setName(name);
            user.setSurname(surname);
            user.setContacts(contacts);

            if ((message = user.validate()) == null) {
                userDao.update(user, user);
                req.getSession().setAttribute(Parameters.USER, user);
                resp.sendRedirect(Pages.USER_SHOW);
            } else {
                String locale = (String) req.getSession().getAttribute("currentLocale");
                Properties prop = new Properties();

                if ("ua".equals(locale)) {
                    locale = "src/resources_ua.properties";
                } else {
                    locale = "src/resources.properties";
                }
                InputStream inputStream = new FileInputStream(locale);
                prop.load(inputStream);

                if (message.split(",")[1].equals("empty")) {
                    message = message.split(",")[0] + prop.get("message.cannot_be_empty");
                } else if (message.split(",")[1].equals("chars")) {
                    message = message.split(",")[0] + prop.get("message.wrong_chars");
                } else if (message.split(",")[1].equals("short")) {
                    message = message.split(",")[0] + prop.get("message.too_short");
                }

                req.setAttribute(Parameters.MESSAGE, message);
                req.getSession().setAttribute(Parameters.USER, user);
                req.getRequestDispatcher(Pages.USER_EDIT).forward(req, resp);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
