package by.naty.fitnesscenter.model.command.impl;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.CommandRouter;
import by.naty.fitnesscenter.model.entity.Client;
import by.naty.fitnesscenter.model.entity.Order;
import by.naty.fitnesscenter.model.exception.CommandException;
import by.naty.fitnesscenter.model.exception.LogicException;
import by.naty.fitnesscenter.model.service.ClientService;
import by.naty.fitnesscenter.model.service.OrderService;
import by.naty.fitnesscenter.model.manager.ConfigurationManager;
import by.naty.fitnesscenter.model.manager.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.*;

public class ClientCabinetCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private ClientService clientService;
    private OrderService orderService;

    public ClientCabinetCommand(ClientService clientService, OrderService orderService) {
        this.clientService = clientService;
        this.orderService = orderService;
    }

    @Override
    public CommandRouter execute(HttpServletRequest request) throws CommandException {
        Client client = (Client) request.getSession().getAttribute(CLIENT);
        String radioSelectOrder = request.getParameter(SELECT_ORDER);
        String actionDeleteOrder = request.getParameter(REFUSE);

        String page;
        try {
            List<Order> orders = clientService.findAllOrderByIdClients(client.getId());
            request.getSession().setAttribute(ORDERS, orders);

            if (radioSelectOrder != null) {
                if (actionDeleteOrder != null) {
                    Order order = orderService.findOrderById(Long.parseLong(radioSelectOrder));
                    if (!order.isPaid()) {
                        orderService.deleteOrderById(order.getId());
                        LOG.info("User " + client.getEmail() + " deleted workout with id " + order.getId());
                        List<Order> ordersUpdated = clientService.findAllOrderByIdClients(client.getId());
                        request.getSession().setAttribute(ORDERS, ordersUpdated);
                    } else {
                        request.setAttribute(
                                ORDER_CANNOT_BE_DELETED, MessageManager.getProperty("messages.orderCannotBeDeleted"));
                        page = ConfigurationManager.getProperty("path.page.client.cabinet");
                        return new CommandRouter(CommandRouter.DispatchType.FORWARD, page);
                    }
                }
            } else {
                request.setAttribute(SELECT_ORDER_RADIO, MessageManager.getProperty("messages.selectOrderRadio"));
                page = ConfigurationManager.getProperty("path.page.client.cabinet");
                return new CommandRouter(CommandRouter.DispatchType.FORWARD, page);
            }
        } catch (LogicException e) {
            throw new CommandException(e.getMessage(), e);
        }

        page = ConfigurationManager.getProperty("path.page.client.cabinet");
        return new CommandRouter(CommandRouter.DispatchType.REDIRECT, page);
    }
}
