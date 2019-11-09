package by.naty.fitnesscenter.model.command;

import by.naty.fitnesscenter.model.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public CommandRF execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.index");
        request.getSession().invalidate(); //session destruction
        return new CommandRF(CommandRF.DispatchType.FORWARD, page);
    }
}
