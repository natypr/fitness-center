package by.naty.fitnesscenter.model.command;

import by.naty.fitnesscenter.model.entity.User;
import by.naty.fitnesscenter.model.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private static final String PARAM_CHANGE_LANGUAGE = "changeLanguage";

    public LogoutCommand() {
    }

    @Override
    public CommandRF execute(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        Object locale = request.getSession().getAttribute(PARAM_CHANGE_LANGUAGE);
        String page = ConfigurationManager.getProperty("path.page.index");

        request.getSession().invalidate();
        request.getSession().setAttribute(PARAM_CHANGE_LANGUAGE, locale);
        if (user == null) {
            LOG.info("Empty log out. Nobody log out.");
        } else {
            LOG.info(user.getEmail() + " log out!");
        }
        return new CommandRF(CommandRF.DispatchType.FORWARD, page);
    }
}
