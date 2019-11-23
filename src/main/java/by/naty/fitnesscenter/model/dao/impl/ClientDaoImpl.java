package by.naty.fitnesscenter.model.dao.impl;

import by.naty.fitnesscenter.model.dao.ClientDao;
import by.naty.fitnesscenter.model.entity.Client;
import by.naty.fitnesscenter.model.entity.User;
import by.naty.fitnesscenter.model.entity.Workout;
import by.naty.fitnesscenter.model.exception.DaoException;
import by.naty.fitnesscenter.model.exception.PoolException;
import by.naty.fitnesscenter.model.pool.ConnectionPool;
import by.naty.fitnesscenter.model.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.*;

public class ClientDaoImpl implements ClientDao {

    private static final String CREATE_CLIENT =
            "INSERT INTO client (`id`, `gender`, `year_old`, `discount`) VALUES (?, ?, ?, ?);";

    private static final String FIND_ALL_CLIENTS =
            "SELECT `user`.id, role, `name`, surname, email, password, gender, year_old, discount " +
                    "FROM `user` RIGHT JOIN `client` ON `user`.id=client.id";

    private static final String FIND_CLIENT_BY_ID =
            "SELECT `user`.id, role, `name`, surname, email, password, gender, year_old, discount " +
                    "FROM `user` JOIN `client` ON `user`.`id_user`=`client`.`id_user` " +
                    "WHERE `client`.`id_user` = ?;";

    private static final String FIND_CLIENT_BY_EMAIL =
            "SELECT `user`.id, role, `name`, surname, email, password, gender, year_old, discount " +
                    "FROM `user` JOIN `client` ON `user`.id=client.id " +
                    "WHERE email=?;";

    private static final String UPDATE_CLIENT =
            "UPDATE client SET client.id=?, gender=?, year_old=?, discount=? WHERE client.id=?;";

    private static final String SELECT_USER_FROM_CLIENT_TABLE = "SELECT `user`.id FROM client WHERE client.id=?;";

    private static final String DELETE_CLIENT_BY_ID = "DELETE FROM client WHERE client.id=?;";

    private static final String FIND_ALL_WORKOUT_BY_ID_CLIENT =
            "SELECT `workout`.id, type_workout, name_of_workout, number_of_visit, id_trainer, id_order " +
                    "FROM client " +
                    "JOIN `order` ON client.id=`order`.id_client " +
                    "JOIN workout ON `order`.id=workout.id_order " +
                    "WHERE client.id=?;";

    private static final String FIND_ALL_CLIENTS_BY_ID_TRAINER =
            "SELECT `user`.id, role, `name`, surname, email, password " +
                    "FROM workout " +
                    "JOIN `order` ON workout.id_order=`order`.id " +
                    "JOIN `user` ON `order`.id_client=`user`.id " +
                    "WHERE workout.id_trainer=?;";

    @Override
    public void createClient(Client client) throws DaoException {
        User user = createUserFromClient(client);
        user = new UserDaoImpl().createUserWithMaxId(user);
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_CLIENT)) {

            statement.setLong(1, user.getId());
            statement.setString(2, client.getGender());
            statement.setByte(3, client.getYearOld());
            statement.setDouble(4, client.getDiscount());
            statement.executeUpdate();
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Client> findAllClients() throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeQuery(FIND_ALL_CLIENTS);
            ResultSet resultSet = statement.getResultSet();
            List<Client> clients = new ArrayList<>();
            while (resultSet.next()) {
                clients.add(createClientFromResult(resultSet));
            }
            return clients;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Client> findClientById(long id) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_CLIENT_BY_ID)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<Client> clientOptionalById = Optional.empty();
            if (resultSet.next()) {
                Client client = createClientFromResult(resultSet);
                clientOptionalById = Optional.of(client);
            }
            return clientOptionalById;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Client> findClientByEmail(String email) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_CLIENT_BY_EMAIL)) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            Optional<Client> clientOptionalByEmail = Optional.empty();
            if (resultSet.next()) {
                Client client = createClientFromResult(resultSet);
                clientOptionalByEmail = Optional.of(client);
            }
            return clientOptionalByEmail;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Client updateClient(Client client) throws DaoException {
        User user = createUserFromClient(client);
        new UserDaoImpl().updateUser(user);
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CLIENT)) {

            statement.setLong(1, client.getId());
            statement.setString(2, client.getGender());
            statement.setByte(3, client.getYearOld());
            statement.setDouble(4, client.getDiscount());
            statement.executeUpdate();
            return client;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteClientById(long id) throws DaoException {
        User user = selectUserFromClientTable(id, SELECT_USER_FROM_CLIENT_TABLE);
        new UserDaoImpl().deleteUserById(user.getId());
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CLIENT_BY_ID)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Workout> findAllWorkoutByIdClient(long idClient) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_WORKOUT_BY_ID_CLIENT)) {

            statement.setLong(1, idClient);
            ResultSet resultSet = statement.executeQuery();
            List<Workout> workouts = new ArrayList<>();
            while (resultSet.next()) {
                workouts.add(createWorkoutFromResult(resultSet));
            }
            return workouts;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Client> findAllClientsByIdTrainer(long idTrainer) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_CLIENTS_BY_ID_TRAINER)) {

            statement.setLong(1, idTrainer);
            ResultSet resultSet = statement.executeQuery();
            List<Client> clients = new ArrayList<>();
            while (resultSet.next()) {
                clients.add(createClientFromResult(resultSet));
            }
            return clients;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    private User createUserFromClient(Client client) {
        return new User(client.getId(), client.getRole(), client.getName(), client.getSurname(),
                client.getEmail(), client.getPassword());
    }

    private Client createClientFromResult(ResultSet resultSet) throws SQLException, DaoException {
        long id = resultSet.getLong(ID);
        Optional<User> user = new UserDaoImpl().findUserById(id);
        return new Client(user.get(), resultSet.getLong(ID),
                resultSet.getString(GENDER), resultSet.getByte(YEAR_OLD),
                resultSet.getDouble(DISCOUNT));
    }

    User selectUserFromClientTable(long idUser, String sufctFinal) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sufctFinal)) {

            statement.setLong(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(ID));
            }
            return user;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    private Workout createWorkoutFromResult(ResultSet resultSet) throws SQLException {
        return new Workout(resultSet.getLong(ID),
                resultSet.getString(TYPE_WORKOUT),
                resultSet.getString(NAME_OF_WORKOUT),
                resultSet.getString(EQUIPMENT),
                resultSet.getString(DESCRIPTION),
                resultSet.getDouble(COST_PER_ONE_WORKOUT),
                resultSet.getInt(NUMBER_OF_VISIT),
                resultSet.getLong(ID_TRAINER),
                resultSet.getLong(ID_ORDER));
    }
}