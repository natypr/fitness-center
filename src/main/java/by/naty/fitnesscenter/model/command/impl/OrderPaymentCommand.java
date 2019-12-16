package by.naty.fitnesscenter.model.command.impl;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.CommandRouter;
import by.naty.fitnesscenter.model.entity.Client;
import by.naty.fitnesscenter.model.entity.Order;
import by.naty.fitnesscenter.model.exception.CommandException;
import by.naty.fitnesscenter.model.exception.LogicException;
import by.naty.fitnesscenter.model.logic.ClientLogic;
import by.naty.fitnesscenter.model.logic.OrderLogic;
import by.naty.fitnesscenter.model.logic.TrainerLogic;
import by.naty.fitnesscenter.model.resource.ConfigurationManager;
import by.naty.fitnesscenter.model.resource.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.*;

public class OrderPaymentCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private ClientLogic clientLogic;
    private TrainerLogic trainerLogic;
    private OrderLogic orderLogic;

    public OrderPaymentCommand(ClientLogic clientLogic, TrainerLogic trainerLogic, OrderLogic orderLogic) {
        this.clientLogic = clientLogic;
        this.trainerLogic = trainerLogic;
        this.orderLogic = orderLogic;
    }

    private double calculateTotalPrice(double price, double discount) {
        return price - price * discount / 100;
    }

    @Override
    public CommandRouter execute(HttpServletRequest request) throws CommandException {
        String actionPayOrder = request.getParameter(PAY);
        String actionSelectOrder = request.getParameter(SELECT_ORDER);  //получить id выбраного заказа

        Client currentClient = (Client) request.getSession().getAttribute(CLIENT);
        long idClient = currentClient.getId();

        String page;
        try {
            List<Order> unpaidOrders = clientLogic.findAllUnpaidOrderByIdClients(idClient);
            request.getSession().setAttribute(UNPAID_ORDERS, unpaidOrders);
            LOG.debug("List of unpaid orders was shown.");

            if (actionSelectOrder != null) {
                long idOrder = Integer.parseInt(actionSelectOrder);
                LOG.debug("idOrder " + idOrder);
                Order order = orderLogic.findOrderById(idOrder);

                double price = orderLogic.calculatePriceByIdOrder(idOrder);
                request.getSession().setAttribute(PRICE, price);

                double newDiscount = clientLogic.calculateNewDiscountByIdClient(idClient, idOrder);
                request.getSession().setAttribute(NEW_DISCOUNT, newDiscount);

                double totalPrice = calculateTotalPrice(price, newDiscount);
                request.getSession().setAttribute(TOTAL_PRICE, totalPrice);

                if (actionPayOrder != null) {
                    orderLogic.payOrder(order);
                    //TODO установить скидку
                    currentClient.setDiscount(currentClient.getDiscount() + newDiscount);
                    LOG.info("Client with id " + idClient + " pay for order with id " + idOrder);

                    List<Order> unpaidOrdersUpdated = clientLogic.findAllUnpaidOrderByIdClients(idClient);
                    request.getSession().setAttribute(UNPAID_ORDERS, unpaidOrdersUpdated);

                    request.getSession().setAttribute(
                            ORDER_SUCCESSFULLY_PAID, MessageManager.getProperty("messages.orderSuccessfullyPaid"));
                }
            } else {
                request.getSession().setAttribute(
                        SELECT_ORDER_RADIO, MessageManager.getProperty("messages.selectOrderRadio"));
            }
        } catch (LogicException e) {
            throw new CommandException(e.getMessage(), e);
        }

        page = ConfigurationManager.getProperty("path.page.client.orderpayment");
        return new CommandRouter(CommandRouter.DispatchType.REDIRECT, page);
    }
}
