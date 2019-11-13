package by.naty.fitnesscenter.model.logic;

import by.naty.fitnesscenter.model.dao.TrainerDAO;
import by.naty.fitnesscenter.model.dao.impl.TrainerDAOImpl;
import by.naty.fitnesscenter.model.entity.Trainer;
import by.naty.fitnesscenter.model.entity.User;
import by.naty.fitnesscenter.model.entity.Workout;
import by.naty.fitnesscenter.model.exception.DAOfcException;
import by.naty.fitnesscenter.model.exception.LogicFCException;
import by.naty.fitnesscenter.model.util.MD5;

import java.util.List;

public class TrainerLogic {

    public void addTrainer(Trainer trainer) throws LogicFCException{
        TrainerDAO trainerDAO = new TrainerDAOImpl();
        try {
            trainer.setPassword(MD5.encrypt(trainer.getPassword()));
            trainerDAO.createTrainer(trainer);
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }

    public List<Trainer> findAllTrainers()  throws LogicFCException {
        TrainerDAO trainerDAO = new TrainerDAOImpl();
        try {
            return trainerDAO.findAllTrainers();
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }

    public Trainer findTrainerById(int id)  throws LogicFCException {
        TrainerDAO trainerDAO = new TrainerDAOImpl();
        try {
            return trainerDAO.findTrainerById(id).get();
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }

    public Trainer findTrainerByEmail(String email)  throws LogicFCException {
        TrainerDAO trainerDAO = new TrainerDAOImpl();
        try {
            return trainerDAO.findTrainerByEmail(email).get();
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }

    public User updateTrainer(Trainer trainer) throws LogicFCException {
        TrainerDAO trainerDAO = new TrainerDAOImpl();
        try {
            return trainerDAO.updateTrainer(trainer);
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }

    public void deleteTrainerById(long id) throws LogicFCException{
        TrainerDAO trainerDAO = new TrainerDAOImpl();
        try {
            trainerDAO.deleteTrainerById(id);
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }


    public Workout createWorkoutForClient(Workout workout) throws LogicFCException{
        TrainerDAO trainerDAO = new TrainerDAOImpl();
        try {
            return trainerDAO.createWorkoutForClient(workout);
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }

    public void updateWorkout(Workout workout) throws LogicFCException{
        TrainerDAO trainerDAO = new TrainerDAOImpl();
        try {
            trainerDAO.updateWorkout(workout);
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }

    public void deleteWorkoutById(long idWorkout) throws LogicFCException{
        TrainerDAO trainerDAO = new TrainerDAOImpl();
        try {
            trainerDAO.deleteWorkoutById(idWorkout);
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }
}
