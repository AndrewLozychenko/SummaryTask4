package ua.nure.lozychenko.facultative.servlet.filters;

import ua.nure.lozychenko.facultative.constants.Pages;
import ua.nure.lozychenko.facultative.constants.Parameters;
import ua.nure.lozychenko.facultative.constants.Requests;
import ua.nure.lozychenko.facultative.constants.Types;
import ua.nure.lozychenko.facultative.db.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String locale = (String) req.getSession().getAttribute("currentLocale");
        Properties prop = new Properties();

        if ("ua".equals(locale)) {
            locale = "src/resources_ua.properties";
        } else {
            locale = "src/resources.properties";
        }

        InputStream inputStream = new FileInputStream(locale);
        prop.load(inputStream);

        User user = (User) req.getSession().getAttribute(Parameters.USER);
        if (user != null) {
            if (req.getRequestURI().contains(Requests.USER_LOGIN)) {
                resp.sendRedirect(Pages.USER_SHOW);
            } else if (req.getRequestURI().contains(Requests.COURSE_CREATE)
                    || req.getRequestURI().contains(Requests.COURSE_EDIT)
                    || req.getRequestURI().contains(Requests.COURSE_REMOVE)
                    || req.getRequestURI().contains(Requests.TOPIC_CREATE)
                    || req.getRequestURI().contains(Requests.TOPIC_EDIT)
                    || req.getRequestURI().contains(Requests.TOPIC_REMOVE)
                    || req.getRequestURI().contains(Requests.TOPIC_LIST)
                    || req.getRequestURI().contains(Requests.USER_LIST)
                    || req.getRequestURI().contains(Requests.USER_BLOCK)
                    || (req.getRequestURI().contains(Requests.USER_CREATE) && (Types.ADMIN.equals(user.getType().getName())))
                    || (req.getRequestURI().contains(Requests.USER_REMOVE) && req.getParameter(Parameters.TARGET) != null)) {
                if (Types.ADMIN.equals(user.getType().getName())) {
                    if (req.getRequestURI().contains(Requests.COURSE_EDIT)
                            || req.getRequestURI().contains(Requests.COURSE_REMOVE)) {
                        if (req.getParameter(Parameters.ID) == null) {
                            req.setAttribute(Parameters.MESSAGE,
                                    prop.get("message.course.missing_params"));
                            req.setAttribute(Parameters.ACTIONS, new Action[]{
                                    new Action(prop.get("labels.course_list").toString(), Requests.COURSE_LIST)
                            });
                            req.getRequestDispatcher(Pages.MESSAGE).forward(req, resp);
                        } else {
                            filterChain.doFilter(req, resp);
                        }
                    } else if (req.getRequestURI().contains(Requests.TOPIC_EDIT)
                            || req.getRequestURI().contains(Requests.TOPIC_REMOVE)) {
                        if (req.getParameter(Parameters.ID) == null) {
                            req.setAttribute(Parameters.MESSAGE,
                                    prop.get("message.topic.missing_params"));
                            req.setAttribute(Parameters.ACTIONS, new Action[]{
                                    new Action(prop.get("labels.topic_list").toString(), Requests.TOPIC_LIST)
                            });
                            req.getRequestDispatcher(Pages.MESSAGE).forward(req, resp);
                        } else {
                            filterChain.doFilter(req, resp);
                        }
                    } else if (req.getRequestURI().contains(Requests.USER_BLOCK)) {
                        if (req.getParameter(Parameters.LOGIN) == null) {
                            req.setAttribute(Parameters.MESSAGE,
                                    prop.get("message.user.missing_params"));
                            req.setAttribute(Parameters.ACTIONS, new Action[]{
                                    new Action(prop.get("labels.student_list").toString(), Requests.USER_LIST_STUDENTS_FILTERED)
                            });
                            req.getRequestDispatcher(Pages.MESSAGE).forward(req, resp);
                        } else {
                            filterChain.doFilter(req, resp);
                        }
                    } else {
                        filterChain.doFilter(req, resp);
                    }
                } else {
                    req.setAttribute(Parameters.MESSAGE, prop.get("message.access_denied.admin"));
                    req.getRequestDispatcher(Pages.MESSAGE).forward(req, resp);
                }
            } else if (req.getRequestURI().contains(Requests.JOURNAL_SAVE)) {
                if (Types.TEACHER.equals(user.getType().getName())) {
                    if (req.getParameter(Parameters.JOURNAL) == null
                            || req.getParameter(Parameters.MARK) == null) {
                        req.setAttribute(Parameters.MESSAGE,
                                prop.get("message.journal.missing_params"));
                        req.setAttribute(Parameters.ACTIONS, new Action[]{
                                new Action(prop.get("labels.course_list").toString(), Requests.COURSE_LIST)
                        });
                        req.getRequestDispatcher(Pages.MESSAGE).forward(req, resp);
                    } else {
                        filterChain.doFilter(req, resp);
                    }
                } else {
                    req.setAttribute(Parameters.MESSAGE, prop.get("message.access_denied.teacher"));
                    req.getRequestDispatcher(Pages.MESSAGE).forward(req, resp);
                }
            } else if (req.getRequestURI().contains(Requests.COURSE_JOIN)
                    || req.getRequestURI().contains(Requests.COURSE_LEAVE)) {
                if (Types.STUDENT.equals(user.getType().getName())) {
                    if (req.getParameter(Parameters.COURSE) == null) {
                        req.setAttribute(Parameters.MESSAGE,
                                prop.get("message.course.join_leave.missing_params"));
                        req.setAttribute(Parameters.ACTIONS, new Action[]{
                                new Action(prop.get("labels.course_list").toString(), Requests.COURSE_LIST)
                        });
                        req.getRequestDispatcher(Pages.MESSAGE).forward(req, resp);
                    } else {
                        filterChain.doFilter(req, resp);
                    }
                } else {
                    req.setAttribute(Parameters.MESSAGE, prop.get("message.access_denied.stundet"));
                    req.getRequestDispatcher(Pages.MESSAGE).forward(req, resp);
                }
            } else {
                filterChain.doFilter(req, resp);
            }
        } else {
            if (!req.getRequestURI().contains(Requests.USER_LOGIN) && !req.getRequestURI().contains(Requests.USER_CREATE)) {
                req.setAttribute(Parameters.MESSAGE, prop.get("message.not_logined"));
                req.setAttribute(Parameters.ACTIONS, new Action[]{
                        new Action(prop.get("buttons.login").toString(), Pages.USER_LOGIN),
                        new Action(prop.get("buttons.registration").toString(), Pages.USER_CREATE)
                });
                req.getRequestDispatcher(Pages.MESSAGE).forward(req, resp);
            } else {
                filterChain.doFilter(req, resp);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
