package by.naty.fitnesscenter.model.command.impl;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.CommandRouter;
import by.naty.fitnesscenter.model.entity.Client;
import by.naty.fitnesscenter.model.entity.Order;
import by.naty.fitnesscenter.model.entity.Trainer;
import by.naty.fitnesscenter.model.exception.CommandException;
import by.naty.fitnesscenter.model.exception.LogicException;
import by.naty.fitnesscenter.model.logic.OrderLogic;
import by.naty.fitnesscenter.model.logic.TrainerLogic;
import by.naty.fitnesscenter.model.resource.ConfigurationManager;
import by.naty.fitnesscenter.model.resource.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.*;

public class OrderCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private TrainerLogic trainerLogic;
    private OrderLogic orderLogic;

    public OrderCommand(TrainerLogic trainerLogic, OrderLogic orderLogic) {
        this.trainerLogic = trainerLogic;
        this.orderLogic = orderLogic;
    }

    @Override
    public CommandRouter execute(HttpServletRequest request) throws CommandException {
        String page;
        String selectTypeOfWorkout = request.getParameter(SELECT_TYPE_OF_WORKOUT);
        String numberOfWorkout = request.getParameter(NUMBER_OF_WORKOUT);
        String radioSelectTrainer = request.getParameter(RADIO_SELECT_TRAINER);
        Client client = (Client) request.getSession().getAttribute(CLIENT);
        Order order = new Order();
        try {
            Trainer trainer = trainerLogic.findTrainerByEmail(radioSelectTrainer);
            order.setTypeOfWorkout(selectTypeOfWorkout);
            order.setNumberOfWorkout(Integer.parseInt(numberOfWorkout));
            order.setIdTrainer(trainer.getId());
            order.setIdClient(client.getId());
            order.setPaid(false);
            orderLogic.createOrder(order);

            LOG.info("Create order by " + client.getEmail());
            request.getSession().setAttribute(SUCCESSFUL_ORDER, MessageManager.getProperty("messages.orderdone"));
            page = ConfigurationManager.getProperty("path.page.client.order");
        } catch (LogicException e) {
            throw new CommandException(e.getMessage(), e);
        }
        return new CommandRouter(CommandRouter.DispatchType.REDIRECT, page);
    }
}