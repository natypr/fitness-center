package by.naty.fitnesscenter.model.command;

import by.naty.fitnesscenter.model.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {
    @Override
    public CommandRF execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.index");
        return new CommandRF(CommandRF.DispatchType.FORWARD, page);
    }
}
