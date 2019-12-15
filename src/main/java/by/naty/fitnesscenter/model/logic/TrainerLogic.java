package by.naty.fitnesscenter.model.logic;

import by.naty.fitnesscenter.model.dao.TrainerDao;
import by.naty.fitnesscenter.model.dao.impl.TrainerDaoImpl;
import by.naty.fitnesscenter.model.entity.Trainer;
import by.naty.fitnesscenter.model.exception.DaoException;
import by.naty.fitnesscenter.model.exception.LogicException;
import by.naty.fitnesscenter.model.util.MD5;

import java.util.List;

public class TrainerLogic {

    public void createTrainer(Trainer trainer) throws LogicException {
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

    public Trainer findTrainerById(long id) throws LogicException {
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

    public void updateTrainer(Trainer trainer) throws LogicException {
        TrainerDao trainerDAO = new TrainerDaoImpl();
        try {
            trainerDAO.updateTrainer(trainer);
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
}
