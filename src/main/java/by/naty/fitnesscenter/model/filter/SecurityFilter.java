package by.naty.fitnesscenter.model.filter;

import by.naty.fitnesscenter.model.entity.User;
import by.naty.fitnesscenter.model.manager.ConfigurationManager;
import by.naty.fitnesscenter.model.manager.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.ACCESS_CLOSED;
import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.USER;

/**
 * The type Security filter.
 */
@WebFilter(urlPatterns = {"/jsp/admin/*", "/jsp/client/*", "/jsp/trainer/*"})
public class SecurityFilter implements Filter {
    private static final Logger LOG = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        User user = (User) httpRequest.getSession().getAttribute(USER);
        String userRole = user.getRole();
        if (userRole != null) {
            String currentPage = httpRequest.getRequestURL().toString();

            if (currentPage.contains("/" + userRole + "/")) {
                chain.doFilter(request, response);
            } else {
                LOG.error("Access closed. ");
                request.setAttribute(ACCESS_CLOSED, MessageManager.getProperty("message.filter.accessClosed"));
                String page = ConfigurationManager.getProperty("path.page.error");
                httpRequest.getRequestDispatcher(page).forward(request, response);
            }
        } else {
            LOG.error("User role is null. ");
            String page = ConfigurationManager.getProperty("path.page.error");
            httpRequest.getRequestDispatcher(page).forward(request, response);
        }
    }
}