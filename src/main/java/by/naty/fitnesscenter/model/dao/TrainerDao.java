package by.naty.fitnesscenter.model.dao;

import by.naty.fitnesscenter.model.entity.Trainer;
import by.naty.fitnesscenter.model.entity.Workout;
import by.naty.fitnesscenter.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface TrainerDao {

    void createTrainer(Trainer trainer) throws DaoException;

    List<Trainer> findAllTrainers() throws DaoException;

    Optional<Trainer> findTrainerById(long id) throws DaoException;

    Optional<Trainer> findTrainerByEmail(String email) throws DaoException;

    Trainer updateTrainer(Trainer trainer) throws DaoException;

    void deleteTrainerById(long id) throws DaoException;


    Workout createWorkoutForClient(Workout workout) throws DaoException;

    Workout updateWorkout(Workout workout) throws DaoException;

    void deleteWorkoutById(long idWorkout) throws DaoException;

}
