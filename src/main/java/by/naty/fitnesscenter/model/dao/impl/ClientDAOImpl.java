package by.naty.fitnesscenter.model.dao.impl;

import by.naty.fitnesscenter.model.dao.ClientDAO;
import by.naty.fitnesscenter.model.dao.DAOConstant;
import by.naty.fitnesscenter.model.entity.Client;
import by.naty.fitnesscenter.model.entity.User;
import by.naty.fitnesscenter.model.entity.Workout;
import by.naty.fitnesscenter.model.exception.DAOfcException;
import by.naty.fitnesscenter.model.exception.PoolFCException;
import by.naty.fitnesscenter.model.pool.ConnectionPool;
import by.naty.fitnesscenter.model.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDAOImpl implements ClientDAO {

    private static final String CREATE_CLIENT = "INSERT INTO `client` " +
            "(`id_user`, `gender`, `year_old`, `discount`)  VALUES (?, ?, ?, ?);";

    private static final String FIND_ALL_CLIENTS = "SELECT `user`.`id_user`, `role_name` AS `role`, " +
            "`name`, `surname`, `email`, `password`, `gender`, `year_old`, `discount` " +
            "FROM `user` RIGHT JOIN `client` ON `user`.`id_user`=`client`.`id_user`";

    private static final String FIND_CLIENT_BY_ID = "SELECT `user`.`id_user`, `role_name` AS `role`, " +
            "`name`, `surname`, `email`, `password`, `gender`, `year_old` `discount` " +
            "FROM `user` JOIN `client` ON `user`.`id_user`=`client`.`id_user` WHERE `client`.`id_user` = ?;";

    private static final String FIND_CLIENT_BY_EMAIL = "SELECT `user`.`id_user`, `role_name` AS `role`, " +
            "`name`, `surname`, `email`, `password`, `gender`, `year_old` `discount` " +
            "FROM `user` JOIN `client` ON `user`.`id_user`=`client`.`id_user` WHERE `user`.`email`=?;";

    private static final String UPDATE_CLIENT= "UPDATE `client` SET `client`.`id_user`=?, `gender`=?, " +
            "`year_old`=?, `client`.`discount`=?  WHERE `client`.`id_user`=?;";

    private static final String SELECT_USER_FROM_CLIENT_TABLE = "SELECT `user`.`id_user` FROM `client` WHERE `client`.`id_user`=?;";

    private static final String DELETE_CLIENT_BY_ID = "DELETE FROM `client` WHERE `client`.`id_user`=?;";


    private static final String FIND_ALL_WORKOUT_BY_ID_CLIENT = "SELECT `id_workout`, `type_workout`, " +
            "`name_of_workout`, `number_of_visit`, `id_trainer`, `id_order` " +
            "FROM `workout` LEFT JOIN client ON `user`.`id_user`=`client`.`id_user` WHERE `client`.`id_user`=?;";

    private static final String FIND_ALL_WORKOUT_BY_ID_CLIENT_TWO = "SELECT `name_of_workout`, `equipment`, " +
            "`description`, `cost_per_one_workout` " +
            "FROM `workout_info` LEFT JOIN client ON `user`.`id_user`=`client`.`id_user` WHERE `client`.`id_user`=?;";

    private static final String FIND_ALL_CLIENTS_BY_ID_TRAINER = "SELECT `user`.`id_user`, `role_name` AS `role`, " +
            "`name`, `surname`, `email`, `password` " +
            "FROM `user` RIGHT JOIN `client` ON `user`.`id_user`=`client`.`id_user` " +
            "LEFT JOIN `order` ON `client`.`id_client`=`order`.`client_idclient` WHERE `id_trainer`=?;";//FIXME

    @Override
    public void createClient(Client client) throws DAOfcException {
        User user = createUserFromClient(client);
        try(ProxyConnection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE_CLIENT)){

            statement.setLong(1, user.getIdUser());
            statement.setString(2, client.getGender());
            statement.setByte(3, client.getYearOld());
            statement.setDouble(4, client.getDiscount());
            statement.executeUpdate();
        } catch (SQLException | PoolFCException e) {
            throw new DAOfcException(e);
        }
    }

    private User createUserFromClient(Client client){
        return new User(client.getIdUser(), client.getRole(), client.getName(), client.getSurname(),
                client.getEmail(),client.getPassword());
    }

    @Override
    public List<Client> findAllClients() throws DAOfcException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeQuery(FIND_ALL_CLIENTS);
            ResultSet resultSet = statement.getResultSet();
            List<Client> clients = new ArrayList<>();
            while (resultSet.next()) {
                clients.add(createClientFromResult(resultSet));
            }
            return clients;
        } catch (SQLException | PoolFCException e) {
            throw new DAOfcException(e);
        }
    }

    private Client createClientFromResult(ResultSet resultSet) throws SQLException, DAOfcException {
        long id = resultSet.getLong(DAOConstant.ID_USER);
        Optional<User> user = new UserDAOImpl().findUserById(id);
        Client client = new Client(user.get(), resultSet.getLong(DAOConstant.ID_USER),
                resultSet.getString(DAOConstant.GENDER), resultSet.getByte(DAOConstant.YEAR_OLD),
                resultSet.getDouble(DAOConstant.DISCOUNT));
        return client;
    }

    @Override
    public Optional<Client> findClientById(long id) throws DAOfcException {
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
        } catch (SQLException | PoolFCException e) {
            throw new DAOfcException(e);
        }
    }

    @Override
    public Optional<Client> findClientByEmail(String email) throws DAOfcException {
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
        } catch (SQLException | PoolFCException e) {
            throw new DAOfcException(e);
        }
    }

    @Override
    public Client updateClient(Client client) throws DAOfcException {
        User user = createUserFromClient(client);
        new UserDAOImpl().updateUser(user);
        try(ProxyConnection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_CLIENT)){

            statement.setLong(1, client.getIdClient());
            statement.setString(2, client.getGender());
            statement.setByte(3, client.getYearOld());
            statement.setDouble(4, client.getDiscount());
            statement.executeUpdate();
            return client;
        } catch (SQLException | PoolFCException e) {
            throw new DAOfcException(e);
        }
    }

    @Override
    public void deleteClientById(long id) throws DAOfcException {
        User user = selectUserFromClientTable(id, SELECT_USER_FROM_CLIENT_TABLE);
        new UserDAOImpl().deleteUserById(user.getIdUser());
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CLIENT_BY_ID)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException | PoolFCException e) {
            throw new DAOfcException(e);
        }
    }

    User selectUserFromClientTable(long idUser, String sufctFinal) throws DAOfcException {
        try(ProxyConnection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sufctFinal)){

            statement.setLong(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();
            if(resultSet.next()){
                user.setIdUser(resultSet.getLong(DAOConstant.ID_USER));
            }
            return user;
        }
        catch (SQLException | PoolFCException e) {
            throw new DAOfcException(e);
        }
    }

    @Override
    public List<Workout> findAllWorkoutByIdClient(long idClient) throws DAOfcException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_WORKOUT_BY_ID_CLIENT)) {

            statement.setLong(1, idClient);
            ResultSet resultSet = statement.executeQuery();
            List<Workout> workouts = new ArrayList<>();
            while (resultSet.next()) {
                workouts.add(createWorkoutFromResult(resultSet));
            }
            return workouts;
        } catch (SQLException | PoolFCException e) {
            throw new DAOfcException(e);
        }
    }

    public Workout createWorkoutFromResult(ResultSet resultSet) throws SQLException {
        Workout workout = new Workout(resultSet.getLong(DAOConstant.ID_WORKOUT),
                resultSet.getString(DAOConstant.TYPE_WORKOUT),
                resultSet.getString(DAOConstant.NAME_OF_WORKOUT),
                resultSet.getString(DAOConstant.EQUIPMENT),
                resultSet.getString(DAOConstant.DESCRIPTION),
                resultSet.getDouble(DAOConstant.COST_PER_ONE_WORKOUT),
                resultSet.getInt(DAOConstant.NUMBER_OF_VISIT),
                resultSet.getLong(DAOConstant.ID_TRAINER),
                resultSet.getLong(DAOConstant.ID_ORDER));
        return workout;
    }

    @Override
    public List<Client> findAllClientsByIdTrainer(int idTrainer) throws DAOfcException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_CLIENTS_BY_ID_TRAINER)) {

            statement.setLong(1, idTrainer);
            ResultSet resultSet = statement.executeQuery();
            List<Client> clients = new ArrayList<>();
            while (resultSet.next()) {
                clients.add(createClientFromResult(resultSet));
            }
            return clients;
        } catch (SQLException | PoolFCException e) {
            throw new DAOfcException(e);
        }
    }
}
