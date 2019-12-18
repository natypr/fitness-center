package by.naty.fitnesscenter.model.command.impl;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.CommandRouter;
import by.naty.fitnesscenter.model.entity.Trainer;
import by.naty.fitnesscenter.model.exception.CommandException;
import by.naty.fitnesscenter.model.exception.LogicException;
import by.naty.fitnesscenter.model.service.TrainerService;
import by.naty.fitnesscenter.model.manager.ConfigurationManager;
import by.naty.fitnesscenter.model.manager.MessageManager;
import by.naty.fitnesscenter.model.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.*;

public class TrainerUpdateCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private TrainerService trainerService;

    public TrainerUpdateCommand(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    private static boolean checkRegistration(String name, String surname, String yearOld,
                                             String education, String costPerOneWorkout) {
        return DataValidator.isNameCorrect(name) &&
                DataValidator.isSurnameCorrect(surname) &&
                DataValidator.isYearsOldCorrect(yearOld) &&
                DataValidator.isEducationCorrect(education) &&
                DataValidator.isCostPerOneWorkoutCorrect(costPerOneWorkout);
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

                if (checkRegistration(name, surname, yearOld, education, costPerOneWorkout)) {
                    trainer = trainerService.findTrainerById(currentTrainer.getId());
                    trainer.setName(name);
                    trainer.setSurname(surname);
                    trainer.setYearOld(Byte.parseByte(yearOld));
                    trainer.setEducation(education);
                    trainer.setCostPerOneWorkout(Double.parseDouble(costPerOneWorkout));

                    trainerService.updateTrainer(trainer);
                    LOG.info("Update trainer " + trainer.getEmail());
                    request.setAttribute(
                            SUCCESSFULLY_UPDATED, MessageManager.getProperty("messages.successfullyupdated"));
                } else {
                    LOG.info("Data isn't correct.");
                    request.setAttribute(DATA_IS_NOT_CORRECT, MessageManager.getProperty("message.dataIsNotCorrect"));
                }
                page = ConfigurationManager.getProperty("path.page.trainer.updateprofile");
                return new CommandRouter(CommandRouter.DispatchType.FORWARD, page);
            }
            request.getSession().setAttribute(TRAINER, trainer);

            page = ConfigurationManager.getProperty("path.page.trainer.updateprofile");
            return new CommandRouter(CommandRouter.DispatchType.REDIRECT, page);
        } catch (LogicException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
