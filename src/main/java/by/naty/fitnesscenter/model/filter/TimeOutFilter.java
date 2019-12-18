package by.naty.fitnesscenter.model.filter;

import by.naty.fitnesscenter.model.manager.ConfigurationManager;

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

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        if (session.isNew()) {
            String page = ConfigurationManager.getProperty("path.page.main");
            request.getRequestDispatcher(page).forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
