package by.naty.fitnesscenter.controller;

import by.naty.fitnesscenter.model.command.ActionFactory;
import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.CommandRouter;
import by.naty.fitnesscenter.model.command.impl.EmptyCommand;
import by.naty.fitnesscenter.model.exception.CommandException;
import by.naty.fitnesscenter.model.exception.PoolException;
import by.naty.fitnesscenter.model.pool.ConnectionPool;
import by.naty.fitnesscenter.model.pool.PoolConfig;
import by.naty.fitnesscenter.model.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger();

    private static final String COMMAND = "command";
    private static final String PAGE_PASS = "page_path";

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
            LOG.debug("Controller started.");
            Optional<Command> commandOptional = ActionFactory.defineCommand(request.getParameter(COMMAND));
            Command command = commandOptional.orElse(new EmptyCommand());

            CommandRouter commandRouter = command.execute(request);

            if (commandRouter.getDispatchType() == CommandRouter.DispatchType.FORWARD) {
                LOG.debug("Dispatch type is Forward.");
                RequestDispatcher dispatcher = request.getRequestDispatcher(commandRouter.getPage());
                dispatcher.forward(request, response);
            } else {
                LOG.debug("Dispatch type is Redirect.");
                String defaultPage = ConfigurationManager.getProperty("path.page.index");
                if (commandRouter.getPage().isEmpty()) {
                    LOG.info("Null page, command not transferred.");
                    response.sendRedirect(request.getContextPath() + defaultPage);
                }
                String page = commandRouter.getPage();
                request.getSession().setAttribute(PAGE_PASS, page);
                response.sendRedirect(request.getContextPath() + page);
            }
        } catch (CommandException e) {
            LOG.error("Command not defined: ", e);
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
            } catch (PoolException e) {
                LOG.error("Some connections aren't closed: ", e);
            }
        }
        LOG.debug("Destroy connection.");
    }
}
