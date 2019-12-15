package by.naty.fitnesscenter.model.command.impl;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.CommandRouter;
import by.naty.fitnesscenter.model.entity.Client;
import by.naty.fitnesscenter.model.entity.Trainer;
import by.naty.fitnesscenter.model.entity.User;
import by.naty.fitnesscenter.model.entity.UserType;
import by.naty.fitnesscenter.model.exception.CommandException;
import by.naty.fitnesscenter.model.exception.LogicException;
import by.naty.fitnesscenter.model.logic.ClientLogic;
import by.naty.fitnesscenter.model.logic.TrainerLogic;
import by.naty.fitnesscenter.model.logic.UserLogic;
import by.naty.fitnesscenter.model.resource.ConfigurationManager;
import by.naty.fitnesscenter.model.resource.MessageManager;
import by.naty.fitnesscenter.model.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.*;

public class RegistrationCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private static final String CLIENT_ROLE = "Client";

    private UserLogic userLogic;
    private ClientLogic clientLogic = new ClientLogic();
    private TrainerLogic trainerLogic = new TrainerLogic();

    public RegistrationCommand(UserLogic userLogic) {
        this.userLogic = userLogic;
    }

    private static boolean checkRegistration(String name, String surname,
                                             String email, String password, String yearOld) {
        LOG.debug("Check registration: " + name + " " + surname + " " + email + " " + yearOld);
        return DataValidator.isNameCorrect(name) &&
                DataValidator.isSurnameCorrect(surname) &&
                DataValidator.isEmailCorrect(email) &&
                DataValidator.isPasswordCorrect(password) &&
                DataValidator.isYearsOldCorrect(yearOld);
    }

    @Override
    public CommandRouter execute(HttpServletRequest request) throws CommandException {
        String page = null;
        String role = request.getParameter(RADIO_ROLE);
        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String gender = request.getParameter(RADIO_GENDER);
        String yearOld = request.getParameter(YEAR_OLD);

        if (checkRegistration(name, surname, email, password, yearOld)) {
            if (role.equals(CLIENT_ROLE)) {
                LOG.debug("Role is Client.");
                User user = new User(UserType.CLIENT.getTypeUser(),
                        name, surname, gender, Byte.parseByte(yearOld), email, password);
                Client client = new Client(user);

                request.getSession().setAttribute(CLIENT, client);
                try {
                    clientLogic.createClient(client);
                } catch (LogicException e) {
                    throw new CommandException(e);
                }
            } else {
                User user = new User(UserType.TRAINER.getTypeUser(),
                        name, surname, gender, Byte.parseByte(yearOld), email, password);
                Trainer trainer = new Trainer(user);

                request.getSession().setAttribute(TRAINER, trainer);
                try {
                    trainerLogic.createTrainer(trainer);
                } catch (LogicException e) {
                    throw new CommandException();
                }
            }
            page = ConfigurationManager.getProperty("path.page.login");
            LOG.info(name + " (" + email + ") is registered now.");
        } else {
            LOG.info("Data isn't correct.");
            request.getSession().setAttribute(ERROR_LOGIN_PASS_MESSAGE, MessageManager.getProperty("message.login.error"));
            page = ConfigurationManager.getProperty("path.page.reg");
        }
        return new CommandRouter(CommandRouter.DispatchType.REDIRECT, page);
    }
}
