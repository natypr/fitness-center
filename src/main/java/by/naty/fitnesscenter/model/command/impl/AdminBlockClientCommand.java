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
import java.util.ArrayList;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.*;

public class AdminBlockClientCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private ClientLogic clientLogic;

    public AdminBlockClientCommand(ClientLogic clientLogic) {
        this.clientLogic = clientLogic;
    }

    @Override
    public CommandRouter execute(HttpServletRequest request) throws CommandException {
        String[] checkboxClient = request.getParameterValues(SELECT_CLIENT);
        String actionAdminBlockClient = request.getParameter(BUTTON_ADMIN_BLOCK_CLIENT);
        String actionAdminUnblockClient = request.getParameter(BUTTON_ADMIN_UNBLOCK_CLIENT);
        ArrayList<Client> clients = new ArrayList<>();

        String page;
        try {
            if (checkboxClient != null) {
                for (String s : checkboxClient) {
                    Client currentClient = clientLogic.findClientByEmail(s);
                    LOG.info("Find client " + currentClient);
                    clients.add(currentClient);
                }
            }
            for (Client client : clients) {
                if (actionAdminBlockClient != null) {
                    clientLogic.deleteClientById(client.getId());
                    LOG.info("Blocked client.");
                }
                if (actionAdminUnblockClient != null) {
                    clientLogic.deleteClientById(client.getId());
                    LOG.info("Unblocked client.");
                }
            }
            request.getSession().setAttribute(CLIENTS, clientLogic.findAllClients());
            page = ConfigurationManager.getProperty("path.page.admin.cabinet");
            return new CommandRouter(CommandRouter.DispatchType.REDIRECT, page);
        } catch (LogicException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
