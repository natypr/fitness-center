package by.naty.fitnesscenter.model.command.impl;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.CommandRouter;
import by.naty.fitnesscenter.model.entity.Client;
import by.naty.fitnesscenter.model.exception.CommandException;
import by.naty.fitnesscenter.model.exception.LogicException;
import by.naty.fitnesscenter.model.logic.ClientLogic;
import by.naty.fitnesscenter.model.resource.ConfigurationManager;
import by.naty.fitnesscenter.model.resource.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.*;

public class ClientUpdateCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private ClientLogic clientLogic;

    public ClientUpdateCommand(ClientLogic clientLogic) {
        this.clientLogic = clientLogic;
    }

    @Override
    public CommandRouter execute(HttpServletRequest request) throws CommandException {
        Client currentClient = (Client) request.getSession().getAttribute(CLIENT);
        String actionUpdateProfile = request.getParameter(UPDATE_PROFILE);

        String page;
        Client client = currentClient;
        try {
            if (actionUpdateProfile != null) {
                String name = request.getParameter(NAME);
                String surname = request.getParameter(SURNAME);
                String yearOld = request.getParameter(YEAR_OLD);

                client = clientLogic.findClientById(currentClient.getId());
                client.setName(name);
                client.setSurname(surname);
                client.setYearOld(Byte.parseByte(yearOld));

                clientLogic.updateClient(client);
                LOG.info("Update client " + client.getEmail());
                request.getSession().setAttribute(
                        SUCCESSFULLY_UPDATED, MessageManager.getProperty("messages.successfullyupdated"));
            }
            request.getSession().setAttribute(CLIENT, client);

            page = ConfigurationManager.getProperty("path.page.client.updateprofile");
            return new CommandRouter(CommandRouter.DispatchType.REDIRECT, page);
        } catch (LogicException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
