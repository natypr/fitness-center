package by.naty.fitnesscenter.model.command.impl;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.CommandRouter;
import by.naty.fitnesscenter.model.entity.Client;
import by.naty.fitnesscenter.model.entity.Order;
import by.naty.fitnesscenter.model.exception.CommandException;
import by.naty.fitnesscenter.model.exception.LogicException;
import by.naty.fitnesscenter.model.logic.ClientLogic;
import by.naty.fitnesscenter.model.logic.TrainerLogic;
import by.naty.fitnesscenter.model.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.*;

public class TrainerCabinetCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private static final String ADVISE_WORKOUT_ACTION = "Advise workout";
    private static final String UPDATE_WORKOUT_ACTION = "Update workout";

    private ClientLogic clientLogic;
    private TrainerLogic trainerLogic;

    public TrainerCabinetCommand(ClientLogic clientLogic, TrainerLogic trainerLogic) {
        this.clientLogic = clientLogic;
        this.trainerLogic = trainerLogic;
    }

    @Override
    public CommandRouter execute(HttpServletRequest request) throws CommandException {
        String[] checkbox = request.getParameterValues(SELECT_CLIENT);
        String actionButtonWorkout = request.getParameter(ACTION_WORKOUT);

        String typeOfWorkout = request.getParameter(TYPE_OF_WORKOUT);
        String numberOfWorkout = request.getParameter(NUMBER_OF_WORKOUT);
        String equipment = request.getParameter(EQUIPMENT);
        String description = request.getParameter(DESCRIPTION);

        String page;
        ArrayList<Client> clients = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();
        try {
            if (checkbox != null) {
                for (String s : checkbox) {
                    System.out.println(s);
                    Client currentClient = clientLogic.findClientByEmail(s);
                    clients.add(currentClient);
                }
            }

            for (Client client : clients) {
                Order order = new Order();
//                if (!typeOfWorkout.isEmpty() && !numberOfWorkout.isEmpty()) {
//                    order = new Order(typeOfWorkout, numberOfWorkout);
//                }
                if (actionButtonWorkout != null) {
                    switch (actionButtonWorkout) {
                        case ADVISE_WORKOUT_ACTION: {
                            trainerLogic.createOrderForClient(order);
                            LOG.info("Create workout for client " + client.getEmail());
                            break;
                        }
                        case UPDATE_WORKOUT_ACTION: {
                            trainerLogic.updateWorkout(order);
                            LOG.info("Update exercises for client" + client.getEmail());
                            break;
                        }
                    }
                }
                orders.add((Order) clientLogic.findAllOrderForClients(client.getId()));
                request.getSession().setAttribute(ORDERS, orders);
            }
            request.getSession().setAttribute(CLIENTS, clients);
            page = ConfigurationManager.getProperty("path.page.trainer.cabinet");
            return new CommandRouter(CommandRouter.DispatchType.REDIRECT, page);
        } catch (LogicException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}