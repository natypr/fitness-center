package by.naty.fitnesscenter.model.command.impl;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.CommandRouter;
import by.naty.fitnesscenter.model.entity.Client;
import by.naty.fitnesscenter.model.entity.Order;
import by.naty.fitnesscenter.model.entity.Trainer;
import by.naty.fitnesscenter.model.exception.CommandException;
import by.naty.fitnesscenter.model.exception.LogicException;
import by.naty.fitnesscenter.model.service.ClientService;
import by.naty.fitnesscenter.model.service.OrderService;
import by.naty.fitnesscenter.model.service.TrainerService;
import by.naty.fitnesscenter.model.manager.ConfigurationManager;
import by.naty.fitnesscenter.model.manager.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.*;
import static by.naty.fitnesscenter.model.validator.DataValidator.isDescriptionCorrect;
import static by.naty.fitnesscenter.model.validator.DataValidator.isEquipmentCorrect;

public class TrainerCabinetCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private ClientService clientService;
    private TrainerService trainerService;
    private OrderService orderService;

    public TrainerCabinetCommand(ClientService clientService, TrainerService trainerService, OrderService orderService) {
        this.clientService = clientService;
        this.trainerService = trainerService;
        this.orderService = orderService;
    }

    @Override
    public CommandRouter execute(HttpServletRequest request) throws CommandException {
        LOG.debug("In trainer cabinet command.");
        Trainer trainer = (Trainer) request.getSession().getAttribute(TRAINER);
        String radioSelectOrder = request.getParameter(SELECT_ORDER);
        String actionUpdateOrder = request.getParameter(UPDATE_ORDER);

        String page;
        try {
            if (radioSelectOrder != null) {
                if (actionUpdateOrder != null) {
                    Order order = orderService.findOrderById(Long.parseLong(radioSelectOrder));
                    if (!order.isPaid()) {
                        String equipment = request.getParameter(EQUIPMENT);
                        String description = request.getParameter(DESCRIPTION);

                        if (isEquipmentCorrect(equipment) && isDescriptionCorrect(description)) {
                            order.setEquipment(equipment);
                            order.setDescription(description);
                            orderService.updateOrder(order);

                            LOG.info("Trainer " + trainer.getEmail() + " update order with id " + order.getId());
                            request.setAttribute(ORDER_SUCCESSFULLY_UPDATED,
                                    MessageManager.getProperty("messages.orderSuccessfullyUpdated"));

                            List<Client> newClients = clientService.findAllClientsByIdTrainer(trainer.getId());
                            for (Client client : newClients) {
                                List<Order> ordersOfClient = clientService.findAllOrderByIdClients(client.getId());
                                client.setOrderList(ordersOfClient);
                            }
                            request.getSession().setAttribute(CLIENTS, newClients);
                        } else {
                            LOG.info("Data isn't correct.");
                            request.setAttribute(
                                    DATA_IS_NOT_CORRECT, MessageManager.getProperty("message.dataIsNotCorrect"));
                        }
                        page = ConfigurationManager.getProperty("path.page.trainer.cabinet");
                        return new CommandRouter(CommandRouter.DispatchType.FORWARD, page);
                    }
                }
            } else {
                request.setAttribute(SELECT_WORKOUT_RADIO, MessageManager.getProperty("messages.selectWorkoutRadio"));
                page = ConfigurationManager.getProperty("path.page.trainer.cabinet");
                return new CommandRouter(CommandRouter.DispatchType.FORWARD, page);
            }
        } catch (LogicException e) {
            throw new CommandException(e.getMessage(), e);
        }

        page = ConfigurationManager.getProperty("path.page.trainer.cabinet");
        return new CommandRouter(CommandRouter.DispatchType.REDIRECT, page);
    }
}