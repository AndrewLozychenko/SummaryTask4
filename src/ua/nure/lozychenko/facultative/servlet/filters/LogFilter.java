package ua.nure.lozychenko.facultative.servlet.filters;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import ua.nure.lozychenko.facultative.servlet.course.GetList;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

public class LogFilter implements Filter {
    private Logger logger;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger = Logger.getLogger(GetList.class);
        BasicConfigurator.configure();

        logger.info("Logger init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        logger.info(new Date() + req.getServletPath() + " : " + req.getRequestURL());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("Logger destroyed");
    }
}
