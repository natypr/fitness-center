/*
package by.naty.fitnesscenter.model.filter;

import by.naty.fitnesscenter.model.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/jsp/client/*", "/jsp/trainer/*"},
        initParams = {@WebInitParam(name = "INDEX_PATH", value = "/index.jsp"),
                @WebInitParam(name = "ADMIN_MENU", value = "/jsp/admin/admin_cabinet.jsp")})
public class SecurityFilter implements Filter {

    private static final String USER = "user";
    private static final String ADMIN = "admin";
    private String indexPath;
    private String indexPathAdminCabinet;

    public void init(FilterConfig fConfig) {
        indexPath = fConfig.getInitParameter("INDEX_PATH");
        indexPathAdminCabinet = fConfig.getInitParameter("ADMIN_CABINET");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        User user = (User) ((HttpServletRequest) request).getSession().getAttribute(USER);
        if (user == null) {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(indexPath);
            dispatcher.forward(request, response);
        } else if (user.getRole().equals(ADMIN)) {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(indexPathAdminCabinet);
            dispatcher.forward(request, response);
        } else {
            String path = ((HttpServletRequest) request).getRequestURI();
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(path);
            dispatcher.forward(request, response);
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
*/
