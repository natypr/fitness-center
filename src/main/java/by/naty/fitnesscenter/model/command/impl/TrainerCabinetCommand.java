package by.naty.fitnesscenter.model.command.impl;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.CommandRouter;
import by.naty.fitnesscenter.model.entity.Client;
import by.naty.fitnesscenter.model.entity.Order;
import by.naty.fitnesscenter.model.entity.Trainer;
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

public class TrainerCabinetCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private ClientLogic clientLogic;
    private TrainerLogic trainerLogic;
    private OrderLogic orderLogic;

    public TrainerCabinetCommand(ClientLogic clientLogic, TrainerLogic trainerLogic, OrderLogic orderLogic) {
        this.clientLogic = clientLogic;
        this.trainerLogic = trainerLogic;
        this.orderLogic = orderLogic;
    }

    @Override
    public CommandRouter execute(HttpServletRequest request) throws CommandException {
        LOG.debug("In trainer cabinet command.");
        Trainer trainer = (Trainer) request.getSession().getAttribute(TRAINER);
        String radioSelectOrder = request.getParameter(SELECT_ORDER);
        String actionUpdateOrder = request.getParameter(UPDATE_ORDER);

        String page;
        try {
            List<Client> clients = clientLogic.findAllClientsByIdTrainer(trainer.getId());
            for (Client client : clients) {
                LOG.debug("Clients: " + clients);
                List<Order> ordersOfClient = clientLogic.findAllOrderForClients(client.getId());
                LOG.debug("Orders of client: " + ordersOfClient);
                client.setOrderList(ordersOfClient);
            }
            LOG.debug("Clients: " + clients);
            request.getSession().setAttribute(CLIENTS, clients);

            if (radioSelectOrder != null) {
                if (actionUpdateOrder != null) {
                    Order order = orderLogic.findOrderById(Long.parseLong(radioSelectOrder));
                    if (!order.isPaid()) {
                        String equipment = request.getParameter(EQUIPMENT);
                        String description = request.getParameter(DESCRIPTION);

                        order.setEquipment(equipment);
                        order.setDescription(description);
                        orderLogic.updateOrder(order);

                        LOG.info("Trainer " + trainer.getEmail() + " update order with id " + order.getId());
                        request.getSession().setAttribute(ORDER_SUCCESSFULLY_UPDATED,
                                MessageManager.getProperty("messages.orderSuccessfullyUpdated"));

                        List<Client> newClients = clientLogic.findAllClientsByIdTrainer(trainer.getId());
                        for (Client client : newClients) {
                            List<Order> ordersOfClient = clientLogic.findAllOrderForClients(client.getId());
                            client.setOrderList(ordersOfClient);
                        }
                        request.getSession().setAttribute(CLIENTS, newClients);
                    }
                }
            } else {
                request.getSession().setAttribute(SELECT_WORKOUT_RADIO, MessageManager.getProperty("messages.selectWorkoutRadio"));
            }
        } catch (LogicException e) {
            throw new CommandException(e.getMessage(), e);
        }

        page = ConfigurationManager.getProperty("path.page.trainer.cabinet");
        return new CommandRouter(CommandRouter.DispatchType.REDIRECT, page);
    }
}