package by.naty.fitnesscenter.model.command.impl;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.CommandRouter;
import by.naty.fitnesscenter.model.logic.UserLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.*;

public class LocaleCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private UserLogic userLogic;

    public LocaleCommand(UserLogic userLogic) {
        this.userLogic = userLogic;
    }

    @Override
    public CommandRouter execute(HttpServletRequest request) {
        String locale = request.getParameter(NEW_LOCALE);
        String page = request.getParameter(PAGE_PATH);

        request.getSession().setAttribute(LOCALE, locale);
        CommandRouter commandRouter = new CommandRouter(CommandRouter.DispatchType.REDIRECT, page);
        commandRouter.setDispatchType(CommandRouter.DispatchType.REDIRECT);

        commandRouter.setPage(userLogic.returnSamePage(page));
        LOG.info("Changing language. Now " + locale);
        return commandRouter;
    }
}
