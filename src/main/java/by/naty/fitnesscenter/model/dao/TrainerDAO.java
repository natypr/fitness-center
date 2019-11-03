package by.naty.fitnesscenter.model.dao;

import by.naty.fitnesscenter.model.entity.Trainer;
import by.naty.fitnesscenter.model.entity.Workout;
import by.naty.fitnesscenter.model.exception.DAOfcException;

import java.util.List;
import java.util.Optional;

public interface TrainerDAO {

    void create(Trainer trainer) throws DAOfcException;

    List<Trainer> findAllTrainers() throws DAOfcException;

    Optional<Trainer> findTrainerById(long id) throws DAOfcException;

    Trainer updateTrainerByTrainer(Trainer trainer) throws DAOfcException;

    void deleteTrainer(long id) throws DAOfcException;


    Workout createWorkoutForClient(Workout workout) throws DAOfcException;

    Workout updateWorkout(Workout workout) throws DAOfcException;

    void deleteWorkoutById(long idWorkout) throws DAOfcException;

}
