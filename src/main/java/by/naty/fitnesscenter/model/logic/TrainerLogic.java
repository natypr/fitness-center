package by.naty.fitnesscenter.model.logic;

import by.naty.fitnesscenter.model.dao.TrainerDao;
import by.naty.fitnesscenter.model.dao.impl.TrainerDaoImpl;
import by.naty.fitnesscenter.model.entity.Trainer;
import by.naty.fitnesscenter.model.entity.User;
import by.naty.fitnesscenter.model.entity.Workout;
import by.naty.fitnesscenter.model.exception.DaoException;
import by.naty.fitnesscenter.model.exception.LogicException;
import by.naty.fitnesscenter.model.util.MD5;

import java.util.List;

public class TrainerLogic {

    public void addTrainer(Trainer trainer) throws LogicException {
        TrainerDao trainerDAO = new TrainerDaoImpl();
        try {
            trainer.setPassword(MD5.encrypt(trainer.getPassword()));
            trainerDAO.createTrainer(trainer);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public List<Trainer> findAllTrainers() throws LogicException {
        TrainerDao trainerDAO = new TrainerDaoImpl();
        try {
            return trainerDAO.findAllTrainers();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public Trainer findTrainerById(int id) throws LogicException {
        TrainerDao trainerDAO = new TrainerDaoImpl();
        try {
            return trainerDAO.findTrainerById(id).get();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public Trainer findTrainerByEmail(String email) throws LogicException {
        TrainerDao trainerDAO = new TrainerDaoImpl();
        try {
            return trainerDAO.findTrainerByEmail(email).get();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public User updateTrainer(Trainer trainer) throws LogicException {
        TrainerDao trainerDAO = new TrainerDaoImpl();
        try {
            return trainerDAO.updateTrainer(trainer);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public void deleteTrainerById(long id) throws LogicException {
        TrainerDao trainerDAO = new TrainerDaoImpl();
        try {
            trainerDAO.deleteTrainerById(id);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }


    public Workout createWorkoutForClient(Workout workout) throws LogicException {
        TrainerDao trainerDAO = new TrainerDaoImpl();
        try {
            return trainerDAO.createWorkoutForClient(workout);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public void updateWorkout(Workout workout) throws LogicException {
        TrainerDao trainerDAO = new TrainerDaoImpl();
        try {
            trainerDAO.updateWorkout(workout);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public void deleteWorkoutById(long idWorkout) throws LogicException {
        TrainerDao trainerDAO = new TrainerDaoImpl();
        try {
            trainerDAO.deleteWorkoutById(idWorkout);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }
}
