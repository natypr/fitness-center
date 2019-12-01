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
import java.util.List;

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
        String page = ConfigurationManager.getProperty("path.page.login");
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        if (DataValidator.isPasswordCorrect(password) && DataValidator.isEmailCorrect(login)) {
            try {
                User user = userLogic.findUserByEmailAndPassword(login, password);
                if (user != null) {
                    if (!user.isBlocked()) {
                        request.getSession().setAttribute(USER, user);

                        List<Trainer> trainers = trainerLogic.findAllTrainers();
                        request.getSession().setAttribute(TRAINERS, trainers);

                        UserType role = UserType.valueOf(user.getRole().toUpperCase());
                        LOG.debug(role + " " + CLIENT);
                        switch (role) {
                            case ADMIN:
                                List<User> users = userLogic.findAllUsers();
                                request.getSession().setAttribute(CLIENTS, users);

                                page = ConfigurationManager.getProperty("path.page.admin.cabinet");
                                LOG.info("  Admin: " + user.getEmail() + " log in.");
                                break;
                            case TRAINER:
                                Trainer trainer = trainerLogic.findTrainerByEmail(login);
                                request.getSession().setAttribute(TRAINER, trainer);

                                List<Client> listOfAllClientsByIdTrainer = clientLogic.findAllClientsByIdTrainer(trainer.getId());
                                request.getSession().setAttribute(CLIENTS_OF_TRAINER, listOfAllClientsByIdTrainer);

                                page = ConfigurationManager.getProperty("path.page.trainer.cabinet");
                                LOG.info("  Trainer: " + user.getEmail() + " log in.");
                                break;
                            case CLIENT:
                                Client client = clientLogic.findClientByEmail(login);
                                request.getSession().setAttribute(CLIENT, client);

                                List<Order> orders = clientLogic.findAllOrderForClients(client.getId());
                                request.getSession().setAttribute(ORDERS, orders);

                                page = ConfigurationManager.getProperty("path.page.client.cabinet");
                                LOG.info(" Client: " + user.getEmail() + " log in.");
                                break;
                        }
                    } else {
                        request.getSession().setAttribute(
                                ERROR_LOGIN_PASS_MESSAGE, MessageManager.getProperty("message.login.userisblocked"));
                    }
                } else {
                    LOG.info("This user does not exist.");
                    request.getSession().setAttribute(ERROR_LOGIN_PASS_MESSAGE, MessageManager.getProperty("message.login.error"));
                }
            } catch (LogicException e) {
                request.getSession().setAttribute(ERROR_LOGIN_PASS_MESSAGE, MessageManager.getProperty("message.login.error"));
                throw new CommandException(e);
            }
        } else {
            LOG.info("Password or email isn't valid.");
            request.getSession().setAttribute(ERROR_LOGIN_PASS_MESSAGE, MessageManager.getProperty("message.login.notvalid"));
        }
        return new CommandRouter(CommandRouter.DispatchType.REDIRECT, page);
    }
}
