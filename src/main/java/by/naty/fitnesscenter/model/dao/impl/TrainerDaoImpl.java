package by.naty.fitnesscenter.model.dao.impl;

import by.naty.fitnesscenter.model.dao.TrainerDao;
import by.naty.fitnesscenter.model.entity.Order;
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
            "UPDATE trainer SET trainer.id=?, education=?, cost_per_one_workout=?  WHERE trainer.id=?;";

    private static final String SELECT_USER_FROM_TRAINER_TABLE = "SELECT `user`.id FROM trainer WHERE trainer.id=?;";

    private static final String DELETE_TRAINER_BY_ID = "DELETE FROM trainer WHERE id=?;";

    private static final String CREATE_ORDER_FOR_CLIENT =
            "INSERT INTO order (type_of_workout, number_of_workout, id_trainer, id_workout, id_client) " +
                    "VALUES (?, ?, ?, ?, ?);";

    private static final String SELECT_MAX_ID_FOR_ORDER = "SELECT max(id) FROM `order`;";

/*    private static final String UPDATE_WORKOUT =
            "UPDATE workout SET id=?, typeWorkout=?, nameOfWorkout=?, equipment=?, description=?, " +
                    "costPerOneWorkout=?, numberOfVisit=?, idTrainer=?, idOrder=? WHERE id=?;";*/

    //trainer update order (create workout where order.id_workout=workout.id)
    private static final String UPDATE_WORKOUT =
            "INSERT INTO workout (id, equipment, description) VALUES (?, ?, ?);";

    //trainer add order (create order with workout)
    private static final String TRAINER_ADD_ORDER =
            "INSERT INTO order () VALUES ();";

    private static final String DELETE_EXERCISES_BY_ID = "DELETE FROM workout WHERE id=?;";

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
            statement.setDouble(3, trainer.getCostPerOneWorkout());
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
    public Order createOrderForClient(Order order) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_ORDER_FOR_CLIENT);
             PreparedStatement preStatement = connection.prepareStatement(SELECT_MAX_ID_FOR_ORDER)) {

            statement.setString(1, order.getTypeOfWorkout());
            statement.setInt(2, order.getNumberOfWorkout());
            statement.setLong(3, order.getIdTrainer());
//            statement.setString(3, order.getEquipment());
//            statement.setString(4, order.getDescription());
    //            statement.setDouble(5, order.getIdWorkout()); //LOOKME add id workout
            statement.setDouble(5, order.getIdClient());
            statement.executeUpdate();
            ResultSet resultSet = preStatement.executeQuery();
            if (resultSet.next()) {
                order.setId(resultSet.getLong(1));
            }
            return order;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Order updateWorkout(Order order) throws DaoException {   //LOOKME and id to order.id.
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_WORKOUT)) {

            statement.setLong(1, order.getId());
            statement.setString(2, order.getEquipment());
            statement.setString(3, order.getDescription());
            return order;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteOrderForClientById(long id) throws DaoException {
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
                trainer.getGender(), trainer.getYearOld(),
                trainer.getEmail(), trainer.getPassword(), trainer.isBlocked());
    }

    //из бд в с
    private Trainer createTrainerFromResult(ResultSet resultSet) throws SQLException, DaoException {
        long id = resultSet.getLong(ID);
        Optional<User> user = new UserDaoImpl().findUserById(id);
        return new Trainer(user.get(), resultSet.getLong(ID),
                resultSet.getString(EDUCATION),
                resultSet.getDouble(COST_PER_ONE_WORKOUT));
    }
}
