package by.naty.fitnesscenter.model.command.impl;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.CommandRouter;
import by.naty.fitnesscenter.model.entity.Trainer;
import by.naty.fitnesscenter.model.exception.CommandException;
import by.naty.fitnesscenter.model.exception.LogicException;
import by.naty.fitnesscenter.model.logic.TrainerLogic;
import by.naty.fitnesscenter.model.resource.ConfigurationManager;
import by.naty.fitnesscenter.model.resource.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.*;

public class TrainerUpdateCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private TrainerLogic trainerLogic;

    public TrainerUpdateCommand(TrainerLogic trainerLogic) {
        this.trainerLogic = trainerLogic;
    }

    @Override
    public CommandRouter execute(HttpServletRequest request) throws CommandException {
        Trainer currentTrainer = (Trainer) request.getSession().getAttribute(TRAINER);
        String actionUpdateProfile = request.getParameter(UPDATE_PROFILE);

        String page;
        Trainer trainer = currentTrainer;
        try {
            if (actionUpdateProfile != null) {
                String name = request.getParameter(NAME);
                String surname = request.getParameter(SURNAME);
                String yearOld = request.getParameter(YEAR_OLD);
                String education = request.getParameter(EDUCATION);
                String costPerOneWorkout = request.getParameter(COST_PER_ONE_WORKOUT);

                trainer = trainerLogic.findTrainerById(currentTrainer.getId());
                trainer.setName(name);
                trainer.setSurname(surname);
                trainer.setYearOld(Byte.parseByte(yearOld));
                trainer.setEducation(education);
                trainer.setCostPerOneWorkout(Double.parseDouble(costPerOneWorkout));

                trainerLogic.updateTrainer(trainer);
                LOG.info("Update trainer " + trainer.getEmail());
                request.getSession().setAttribute(
                        SUCCESSFULLY_UPDATED, MessageManager.getProperty("messages.successfullyupdated"));
            }
            request.getSession().setAttribute(TRAINER, trainer);

            page = ConfigurationManager.getProperty("path.page.trainer.updateprofile");
            return new CommandRouter(CommandRouter.DispatchType.REDIRECT, page);
        } catch (LogicException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
