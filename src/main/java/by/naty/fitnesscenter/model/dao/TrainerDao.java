package by.naty.fitnesscenter.model.dao;

import by.naty.fitnesscenter.model.entity.Trainer;
import by.naty.fitnesscenter.model.entity.Workout;
import by.naty.fitnesscenter.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * The Interface TrainerDao.
 */
public interface TrainerDao {

    /**
     * Creates the trainer.
     *
     * @param trainer the trainer
     * @throws DaoException the dao exception
     */
    void createTrainer(Trainer trainer) throws DaoException;

    /**
     * Finds all trainers.
     *
     * @return the list of trainers
     * @throws DaoException the dao exception
     */
    List<Trainer> findAllTrainers() throws DaoException;

    /**
     * Finds trainer by id.
     *
     * @param id the id of trainer
     * @return the optional trainer
     * @throws DaoException the dao exception
     */
    Optional<Trainer> findTrainerById(long id) throws DaoException;

    /**
     * Finds trainer by email.
     *
     * @param email the email of trainer
     * @return the optional trainer
     * @throws DaoException the dao exception
     */
    Optional<Trainer> findTrainerByEmail(String email) throws DaoException;

    /**
     * Updates trainer.
     *
     * @param trainer the trainer
     * @return the updated trainer
     * @throws DaoException the dao exception
     */
    Trainer updateTrainer(Trainer trainer) throws DaoException;

    /**
     * Deletes trainer by id.
     *
     * @param id the id of trainer
     * @throws DaoException the dao exception
     */
    void deleteTrainerById(long id) throws DaoException;


    /**
     * Creates workout for client.
     *
     * @param workout the workout
     * @return the workout
     * @throws DaoException the dao exception
     */
    Workout createWorkoutForClient(Workout workout) throws DaoException;

    /**
     * Updates workout.
     *
     * @param workout the workout
     * @return the updated workout
     * @throws DaoException the dao exception
     */
    Workout updateWorkout(Workout workout) throws DaoException;

    /**
     * Deletes workout by id.
     *
     * @param idWorkout the id of workout
     * @throws DaoException the dao exception
     */
    void deleteWorkoutById(long idWorkout) throws DaoException;
}
