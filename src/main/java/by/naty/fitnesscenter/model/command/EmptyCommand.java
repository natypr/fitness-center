package by.naty.fitnesscenter.model.command;

import by.naty.fitnesscenter.model.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandRF execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.index");
        LOG.debug("Empty command, transition to the index.");
        return new CommandRF(CommandRF.DispatchType.FORWARD, page);
    }
}
