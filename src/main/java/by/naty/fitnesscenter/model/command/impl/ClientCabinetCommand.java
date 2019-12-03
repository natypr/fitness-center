package by.naty.fitnesscenter.model.command.impl;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.CommandRouter;
import by.naty.fitnesscenter.model.entity.Client;
import by.naty.fitnesscenter.model.entity.Order;
import by.naty.fitnesscenter.model.exception.CommandException;
import by.naty.fitnesscenter.model.exception.LogicException;
import by.naty.fitnesscenter.model.logic.ClientLogic;
import by.naty.fitnesscenter.model.logic.OrderLogic;
import by.naty.fitnesscenter.model.resource.ConfigurationManager;
import by.naty.fitnesscenter.model.resource.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.*;

public class ClientCabinetCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private ClientLogic clientLogic;
    private OrderLogic orderLogic;

    public ClientCabinetCommand(ClientLogic clientLogic, OrderLogic orderLogic) {
        this.clientLogic = clientLogic;
        this.orderLogic = orderLogic;
    }

    @Override
    public CommandRouter execute(HttpServletRequest request) throws CommandException {
        Client client = (Client) request.getSession().getAttribute(CLIENT);
        String radioSelectOrder = request.getParameter(SELECT_ORDER);
        String actionDeleteOrder = request.getParameter(REFUSE);

        String page;
        try {
            if (actionDeleteOrder != null) {
                Order order = orderLogic.findOrderById(Long.parseLong(radioSelectOrder));
                orderLogic.deleteOrderById(order.getId());
                LOG.info("User " + client.getEmail() + " deleted workout with id " + order.getId());

                request.getSession().setAttribute(ORDER_SUCCESSFULLY_DELETED, MessageManager.getProperty("messages.orderSuccessfullyDeleted"));
                page = ConfigurationManager.getProperty("path.page.client.cabinet");
                return new CommandRouter(CommandRouter.DispatchType.FORWARD, page);
            }
        } catch (LogicException e) {
            throw new CommandException(e.getMessage(), e);
        }


        try {
            List<Order> orders = clientLogic.findAllOrderForClients(client.getId());
            request.getSession().setAttribute(ORDERS, orders);
            LOG.debug("Show list of orders in client cabinet.");
            page = ConfigurationManager.getProperty("path.page.client.cabinet");
            return new CommandRouter(CommandRouter.DispatchType.FORWARD, page);
        } catch (LogicException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
