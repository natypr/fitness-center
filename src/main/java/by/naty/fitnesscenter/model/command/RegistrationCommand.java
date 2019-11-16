package by.naty.fitnesscenter.model.command;

import by.naty.fitnesscenter.model.entity.Client;
import by.naty.fitnesscenter.model.entity.Trainer;
import by.naty.fitnesscenter.model.entity.User;
import by.naty.fitnesscenter.model.entity.UserType;
import by.naty.fitnesscenter.model.exception.CommandFCException;
import by.naty.fitnesscenter.model.exception.LogicFCException;
import by.naty.fitnesscenter.model.logic.ClientLogic;
import by.naty.fitnesscenter.model.logic.TrainerLogic;
import by.naty.fitnesscenter.model.logic.UserLogic;
import by.naty.fitnesscenter.model.resource.ConfigurationManager;
import by.naty.fitnesscenter.model.resource.MessageManager;
import by.naty.fitnesscenter.model.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private static final String ROLE = "radio-role";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String GENDER = "radio-gender";
    private static final String YEAR_OLD = "year_old";

    private UserLogic userLogic;
    private ClientLogic clientLogic = new ClientLogic();
    private TrainerLogic trainerLogic = new TrainerLogic();

    public RegistrationCommand(UserLogic userLogic) {
        this.userLogic = userLogic;
    }

    private static boolean checkRegistration(String name, String surname,
                                             String email, String password, String yearOld){
        LOG.debug("!!! Check registration: " + name + " " + surname + " " + email + " " + password + " " + yearOld);
        return DataValidator.isNameCorrect(name) &&
                DataValidator.isSurnameCorrect(surname) &&
                DataValidator.isEmailCorrect(email) &&
                DataValidator.isPasswordCorrect(password) &&
                DataValidator.isYearsOldCorrect(yearOld);
    }

    @Override
    public CommandRF execute(HttpServletRequest request) throws CommandFCException {
        String page = null;
        String role = request.getParameter(ROLE);
        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String gender = request.getParameter(GENDER);
        String yearOld = request.getParameter(YEAR_OLD);

        if (checkRegistration(name, surname, email, password, yearOld)) {
            if (role.equals("Client")){
                LOG.debug("Role is Client.");
                User user = new User(UserType.CLIENT.getTypeUser(), name, surname, email, password);
                Client client = new Client(user, gender, Byte.parseByte(yearOld), 0.0);

                request.getSession().setAttribute("client", client);
                try {
                    clientLogic.addClient(client);
                } catch (LogicFCException e) {
                    throw new CommandFCException(e);
                }
            } else {
                User user = new User(UserType.TRAINER.getTypeUser(), name, surname, email, password);
                Trainer trainer = new Trainer(user);

                request.getSession().setAttribute("trainer", trainer);
                try {
                    trainerLogic.addTrainer(trainer);
                } catch (LogicFCException e) {
                    throw new CommandFCException();
                }
            }
            request.setAttribute("successMessage", MessageManager.getProperty("message.reg.success"));
            page = ConfigurationManager.getProperty("path.page.login");
            LOG.info(name + " (" + email + ") is registered now.");
        } else {
            LOG.debug("Data isn't correct.");
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.login.error"));
            page = ConfigurationManager.getProperty("path.page.reg");
        }
        return new CommandRF(CommandRF.DispatchType.REDIRECT, page);
    }
}
