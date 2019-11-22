package by.naty.fitnesscenter.model.command;

import by.naty.fitnesscenter.model.entity.Client;
import by.naty.fitnesscenter.model.entity.ClientInfoWorkout;
import by.naty.fitnesscenter.model.entity.Workout;
import by.naty.fitnesscenter.model.exception.CommandFCException;
import by.naty.fitnesscenter.model.exception.LogicFCException;
import by.naty.fitnesscenter.model.logic.ClientLogic;
import by.naty.fitnesscenter.model.logic.TrainerLogic;
import by.naty.fitnesscenter.model.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class TrainerCabinetCommand implements Command{
    private static final Logger LOG = LogManager.getLogger();

    private ClientLogic clientLogic;
    private TrainerLogic trainerLogic;

    public TrainerCabinetCommand(ClientLogic clientLogic, TrainerLogic trainerLogic) {
        this.clientLogic = clientLogic;
        this.trainerLogic = trainerLogic;
    }

    @Override
    public CommandRF execute(HttpServletRequest request) throws CommandFCException {
        String[] checkbox = request.getParameterValues("selectClient");
        String actionButtonWorkout = request.getParameter("actionWorkout");

        String typeWorkout = request.getParameter("type_workout");
        String nameOfWorkout = request.getParameter("name_of_workout");
        String equipment = request.getParameter("equipment");
        String description = request.getParameter("description");
        String costPerOneWorkout = request.getParameter("cost_per_one_workout");
        String numberOfVisit = request.getParameter("number_of_visit");

        String page;
        ArrayList<Client> clients = new ArrayList<>();
        List<ClientInfoWorkout> clientInfo = new ArrayList<>();
        try {
            if(checkbox != null){
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
                        case "Add workout": {
                            trainerLogic.createWorkoutForClient(workout);
                            LOG.info("Create workout for client " + client.getEmail());
                            break;
                        }
                        case "Delete workout": {
                            trainerLogic.deleteWorkoutById(workout.getId());
                            LOG.info("Delete workout for client" + client.getEmail());
                            break;
                        }
                        case "Update workout": {
                            trainerLogic.updateWorkout(workout);
                            LOG.info("Update exercises for client" + client.getEmail());
                            break;
                        }
                    }
                }
                List<Workout> currentWorkout = clientLogic.findAllWorkoutForClients(client.getId());//FIXME
                clientInfo.add(new ClientInfoWorkout(client, currentWorkout));
            }
            request.getSession().setAttribute("clientInfo", clientInfo);
            page = ConfigurationManager.getProperty("path.page.trainer.cabinet");
            return new CommandRF(CommandRF.DispatchType.REDIRECT, page);
        } catch (LogicFCException e) {
            throw new CommandFCException(e.getMessage(), e);
        }
    }
}