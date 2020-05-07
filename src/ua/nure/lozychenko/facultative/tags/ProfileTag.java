package ua.nure.lozychenko.facultative.tags;

import ua.nure.lozychenko.facultative.db.entity.User;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ProfileTag extends TagSupport {
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public int doStartTag() {
        JspWriter out = pageContext.getOut();
        String locale = (String) pageContext.getSession().getAttribute("currentLocale");
        Properties prop = new Properties();

        if ("ua".equals(locale)) {
            locale = "src/resources_ua.properties";
        } else {
            locale = "src/resources.properties";
        }
        try {
            InputStream inputStream = new FileInputStream(locale);
            prop.load(inputStream);

            out.print("<table border = \"1\">");
            out.print("<tr>");
            out.print("<td>" + prop.get("labels.login") + "</td>");
            out.print("<td>");
            out.print(user.getLogin());
            out.print("</td>");
            out.print("</tr>");
            out.print("<tr>");
            out.print("<td>" + prop.get("labels.name") + "</td>");
            out.print("<td>");
            out.print(user.getName());
            out.print("</td>");
            out.print("</tr>");
            out.print("<tr>");
            out.print("<td>" + prop.get("labels.surname") + "</td>");
            out.print("<td>");
            out.print(user.getSurname());
            out.print("</td>");
            out.print("</tr>");
            out.print("<tr>");
            out.print("<td>" + prop.get("labels.type") + "</td>");
            out.print("<td>");
            out.print(prop.get("types." + user.getType().getName()));
            out.print("</td>");
            out.print("</tr>");
            out.print("<tr>");
            out.print("<td>" + prop.get("labels.contacts") + "</td>");
            out.print("<td>");
            out.print(user.getContacts());
            out.print("</td>");
            out.print("</tr>");
            out.print("</table>");
        } catch (Exception e) {
            System.out.println(e);
        }

        return SKIP_BODY;
    }
}
