package by.naty.fitnesscenter.model.filter;

import by.naty.fitnesscenter.model.manager.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Time out filter.
 */
@WebFilter(urlPatterns = {"/*"})
public class TimeOutFilter implements Filter {
    private static final Logger LOG = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(false);
        if (session != null && !session.isNew()) {
            chain.doFilter(servletRequest, servletResponse);
        } else {
            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.index"))
                    .forward(servletRequest, servletResponse);
        }
    }
}
