package by.naty.fitnesscenter.model.dao.impl;

import by.naty.fitnesscenter.model.dao.TrainerDao;
import by.naty.fitnesscenter.model.entity.Trainer;
import by.naty.fitnesscenter.model.entity.User;
import by.naty.fitnesscenter.model.entity.Workout;
import by.naty.fitnesscenter.model.exception.DaoException;
import by.naty.fitnesscenter.model.exception.PoolException;
import by.naty.fitnesscenter.model.pool.ConnectionPool;
import by.naty.fitnesscenter.model.pool.ProxyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.*;

public class TrainerDaoImpl implements TrainerDao {

    private static final String CREATE_TRAINER =
            "INSERT INTO trainer (id, education, cost_per_hour)  VALUES (?, ?, ?);";

    private static final String FIND_ALL_TRAINERS =
            "SELECT `user`.id, role, `name`, surname, email, password, education, cost_per_hour " +
                    "FROM `user` RIGHT JOIN trainer ON `user`.id=trainer.id";

    private static final String FIND_TRAINER_BY_ID =
            "SELECT `user`.id, role, `name`, surname, email, password,  education, cost_per_hour " +
                    "FROM `user` JOIN trainer ON trainer.id=`user`.id WHERE `trainer`.id = ?;";

    private static final String FIND_TRAINER_BY_EMAIL =
            "SELECT `user`.id, role, `name`, surname, email, password,  education, cost_per_hour " +
                    "FROM `user` JOIN trainer ON trainer.id=`user`.id WHERE email = ?;";

    private static final String UPDATE_TRAINER =
            "UPDATE trainer SET trainer.id=?, education=?, cost_per_hous=?  WHERE trainer.id=?;";

    private static final String SELECT_USER_FROM_TRAINER_TABLE = "SELECT `user`.id FROM trainer WHERE trainer.id=?;";

    private static final String DELETE_TRAINER_BY_ID = "DELETE FROM trainer WHERE id=?;";

    private static final String CREATE_WORKOUT_FOR_CLIENT =
            "INSERT INTO workout (typeWorkout, nameOfWorkout, equipment, description, " +
                    "costPerOneWorkout, numberOfVisit, idTrainer, idOrder) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_MAX_ID_FOR_WORKOUT = "SELECT max(id) FROM workout;";

    private static final String UPDATE_WORKOUT =
            "UPDATE workout SET id=?, typeWorkout=?, nameOfWorkout=?, equipment=?, description=?, " +
                    "costPerOneWorkout=?, numberOfVisit=?, idTrainer=?, idOrder=? WHERE id=?;";

    private static final String DELETE_EXERCISES_BY_ID = "DELETE FROM workout WHERE id=?;";

    @Override
    public void createTrainer(Trainer trainer) throws DaoException {
        User user = createUserFromTrainer(trainer);
        user = new UserDaoImpl().createUserWithMaxId(user);
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_TRAINER)) {

            statement.setLong(1, user.getId());
            statement.setString(2, trainer.getEducation());
            statement.setDouble(3, trainer.getCostPerHour());
            statement.executeUpdate();
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Trainer> findAllTrainers() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeQuery(FIND_ALL_TRAINERS);
            ResultSet resultSet = statement.getResultSet();
            List<Trainer> trainers = new ArrayList<>();
            while (resultSet.next()) {
                trainers.add(createTrainerFromResult(resultSet));
            }
            return trainers;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Trainer> findTrainerById(long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_TRAINER_BY_ID)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<Trainer> trainerOptionalById = Optional.empty();
            if (resultSet.next()) {
                trainerOptionalById = Optional.of(createTrainerFromResult(resultSet));
            }
            return trainerOptionalById;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Trainer> findTrainerByEmail(String email) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_TRAINER_BY_EMAIL)) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            Optional<Trainer> trainerOptional = Optional.empty();
            if (resultSet.next()) {
                trainerOptional = Optional.of(createTrainerFromResult(resultSet));
            }
            return trainerOptional;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Trainer updateTrainer(Trainer trainer) throws DaoException {
        User user = createUserFromTrainer(trainer);
        new UserDaoImpl().updateUser(user);
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TRAINER)) {

            statement.setLong(1, trainer.getId());
            statement.setString(2, trainer.getEducation());
            statement.setDouble(3, trainer.getCostPerHour());
            statement.executeUpdate();
            return trainer;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteTrainerById(long id) throws DaoException {
        User user = new ClientDaoImpl().selectUserFromClientTable(id, SELECT_USER_FROM_TRAINER_TABLE);
        new UserDaoImpl().deleteUserById(user.getId());
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_TRAINER_BY_ID)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Workout createWorkoutForClient(Workout workout) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_WORKOUT_FOR_CLIENT);
             PreparedStatement preStatement = connection.prepareStatement(SELECT_MAX_ID_FOR_WORKOUT)) {

            statement.setString(1, workout.getTypeWorkout());
            statement.setString(2, workout.getNameOfWorkout());
            statement.setString(3, workout.getEquipment());
            statement.setString(4, workout.getDescription());
            statement.setDouble(5, workout.getCostPerOneWorkout());
            statement.setDouble(6, workout.getNumberOfVisit());
            statement.setDouble(7, workout.getIdTrainer());
            statement.setDouble(8, workout.getIdOrder());
            statement.executeUpdate();
            ResultSet resultSet = preStatement.executeQuery();
            if (resultSet.next()) {
                workout.setId(resultSet.getLong(1));
            }
            return workout;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Workout updateWorkout(Workout workout) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_WORKOUT)) {

            statement.setLong(1, workout.getId());
            statement.setString(2, workout.getTypeWorkout());
            statement.setString(3, workout.getNameOfWorkout());
            statement.setString(4, workout.getEquipment());
            statement.setString(5, workout.getDescription());
            statement.setDouble(6, workout.getCostPerOneWorkout());
            statement.setDouble(7, workout.getNumberOfVisit());
            statement.setDouble(8, workout.getIdTrainer());
            statement.setDouble(9, workout.getIdOrder());
            statement.executeUpdate();
            return workout;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteWorkoutById(long id) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_EXERCISES_BY_ID)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    private User createUserFromTrainer(Trainer trainer) {
        return new User(trainer.getId(), trainer.getRole(), trainer.getName(), trainer.getSurname(),
                trainer.getEmail(), trainer.getPassword());
    }

    private Trainer createTrainerFromResult(ResultSet resultSet) throws SQLException, DaoException {
        long id = resultSet.getLong(ID);
        Optional<User> user = new UserDaoImpl().findUserById(id);
        return new Trainer(user.get(), resultSet.getInt(ID),
                resultSet.getString(EDUCATION),
                resultSet.getDouble(COST_PER_HOUR));
    }
}
