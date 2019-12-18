package by.naty.fitnesscenter.model.command.impl;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.CommandRouter;
import by.naty.fitnesscenter.model.entity.Client;
import by.naty.fitnesscenter.model.exception.CommandException;
import by.naty.fitnesscenter.model.exception.LogicException;
import by.naty.fitnesscenter.model.service.ClientService;
import by.naty.fitnesscenter.model.manager.ConfigurationManager;
import by.naty.fitnesscenter.model.manager.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.*;
import static by.naty.fitnesscenter.model.validator.DataValidator.*;

public class ClientUpdateCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private ClientService clientService;

    public ClientUpdateCommand(ClientService clientService) {
        this.clientService = clientService;
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

                if (isNameCorrect(name) && isSurnameCorrect(surname) && isYearsOldCorrect(yearOld)) {
                    client = clientService.findClientById(currentClient.getId());
                    client.setName(name);
                    client.setSurname(surname);
                    client.setYearOld(Byte.parseByte(yearOld));

                    clientService.updateClient(client);
                    LOG.info("Update client " + client.getEmail());
                    request.setAttribute(SUCCESSFULLY_UPDATED, MessageManager.getProperty("messages.successfullyupdated"));
                } else {
                    LOG.info("Data isn't correct.");
                    request.setAttribute(DATA_IS_NOT_CORRECT, MessageManager.getProperty("message.dataIsNotCorrect"));
                    page = ConfigurationManager.getProperty("path.page.client.updateprofile");
                    return new CommandRouter(CommandRouter.DispatchType.FORWARD, page);
                }
            }
            request.getSession().setAttribute(CLIENT, client);

            page = ConfigurationManager.getProperty("path.page.client.updateprofile");
            return new CommandRouter(CommandRouter.DispatchType.FORWARD, page);
        } catch (LogicException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
