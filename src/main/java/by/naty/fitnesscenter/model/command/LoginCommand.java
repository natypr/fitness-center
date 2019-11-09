package by.naty.fitnesscenter.model.command;

import by.naty.fitnesscenter.model.logic.LoginLogic;
import by.naty.fitnesscenter.model.resource.ConfigurationManager;
import by.naty.fitnesscenter.model.resource.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";

    public LoginCommand() {
        //...
    }

    @Override
    public CommandRF execute(HttpServletRequest request){
        String page = null;
        String login = request.getParameter(PARAM_LOGIN);
        String password = request.getParameter(PARAM_PASSWORD);

        if (LoginLogic.checkLogin(login, password)) {
            request.setAttribute("user", login);
            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }

        return new CommandRF(CommandRF.DispatchType.REDIRECT, page);
    }
}
