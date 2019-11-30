package by.naty.fitnesscenter.model.command.impl;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.CommandRouter;
import by.naty.fitnesscenter.model.entity.Client;
import by.naty.fitnesscenter.model.exception.CommandException;
import by.naty.fitnesscenter.model.exception.LogicException;
import by.naty.fitnesscenter.model.logic.ClientLogic;
import by.naty.fitnesscenter.model.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.*;

public class ClientCabinetCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private ClientLogic clientLogic;

    public ClientCabinetCommand(ClientLogic clientLogic) {
        this.clientLogic = clientLogic;
    }

    @Override
    public CommandRouter execute(HttpServletRequest request) throws CommandException {
        Client client = (Client) request.getSession().getAttribute(CLIENT);
        String actionButton = request.getParameter(REFUSE);

        if (actionButton.equals(REFUSE)) {

            LOG.info("User deleted workout.");
        }
        String page;
        try {
            request.getSession().setAttribute(WORKOUT, clientLogic.findAllOrderForClients(client.getId()));
            LOG.info("Set attribute workout.");
            page = ConfigurationManager.getProperty("path.page.client.cabinet");
            return new CommandRouter(CommandRouter.DispatchType.FORWARD, page);
        } catch (LogicException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
