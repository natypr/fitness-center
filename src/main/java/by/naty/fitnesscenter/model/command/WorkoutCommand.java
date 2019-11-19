package by.naty.fitnesscenter.model.command;

import by.naty.fitnesscenter.model.entity.Client;
import by.naty.fitnesscenter.model.exception.CommandFCException;
import by.naty.fitnesscenter.model.exception.LogicFCException;
import by.naty.fitnesscenter.model.logic.ClientLogic;
import by.naty.fitnesscenter.model.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class WorkoutCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private ClientLogic clientLogic;

    public WorkoutCommand(ClientLogic clientLogic){
        this.clientLogic = clientLogic;
    }

    @Override
    public CommandRF execute(HttpServletRequest request) throws CommandFCException {
        Client client = (Client) request.getSession().getAttribute("client");
        String actionButton = request.getParameter("Refuse");

        if(actionButton.equals("Refuse")){
            //TODO удаляем !?
            LOG.info("User deleted workout.");
        }
        String page;
        try {
            request.getSession().setAttribute("workout", clientLogic.findAllWorkoutForClients(client.getId()));
            LOG.info("Set attribute workout.");
            page = ConfigurationManager.getProperty("path.page.client.cabinet");
            return new CommandRF(CommandRF.DispatchType.FORWARD, page);
        } catch (LogicFCException e) {
            throw new CommandFCException(e.getMessage(), e);
        }
    }
}
