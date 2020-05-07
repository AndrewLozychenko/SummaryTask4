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
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

@WebServlet("/user.login")
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(Parameters.LOGIN);
        String password = req.getParameter(Parameters.PASSWORD);

        MessageDigest digest = null;

        UserDao userDao = new UserService();

        String locale = (String) req.getSession().getAttribute("currentLocale");
        Properties prop = new Properties();

        if ("ua".equals(locale)) {
            locale = "src/resources_ua.properties";
        } else {
            locale = "src/resources.properties";
        }
        try {
            InputStream inputStream = new FileInputStream(locale);
            prop.load(inputStream);

            digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(password.getBytes("utf8"));
            password = String.format("%040x", new BigInteger(1, digest.digest()));

            User user = userDao.get(login);
            if (user == null) {
                req.setAttribute(Parameters.MESSAGE, prop.get("message.user_not_exists"));
                req.getRequestDispatcher(Pages.USER_LOGIN).forward(req, resp);
            } else if (user.getPassword().equals(password)) {
                if (!user.isBlocked()) {
                    req.getSession().setAttribute(Parameters.USER, user);
                    req.removeAttribute(Parameters.MESSAGE);
                    resp.sendRedirect(Pages.USER_SHOW);
                } else {
                    req.setAttribute(Parameters.MESSAGE, prop.get("message.user_was_blocked"));
                    req.getRequestDispatcher(Pages.USER_LOGIN).forward(req, resp);
                }
            } else {
                req.setAttribute(Parameters.LOGIN, login);
                req.setAttribute(Parameters.MESSAGE, prop.get("message.wrong_password"));
                req.getRequestDispatcher(Pages.USER_LOGIN).forward(req, resp);
            }
        } catch (DBException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
