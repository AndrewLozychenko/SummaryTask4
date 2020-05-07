package ua.nure.lozychenko.facultative.servlet.filters;

import ua.nure.lozychenko.facultative.constants.Pages;
import ua.nure.lozychenko.facultative.constants.Parameters;
import ua.nure.lozychenko.facultative.constants.Requests;
import ua.nure.lozychenko.facultative.constants.Types;
import ua.nure.lozychenko.facultative.db.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

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
                    || req.getRequestURI().contains(Requests.USER_LIST)
                    || req.getRequestURI().contains(Requests.USER_BLOCK)
                    || (req.getRequestURI().contains(Requests.USER_CREATE) && (Types.ADMIN.equals(user.getType().getName())))
                    || (req.getRequestURI().contains(Requests.USER_REMOVE) && req.getParameter(Parameters.TARGET) != null)) {
                if (Types.ADMIN.equals(user.getType().getName())) {
                    if (req.getRequestURI().contains(Requests.COURSE_EDIT)
                            || req.getRequestURI().contains(Requests.COURSE_REMOVE)) {
                        if (req.getParameter(Parameters.ID) == null) {
                            req.setAttribute(Parameters.MESSAGE,
                                    "Missing required parameter \"id\". You must select course from list");
                            req.setAttribute(Parameters.ACTIONS, new Action[]{
                                    new Action("Courses", Requests.COURSE_LIST)
                            });
                            req.getRequestDispatcher(Pages.MESSAGE).forward(req, resp);
                        } else {
                            filterChain.doFilter(req, resp);
                        }
                    } else if (req.getRequestURI().contains(Requests.TOPIC_EDIT)
                            || req.getRequestURI().contains(Requests.TOPIC_REMOVE)) {
                        if (req.getParameter(Parameters.ID) == null) {
                            req.setAttribute(Parameters.MESSAGE,
                                    "Missing required parameter \"id\". You must select course from list");
                            req.setAttribute(Parameters.ACTIONS, new Action[]{
                                    new Action("Topics", Requests.TOPIC_LIST)
                            });
                            req.getRequestDispatcher(Pages.MESSAGE).forward(req, resp);
                        } else {
                            filterChain.doFilter(req, resp);
                        }
                    } else if (req.getRequestURI().contains(Requests.USER_BLOCK)) {
                        if (req.getParameter(Parameters.LOGIN) == null) {
                            req.setAttribute(Parameters.MESSAGE,
                                    "Missing required parameter \"login\". You must select student from list");
                            req.setAttribute(Parameters.ACTIONS, new Action[]{
                                    new Action("Students", Requests.USER_LIST_STUDENTS_FILTERED)
                            });
                            req.getRequestDispatcher(Pages.MESSAGE).forward(req, resp);
                        } else {
                            filterChain.doFilter(req, resp);
                        }
                    } else {
                        filterChain.doFilter(req, resp);
                    }
                } else {
                    req.setAttribute(Parameters.MESSAGE, "Access denied. For admins only");
                    req.getRequestDispatcher(Pages.MESSAGE).forward(req, resp);
                }
            } else if (req.getRequestURI().contains(Requests.JOURNAL_SAVE)) {
                if (Types.TEACHER.equals(user.getType().getName())) {
                    if (req.getParameter(Parameters.JOURNAL) == null
                            || req.getParameter(Parameters.MARK) == null) {
                        req.setAttribute(Parameters.MESSAGE,
                                "Missing required parameters \"journal\" and/or \"mark\". You must set marks in journal");
                        req.setAttribute(Parameters.ACTIONS, new Action[]{
                                new Action("Courses", Requests.COURSE_LIST)
                        });
                        req.getRequestDispatcher(Pages.MESSAGE).forward(req, resp);
                    } else {
                        filterChain.doFilter(req, resp);
                    }
                } else {
                    req.setAttribute(Parameters.MESSAGE, "Access denied. For teachers only");
                    req.getRequestDispatcher(Pages.MESSAGE).forward(req, resp);
                }
            } else if (req.getRequestURI().contains(Requests.COURSE_JOIN)
                    || req.getRequestURI().contains(Requests.COURSE_LEAVE)) {
                if (Types.STUDENT.equals(user.getType().getName())) {
                    if (req.getParameter(Parameters.COURSE) == null) {
                        req.setAttribute(Parameters.MESSAGE,
                                "Missing required parameter \"course\". You must select course from list");
                        req.setAttribute(Parameters.ACTIONS, new Action[]{
                                new Action("Courses", Requests.COURSE_LIST)
                        });
                        req.getRequestDispatcher(Pages.MESSAGE).forward(req, resp);
                    } else {
                        filterChain.doFilter(req, resp);
                    }
                } else {
                    req.setAttribute(Parameters.MESSAGE, "Access denied. For students only");
                    req.getRequestDispatcher(Pages.MESSAGE).forward(req, resp);
                }
            } else {
                filterChain.doFilter(req, resp);
            }
        } else {
            if (!req.getRequestURI().contains(Requests.USER_LOGIN) && !req.getRequestURI().contains(Requests.USER_CREATE)) {
                req.setAttribute(Parameters.MESSAGE, "Access denied. You must login or register first");
                req.setAttribute(Parameters.ACTIONS, new Action[]{
                        new Action("Login", Pages.USER_LOGIN),
                        new Action("Registartion", Pages.USER_CREATE)
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
