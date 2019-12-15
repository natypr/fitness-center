package by.naty.fitnesscenter.model.dao.impl;

import by.naty.fitnesscenter.model.dao.TrainerDao;
import by.naty.fitnesscenter.model.entity.Trainer;
import by.naty.fitnesscenter.model.entity.User;
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
            "INSERT INTO trainer (id, education, cost_per_one_workout)  VALUES (?, ?, ?);";

    private static final String FIND_ALL_TRAINERS =
            "SELECT `user`.id, role, `name`, surname, gender, year_old, email, password, education, cost_per_one_workout " +
                    "FROM `user` " +
                    "JOIN role_legend ON `user`.role_num=role_legend.role_num " +
                    "RIGHT JOIN trainer ON `user`.id=trainer.id";

    private static final String FIND_TRAINER_BY_ID =
            "SELECT `user`.id, role, `name`, surname, gender, year_old, email, password, education, cost_per_one_workout " +
                    "FROM `user` " +
                    "JOIN role_legend ON `user`.role_num=role_legend.role_num " +
                    "JOIN trainer ON `user`.id=trainer.id WHERE `trainer`.id = ?;";

    private static final String FIND_TRAINER_BY_EMAIL =
            "SELECT `user`.id, role, `name`, surname, gender, year_old, email, password, education, cost_per_one_workout " +
                    "FROM `user` " +
                    "JOIN role_legend ON `user`.role_num=role_legend.role_num " +
                    "JOIN trainer ON trainer.id=`user`.id WHERE email = ?;";

    private static final String UPDATE_TRAINER =
            "UPDATE trainer SET education=?, cost_per_one_workout=?  WHERE trainer.id=?;";

    private static final String SELECT_USER_FROM_TRAINER_TABLE = "SELECT `user`.id FROM trainer WHERE trainer.id=?;";

    private static final String DELETE_TRAINER_BY_ID = "DELETE FROM trainer WHERE id=?;";

    @Override
    public void createTrainer(Trainer trainer) throws DaoException {
        User user = createUserFromTrainer(trainer);
        user = new UserDaoImpl().createUserWithMaxId(user);
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_TRAINER)) {

            statement.setLong(1, user.getId());
            statement.setString(2, trainer.getEducation());
            statement.setDouble(3, trainer.getCostPerOneWorkout());
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
            List<Trainer> trainers;
            try (ResultSet resultSet = statement.getResultSet()) {
                trainers = new ArrayList<>();
                while (resultSet.next()) {
                    trainers.add(createTrainerFromResult(resultSet));
                }
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
            Optional<Trainer> trainerOptional;
            try (ResultSet resultSet = statement.executeQuery()) {
                trainerOptional = Optional.empty();
                if (resultSet.next()) {
                    trainerOptional = Optional.of(createTrainerFromResult(resultSet));
                }
            }
            return trainerOptional;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Trainer> findTrainerByEmail(String email) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_TRAINER_BY_EMAIL)) {

            statement.setString(1, email);
            Optional<Trainer> trainerOptional;
            try (ResultSet resultSet = statement.executeQuery()) {
                trainerOptional = Optional.empty();
                if (resultSet.next()) {
                    trainerOptional = Optional.of(createTrainerFromResult(resultSet));
                }
            }
            return trainerOptional;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void updateTrainer(Trainer trainer) throws DaoException {
        User user = createUserFromTrainer(trainer);
        new UserDaoImpl().updateUser(user);
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TRAINER)) {

            statement.setString(1, trainer.getEducation());
            statement.setDouble(2, trainer.getCostPerOneWorkout());
            statement.setLong(3, trainer.getId());
            statement.executeUpdate();
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

    private User createUserFromTrainer(Trainer trainer) {
        return new User(trainer.getId(), trainer.getRole(), trainer.getName(), trainer.getSurname(),
                trainer.getGender(), trainer.getYearOld(),
                trainer.getEmail(), trainer.getPassword(), trainer.isBlocked());
    }

    //from db to entity
    private Trainer createTrainerFromResult(ResultSet resultSet) throws SQLException, DaoException {
        long id = resultSet.getLong(ID);
        Optional<User> user = new UserDaoImpl().findUserById(id);
        return new Trainer(user.get(), resultSet.getLong(ID),
                resultSet.getString(EDUCATION),
                resultSet.getDouble(COST_PER_ONE_WORKOUT));
    }
}
