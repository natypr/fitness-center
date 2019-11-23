package by.naty.fitnesscenter.model.command.impl;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.CommandRouter;
import by.naty.fitnesscenter.model.entity.*;
import by.naty.fitnesscenter.model.exception.CommandException;
import by.naty.fitnesscenter.model.exception.LogicException;
import by.naty.fitnesscenter.model.logic.ClientLogic;
import by.naty.fitnesscenter.model.logic.OrderLogic;
import by.naty.fitnesscenter.model.logic.TrainerLogic;
import by.naty.fitnesscenter.model.logic.UserLogic;
import by.naty.fitnesscenter.model.resource.ConfigurationManager;
import by.naty.fitnesscenter.model.resource.MessageManager;
import by.naty.fitnesscenter.model.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.*;

public class LoginCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private UserLogic userLogic;
    private ClientLogic clientLogic;
    private TrainerLogic trainerLogic;
    private OrderLogic orderLogic;

    public LoginCommand(UserLogic userLogic, ClientLogic clientLogic,
                        TrainerLogic trainerLogic, OrderLogic orderLogic) {
        this.userLogic = userLogic;
        this.clientLogic = clientLogic;
        this.trainerLogic = trainerLogic;
        this.orderLogic = orderLogic;
    }

    @Override
    public CommandRouter execute(HttpServletRequest request) throws CommandException {
        String page = null;
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        if (DataValidator.isPasswordCorrect(password) && DataValidator.isEmailCorrect(login)) {
            try {
                User user = userLogic.findUserByEmailAndPassword(login, password);
                request.getSession().setAttribute(USER, user);

                if (user != null) {
                    List<Trainer> trainers = trainerLogic.findAllTrainers();
                    request.getSession().setAttribute(TRAINERS, trainers);

                    if (user.getRole().equals(UserType.ADMIN.getTypeUser())) {
                        List<Client> clients = clientLogic.findAllClients();
                        request.getSession().setAttribute(CLIENTS, clients);
                        page = ConfigurationManager.getProperty("path.page.admin.main");
                        LOG.info("  Admin: " + user.getEmail() + " log in.");

                    } else if (user.getRole().equals(UserType.TRAINER.getTypeUser())) {
                        Trainer trainer = trainerLogic.findTrainerByEmail(login);
                        request.getSession().setAttribute(TRAINER, trainer);

                        List<Client> listOfAllClientsByIdTrainer = clientLogic.findAllClientsByIdTrainer(trainer.getId());
                        request.getSession().setAttribute(CLIENTS_OF_TRAINER, listOfAllClientsByIdTrainer);

                        Map<Client, List<Workout>> listOfClientInfo = new HashMap<>();
                        for (Client client : listOfAllClientsByIdTrainer) {
                            List<Workout> workouts = clientLogic.findAllWorkoutForClients(client.getId());
                            listOfClientInfo.put(client, workouts);
                        }

                        request.getSession().setAttribute(LIST_OF_CLIENT_INFO, listOfClientInfo);
                        page = ConfigurationManager.getProperty("path.page.trainer.cabinet");
                        LOG.info("  Trainer: " + user.getEmail() + " log in.");

                    } else {
                        Client client = clientLogic.findClientByEmail(login);
                        request.getSession().setAttribute(CLIENT, client);

//                        List<Order> orders = (List<Order>) orderLogic.findOrderByEmailClient(client.getEmail());
//                        request.getSession().setAttribute("order", orders); FIXME OrderLogic.findOrderByEmailClient()

                        List<Workout> workouts = clientLogic.findAllWorkoutForClients(client.getId());
                        request.getSession().setAttribute(WORKOUTS, workouts);

                        page = ConfigurationManager.getProperty("path.page.client.cabinet");
                        LOG.info(" Client: " + user.getEmail() + " log in.");
                    }

                } else {
                    request.setAttribute(ERROR_LOGIN_PASS_MESSAGE, MessageManager.getProperty("message.login.error"));
                    page = ConfigurationManager.getProperty("path.page.login");
                }
            } catch (LogicException e) {
                request.setAttribute(ERROR_LOGIN_PASS_MESSAGE, MessageManager.getProperty("message.login.error"));
                throw new CommandException(e);
            }
        } else {
            request.setAttribute(ERROR_LOGIN_PASS_MESSAGE, MessageManager.getProperty("message.login.empty"));
        }
        return new CommandRouter(CommandRouter.DispatchType.REDIRECT, page);
    }
}
