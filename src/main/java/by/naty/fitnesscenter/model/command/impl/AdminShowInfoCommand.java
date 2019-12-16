package by.naty.fitnesscenter.model.command.impl;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.CommandRouter;
import by.naty.fitnesscenter.model.entity.Client;
import by.naty.fitnesscenter.model.entity.Trainer;
import by.naty.fitnesscenter.model.exception.CommandException;
import by.naty.fitnesscenter.model.exception.LogicException;
import by.naty.fitnesscenter.model.logic.ClientLogic;
import by.naty.fitnesscenter.model.logic.TrainerLogic;
import by.naty.fitnesscenter.model.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.CLIENTS;
import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.TRAINERS;

public class AdminShowInfoCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private ClientLogic clientLogic;
    private TrainerLogic trainerLogic;

    public AdminShowInfoCommand(ClientLogic clientLogic, TrainerLogic trainerLogic) {
        this.clientLogic = clientLogic;
        this.trainerLogic = trainerLogic;
    }

    @Override
    public CommandRouter execute(HttpServletRequest request) throws CommandException {

        String page;
        try {
            List<Client> clients = clientLogic.findAllClients();
            request.getSession().setAttribute(CLIENTS, clients);

            List<Trainer> trainers = trainerLogic.findAllTrainers();
            request.getSession().setAttribute(TRAINERS, trainers);
            LOG.info("List of clients and trainers was shown.");
        } catch (LogicException e) {
            throw new CommandException(e.getMessage(), e);
        }

        page = ConfigurationManager.getProperty("path.page.admin.allinfo");
        return new CommandRouter(CommandRouter.DispatchType.REDIRECT, page);
    }


}
