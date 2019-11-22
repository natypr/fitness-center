package by.naty.fitnesscenter.model.command;

import by.naty.fitnesscenter.model.entity.*;
import by.naty.fitnesscenter.model.exception.CommandFCException;
import by.naty.fitnesscenter.model.exception.LogicFCException;
import by.naty.fitnesscenter.model.logic.*;
import by.naty.fitnesscenter.model.resource.ConfigurationManager;
import by.naty.fitnesscenter.model.resource.MessageManager;
import by.naty.fitnesscenter.model.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

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
    public CommandRF execute(HttpServletRequest request) throws CommandFCException {
        String page = null;
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        if (DataValidator.isPasswordCorrect(password) && DataValidator.isEmailCorrect(login)){
            try {
                User user = userLogic.findUserByEmailAndPassword(login, password);
                request.getSession().setAttribute("user", user);

                if (user != null){
                    List<Trainer> trainers = trainerLogic.findAllTrainers();
                    request.getSession().setAttribute("trainers", trainers);

                    if (user.getRole().equals(UserType.ADMIN.getTypeUser())){
                        List<Client> clients = clientLogic.findAllClients();
                        request.getSession().setAttribute("clients", clients);
                        page = ConfigurationManager.getProperty("path.page.admin.main");
                        LOG.info("  Admin: " + user.getEmail() + " log in.");

                    } else if (user.getRole().equals(UserType.TRAINER.getTypeUser())){
                        Trainer trainer = trainerLogic.findTrainerByEmail(login);
                        request.getSession().setAttribute("trainer", trainer);

                        List<Client> listOfAllClientsByIdTrainer = clientLogic.findAllClientsByIdTrainer(trainer.getId());
                        request.getSession().setAttribute("clientsOfTrainer", listOfAllClientsByIdTrainer);

                        Map<Client, List<Workout>> listOfClientInfo = new HashMap<>();
                        for (Client client : listOfAllClientsByIdTrainer){
                            List<Workout> workouts = clientLogic.findAllWorkoutForClients(client.getId());
                            listOfClientInfo.put(client, workouts);
                        }

                        request.getSession().setAttribute("listOfClientInfo", listOfClientInfo);
                        page = ConfigurationManager.getProperty("path.page.trainer.cabinet");
                        LOG.info("  Trainer: " + user.getEmail() + " log in.");

                    } else {
                        Client client = clientLogic.findClientByEmail(login);
                        request.getSession().setAttribute("client", client);

//                        List<Order> orders = (List<Order>) orderLogic.findOrderByEmailClient(client.getEmail());
//                        request.getSession().setAttribute("order", orders); FIXME OrderLogic.findOrderByEmailClient()

                        List<Workout> workouts = clientLogic.findAllWorkoutForClients(client.getId());
                        request.getSession().setAttribute("exercises", workouts);

                        page = ConfigurationManager.getProperty("path.page.client.cabinet");
                        LOG.info(" Client: " + user.getEmail() + " log in.");
                    }

                } else {
                    request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.login.error"));
                    page = ConfigurationManager.getProperty("path.page.login");
                }
            } catch (LogicFCException e) {
                request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.login.error"));
                throw new CommandFCException(e);
            }
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.login.empty"));
        }
        return  new CommandRF(CommandRF.DispatchType.REDIRECT, page);
    }
}
