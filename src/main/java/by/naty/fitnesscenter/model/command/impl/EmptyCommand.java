package by.naty.fitnesscenter.model.command.impl;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.CommandRouter;
import by.naty.fitnesscenter.model.manager.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandRouter execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.index");
        LOG.debug("Empty command, transition to the index.");
        return new CommandRouter(CommandRouter.DispatchType.FORWARD, page);
    }
}
