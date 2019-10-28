package by.naty.fitnesscenter.model.command.factory;

import by.naty.fitnesscenter.model.command.ActionCommand;
import by.naty.fitnesscenter.model.command.EmptyCommand;
import by.naty.fitnesscenter.model.command.client.CommandEnum;
import by.naty.fitnesscenter.model.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter("command");

        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}
