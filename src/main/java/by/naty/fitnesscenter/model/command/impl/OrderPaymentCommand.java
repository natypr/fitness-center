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

public class OrderPaymentCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private ClientService clientService;
    private OrderService orderService;

    public OrderPaymentCommand(ClientService clientService, OrderService orderService) {
        this.clientService = clientService;
        this.orderService = orderService;
    }

    private double calculateTotalPrice(double price, double discount) {
        return price - price * discount / 100;
    }

    @Override
    public CommandRouter execute(HttpServletRequest request) throws CommandException {
        String actionPayOrder = request.getParameter(PAY);
        String actionSelectOrder = request.getParameter(SELECT_ORDER);

        Client currentClient = (Client) request.getSession().getAttribute(CLIENT);
        long idClient = currentClient.getId();

        String page;
        try {
            List<Order> unpaidOrders = clientService.findAllUnpaidOrderByIdClients(idClient);
            request.getSession().setAttribute(UNPAID_ORDERS, unpaidOrders);
            LOG.debug("List of unpaid orders was shown.");

            if (actionSelectOrder != null) {
                long idOrder = Integer.parseInt(actionSelectOrder);
                Order order = orderService.findOrderById(idOrder);
                request.getSession().setAttribute(ORDER, order);

                double price = orderService.calculatePriceByIdOrder(idOrder);
                request.getSession().setAttribute(PRICE, price);

                double newDiscount = clientService.calculateNewDiscountByIdClient(idClient, idOrder);
                request.getSession().setAttribute(NEW_DISCOUNT, newDiscount);

                double totalPrice = calculateTotalPrice(price, newDiscount);
                request.getSession().setAttribute(TOTAL_PRICE, totalPrice);

                LOG.debug("Price was calculated.");
            }

            if (actionPayOrder != null) {
                try {
                    Order currentOrder = (Order) request.getSession().getAttribute(ORDER);
                    long idOrder = currentOrder.getId();
                    double newDiscount = (double) (request.getSession().getAttribute(NEW_DISCOUNT));

                    orderService.payOrderWithDiscount(currentOrder, newDiscount);
                    currentClient.setDiscount(newDiscount);
                    LOG.info("Client with id " + idClient + " pay for order with id " + idOrder);

                    List<Order> unpaidOrdersUpdated = clientService.findAllUnpaidOrderByIdClients(idClient);
                    request.getSession().setAttribute(UNPAID_ORDERS, unpaidOrdersUpdated);

                    List<Order> orders = clientService.findAllOrderByIdClients(idClient);
                    request.getSession().setAttribute(ORDERS, orders);

                    request.setAttribute(
                            ORDER_SUCCESSFULLY_PAID, MessageManager.getProperty("messages.orderSuccessfullyPaid"));

                } catch (NullPointerException e) {
                    request.setAttribute(SELECT_ORDER_RADIO, MessageManager.getProperty("messages.selectOrderRadio"));
                }
            }
        } catch (LogicException e) {
            throw new CommandException(e.getMessage(), e);
        }

        page = ConfigurationManager.getProperty("path.page.client.orderpayment");
        return new CommandRouter(CommandRouter.DispatchType.FORWARD, page);
    }
}
