package by.naty.fitnesscenter.model.command.impl;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.CommandRouter;
import by.naty.fitnesscenter.model.entity.Client;
import by.naty.fitnesscenter.model.entity.ClientInfoWorkout;
import by.naty.fitnesscenter.model.entity.Workout;
import by.naty.fitnesscenter.model.exception.CommandException;
import by.naty.fitnesscenter.model.exception.LogicException;
import by.naty.fitnesscenter.model.logic.ClientLogic;
import by.naty.fitnesscenter.model.logic.TrainerLogic;
import by.naty.fitnesscenter.model.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.*;

public class TrainerCabinetCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private static final String ADD_WORKOUT_ACTION = "Add workout";
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

        String typeWorkout = request.getParameter(TYPE_OF_WORKOUT);
        String nameOfWorkout = request.getParameter(NAME_OF_WORKOUT);
        String equipment = request.getParameter(EQUIPMENT);
        String description = request.getParameter(DESCRIPTION);
        String costPerOneWorkout = request.getParameter(COST_PER_ONE_WORKOUT);
        String numberOfVisit = request.getParameter(NUMBER_OF_WORKOUT);

        String page;
        ArrayList<Client> clients = new ArrayList<>();
        List<ClientInfoWorkout> clientInfo = new ArrayList<>();
        try {
            if (checkbox != null) {
                for (String s : checkbox) {
                    System.out.println(s);
                    Client currentClient = clientLogic.findClientByEmail(s);
                    clients.add(currentClient);
                }
            }

            for (Client client : clients) {
                Workout workout = new Workout();
                if (!typeWorkout.isEmpty() && !nameOfWorkout.isEmpty() && !equipment.isEmpty() &&
                        !description.isEmpty() && !costPerOneWorkout.isEmpty() && !numberOfVisit.isEmpty()) {
                    workout = new Workout(typeWorkout, nameOfWorkout, equipment, description,
                            Double.parseDouble(costPerOneWorkout), Integer.parseInt(numberOfVisit));
                }
                if (actionButtonWorkout != null) {
                    switch (actionButtonWorkout) {
                        case ADD_WORKOUT_ACTION: {
                            trainerLogic.createWorkoutForClient(workout);
                            LOG.info("Create workout for client " + client.getEmail());
                            break;
                        }
                        case UPDATE_WORKOUT_ACTION: {
                            trainerLogic.updateWorkout(workout);
                            LOG.info("Update exercises for client" + client.getEmail());
                            break;
                        }
                    }
                }
                List<Workout> currentWorkout = clientLogic.findAllWorkoutForClients(client.getId());//FIXME
                clientInfo.add(new ClientInfoWorkout(client, currentWorkout));
            }
            request.getSession().setAttribute(CLIENT_INFO, clientInfo);
            page = ConfigurationManager.getProperty("path.page.trainer.cabinet");
            return new CommandRouter(CommandRouter.DispatchType.REDIRECT, page);
        } catch (LogicException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}