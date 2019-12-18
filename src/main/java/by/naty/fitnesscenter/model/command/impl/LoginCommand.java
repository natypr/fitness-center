package by.naty.fitnesscenter.model.command.impl;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.CommandRouter;
import by.naty.fitnesscenter.model.entity.*;
import by.naty.fitnesscenter.model.exception.CommandException;
import by.naty.fitnesscenter.model.exception.LogicException;
import by.naty.fitnesscenter.model.service.ClientService;
import by.naty.fitnesscenter.model.service.TrainerService;
import by.naty.fitnesscenter.model.service.UserService;
import by.naty.fitnesscenter.model.manager.ConfigurationManager;
import by.naty.fitnesscenter.model.manager.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.*;
import static by.naty.fitnesscenter.model.validator.DataValidator.isEmailCorrect;
import static by.naty.fitnesscenter.model.validator.DataValidator.isPasswordCorrect;

public class LoginCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private UserService userService;
    private ClientService clientService;
    private TrainerService trainerService;

    public LoginCommand(UserService userService, ClientService clientService, TrainerService trainerService) {
        this.userService = userService;
        this.clientService = clientService;
        this.trainerService = trainerService;
    }

    @Override
    public CommandRouter execute(HttpServletRequest request) throws CommandException {
        String page = ConfigurationManager.getProperty("path.page.login");
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        if (isPasswordCorrect(password) && isEmailCorrect(login)) {
            try {
                User user = userService.findUserByEmailAndPassword(login, password);
                if (user != null) {
                    if (!user.isBlocked()) {
                        request.getSession().setAttribute(USER, user);

                        List<Trainer> trainers = trainerService.findAllTrainers();
                        request.getSession().setAttribute(TRAINERS, trainers);

                        UserType role = UserType.valueOf(user.getRole().toUpperCase());
                        LOG.debug("Role is " + role);
                        switch (role) {
                            case ADMIN:
                                List<User> users = userService.findAllUsers();
                                request.getSession().setAttribute(USERS, users);

                                List<Client> clients = clientService.findAllClients();
                                request.getSession().setAttribute(CLIENTS, clients);

                                page = ConfigurationManager.getProperty("path.page.admin.cabinet");
                                LOG.info("  Admin: " + user.getEmail() + " log in.");
                                break;
                            case TRAINER:
                                Trainer trainer = trainerService.findTrainerByEmail(login);
                                request.getSession().setAttribute(TRAINER, trainer);

                                List<Client> clientsByTrainer = clientService.findAllClientsByIdTrainer(trainer.getId());
                                for (Client client : clientsByTrainer) {
                                    List<Order> ordersOfClient = clientService.findAllOrderByIdClients(client.getId());
                                    client.setOrderList(ordersOfClient);
                                }
                                request.getSession().setAttribute(CLIENTS, clientsByTrainer);

                                page = ConfigurationManager.getProperty("path.page.trainer.cabinet");
                                LOG.info("  Trainer: " + user.getEmail() + " log in.");
                                break;
                            case CLIENT:
                                Client client = clientService.findClientByEmail(login);
                                request.getSession().setAttribute(CLIENT, client);

                                List<Order> orders = clientService.findAllOrderByIdClients(client.getId());
                                request.getSession().setAttribute(ORDERS, orders);

                                page = ConfigurationManager.getProperty("path.page.client.cabinet");
                                LOG.info(" Client: " + user.getEmail() + " log in.");
                                break;
                        }
                    } else {
                        request.setAttribute(
                                ERROR_LOGIN_PASS_MESSAGE, MessageManager.getProperty("message.login.userisblocked"));
                        return new CommandRouter(CommandRouter.DispatchType.FORWARD, page);
                    }
                } else {
                    LOG.info("This user does not exist.");
                    request.setAttribute(ERROR_LOGIN_PASS_MESSAGE, MessageManager.getProperty("message.login.error"));
                    return new CommandRouter(CommandRouter.DispatchType.FORWARD, page);
                }
            } catch (LogicException e) {
                request.setAttribute(ERROR_LOGIN_PASS_MESSAGE, MessageManager.getProperty("message.login.error"));
                throw new CommandException(e);
            }
        } else {
            LOG.info("Password or email isn't valid.");
            request.setAttribute(ERROR_LOGIN_PASS_MESSAGE, MessageManager.getProperty("message.login.notvalid"));
            return new CommandRouter(CommandRouter.DispatchType.FORWARD, page);
        }
        return new CommandRouter(CommandRouter.DispatchType.REDIRECT, page);
    }
}
