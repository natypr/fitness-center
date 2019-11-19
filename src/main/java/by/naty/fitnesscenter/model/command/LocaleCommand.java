package by.naty.fitnesscenter.model.command;

import by.naty.fitnesscenter.model.logic.UserLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LocaleCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private UserLogic userLogic;

    public LocaleCommand(UserLogic userLogic){
        this.userLogic = userLogic;
    }

    @Override
    public CommandRF execute(HttpServletRequest request){
        String locale = request.getParameter("newLocale");
        String page = request.getParameter("pagePath");

        request.getSession().setAttribute("locale", locale);
        CommandRF commandRF = new CommandRF(CommandRF.DispatchType.REDIRECT, page);
        commandRF.setDispatchType(CommandRF.DispatchType.REDIRECT);

        commandRF.setPage(userLogic.returnSamePage(page));
        LOG.info("Changing language. Now " + locale);
        return commandRF;
    }
}
