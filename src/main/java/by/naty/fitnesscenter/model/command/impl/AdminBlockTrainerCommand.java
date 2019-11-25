package by.naty.fitnesscenter.model.command.impl;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.CommandRouter;
import by.naty.fitnesscenter.model.entity.Trainer;
import by.naty.fitnesscenter.model.exception.CommandException;
import by.naty.fitnesscenter.model.exception.LogicException;
import by.naty.fitnesscenter.model.logic.TrainerLogic;
import by.naty.fitnesscenter.model.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.*;

public class AdminBlockTrainerCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();
    
    private TrainerLogic trainerLogic;

    public AdminBlockTrainerCommand(TrainerLogic trainerLogic) {
        this.trainerLogic = trainerLogic;
    }

    @Override
    public CommandRouter execute(HttpServletRequest request) throws CommandException {
        String[] checkboxTrainer = request.getParameterValues(SELECT_TRAINER);
        String actionAdminBlockTrainer = request.getParameter(BUTTON_ADMIN_BLOCK_TRAINER);
        String actionAdminUnblockTrainer = request.getParameter(BUTTON_ADMIN_UNBLOCK_TRAINER);
        ArrayList<Trainer> trainers = new ArrayList<>();

        String page;
        try {
            if (checkboxTrainer != null) {
                for (String s : checkboxTrainer) {
                    Trainer currentTrainer = trainerLogic.findTrainerByEmail(s);
                    LOG.info("Find trainer " + currentTrainer);
                    trainers.add(currentTrainer);
                }
            }
            for (Trainer trainer : trainers) {
                if (actionAdminBlockTrainer != null) {
                    trainerLogic.deleteTrainerById(trainer.getId());
                    LOG.info("Blocked trainer");
                }
                if (actionAdminBlockTrainer != null) {
                    trainerLogic.deleteTrainerById(trainer.getId());
                    LOG.info("Unblocked trainer");
                }
            }
            request.getSession().setAttribute(TRAINERS, trainerLogic.findAllTrainers());
            page = ConfigurationManager.getProperty("path.page.admin.cabinet");
            return new CommandRouter(CommandRouter.DispatchType.REDIRECT, page);
        } catch (LogicException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
