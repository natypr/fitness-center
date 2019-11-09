package by.naty.fitnesscenter.controller;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.CommandRF;
import by.naty.fitnesscenter.model.command.EmptyCommand;
import by.naty.fitnesscenter.model.command.factory.ActionFactory;
import by.naty.fitnesscenter.model.exception.CommandFCException;
import by.naty.fitnesscenter.model.exception.PoolFCException;
import by.naty.fitnesscenter.model.pool.ConnectionPool;
import by.naty.fitnesscenter.model.pool.PoolConfig;
import by.naty.fitnesscenter.model.resource.ConfigurationManager;
import by.naty.fitnesscenter.model.resource.LocaleManager;
import by.naty.fitnesscenter.model.resource.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.annotation.WebServlet;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Optional<Command> commandOptional = ActionFactory.defineCommand(request.getParameter("command"));
            Command command = commandOptional.orElse(new EmptyCommand());

            String locale = (String) request.getSession().getAttribute("changeLanguage");
            LocaleManager localeManager = LocaleManager.defineLocale(locale);

            CommandRF commandRF = command.execute(request);

            if (commandRF.getDispatchType() == CommandRF.DispatchType.FORWARD) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(commandRF.getPage());
                dispatcher.forward(request, response);
            } else {
                String defaultPage = ConfigurationManager.getProperty("path.page.index");
                if (commandRF.getPage().isEmpty()) {
                    LOG.info("Null page.");
                    request.getSession().setAttribute("nullPage", localeManager.getProperty("messages.nullpage"));
                    response.sendRedirect(request.getContextPath() + defaultPage);
                }

                String page = commandRF.getPage();
                request.getSession().setAttribute("pagePath", page);
                response.sendRedirect(request.getContextPath() + page);
            }
        } catch (CommandFCException e) {
            request.getSession().setAttribute("nullPage", MessageManager.getProperty("messages.nullpage"));
            LOG.error("Command not defined. ", e);
            String page = ConfigurationManager.getProperty("path.page.error");
            request.getRequestDispatcher(page).forward(request, response);
        }

    }

    @Override
    public void destroy() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        PoolConfig config = new PoolConfig();
        int poolSize = config.getPoolSize();
        for (int i = 0; i < poolSize; i++) {
            try {
                connectionPool.closeConnection(connectionPool.getConnection());
            } catch (PoolFCException e) {
                LOG.error("Some connections aren't closed: ", e);
            }
        }
    }
}
