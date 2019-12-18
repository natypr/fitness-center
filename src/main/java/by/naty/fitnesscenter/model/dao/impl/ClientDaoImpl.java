package by.naty.fitnesscenter.model.dao.impl;

import by.naty.fitnesscenter.model.dao.ClientDao;
import by.naty.fitnesscenter.model.entity.Client;
import by.naty.fitnesscenter.model.entity.Order;
import by.naty.fitnesscenter.model.entity.User;
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

    private static final String CREATE_CLIENT = "INSERT INTO client (`id`, `discount`) VALUES (?, ?);";

    private static final String FIND_ALL_CLIENTS =
            "SELECT `user`.id, role, `name`, surname, gender, year_old, email, password, discount " +
                    "FROM `user` " +
                    "JOIN role_legend ON `user`.role_num=role_legend.role_num " +
                    "RIGHT JOIN `client` ON `user`.id=client.id";

    private static final String FIND_CLIENT_BY_ID =
            "SELECT `user`.id, role, `name`, surname, gender, year_old, email, password, discount " +
                    "FROM `user` " +
                    "JOIN role_legend ON `user`.role_num=role_legend.role_num " +
                    "JOIN `client` ON `user`.id=`client`.id " +
                    "WHERE `client`.id = ?;";

    private static final String FIND_CLIENT_BY_EMAIL =
            "SELECT `user`.id, role, `name`, surname, gender, year_old, email, password, discount " +
                    "FROM `user` " +
                    "JOIN role_legend ON `user`.role_num=role_legend.role_num " +
                    "JOIN `client` ON `user`.id=client.id " +
                    "WHERE email=?;";

    private static final String UPDATE_CLIENT = "UPDATE client SET discount=? WHERE client.id=?;";

    private static final String SELECT_USER_FROM_CLIENT_TABLE = "SELECT `user`.id FROM client WHERE client.id=?;";

    private static final String DELETE_CLIENT_BY_ID = "DELETE FROM client WHERE client.id=?;";

    private static final String FIND_ALL_ORDER_BY_ID_CLIENT =
            "SELECT o.id, o.type_of_workout, o.number_of_workout, o.id_trainer, u.email, o.equipment, o.description, o.id_client, o.is_paid " +
                    "FROM `order` as o " +
                    "INNER JOIN user as u ON o.id_trainer=u.id " +
                    "WHERE o.id_client=?;";

    private static final String FIND_ALL_UNPAID_ORDER_BY_ID_CLIENT =
            "SELECT o.id, o.type_of_workout, o.number_of_workout, o.id_trainer, u.email, o.equipment, o.description, o.id_client, o.is_paid " +
                    "FROM `order` as o " +
                    "INNER JOIN user as u ON o.id_trainer=u.id " +
                    "WHERE o.is_paid=false AND o.id_client=?;";

    private static final String FIND_ALL_CLIENTS_BY_ID_TRAINER =    //FIXME
            "SELECT DISTINCT `user`.`id`, role, `name`, surname, gender, year_old, email, password, discount " +
                    "FROM `trainer` " +
                    "JOIN `order` ON `order`.id_trainer=`trainer`.id " +
                    "JOIN `user` ON `order`.id_client=`user`.id " +
                    "JOIN `role_legend` ON `user`.`role_num`=`role_legend`.`role_num`" +
                    "JOIN `client` ON `client`.`id`=`user`.id " +
                    "WHERE trainer.id=?;";

    private static final String UPDATE_DISCOUNT = "UPDATE `client` SET discount=? WHERE id=?;";

    @Override
    public void createClient(Client client) throws DaoException {
        User user = createUserFromClient(client);
        user = new UserDaoImpl().createUserWithMaxId(user);
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_CLIENT)) {

            statement.setLong(1, user.getId());
            statement.setDouble(2, client.getDiscount());
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
            List<Client> clients;
            try (ResultSet resultSet = statement.getResultSet()) {
                clients = new ArrayList<>();
                while (resultSet.next()) {
                    clients.add(createClientFromResult(resultSet));
                }
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
            Optional<Client> clientOptional;
            try (ResultSet resultSet = statement.executeQuery()) {
                clientOptional = Optional.empty();
                if (resultSet.next()) {
                    Client client = createClientFromResult(resultSet);
                    clientOptional = Optional.of(client);
                }
            }
            return clientOptional;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Client> findClientByEmail(String email) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_CLIENT_BY_EMAIL)) {

            statement.setString(1, email);
            Optional<Client> clientOptional;
            try (ResultSet resultSet = statement.executeQuery()) {
                clientOptional = Optional.empty();
                if (resultSet.next()) {
                    Client client = createClientFromResult(resultSet);
                    clientOptional = Optional.of(client);
                }
            }
            return clientOptional;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void updateClient(Client client) throws DaoException {
        User user = createUserFromClient(client);
        new UserDaoImpl().updateUser(user);
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CLIENT)) {

            statement.setDouble(1, client.getDiscount());
            statement.setLong(2, client.getId());

            statement.executeUpdate();
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteClientById(long id) throws DaoException {
        User user = selectUserFromClientTable(id, SELECT_USER_FROM_CLIENT_TABLE);
        new UserDaoImpl().blockUserById(user.getId());
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CLIENT_BY_ID)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Order> findAllOrderByIdClient(long idClient) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ORDER_BY_ID_CLIENT)) {

            statement.setLong(1, idClient);
            List<Order> orders;
            try (ResultSet resultSet = statement.executeQuery()) {
                orders = new ArrayList<>();
                while (resultSet.next()) {
                    orders.add(createOrderFromResult(resultSet));
                }
            }
            return orders;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Order> findAllUnpaidOrderByIdClient(long idClient) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_UNPAID_ORDER_BY_ID_CLIENT)) {

            statement.setLong(1, idClient);
            List<Order> orders;
            try (ResultSet resultSet = statement.executeQuery()) {
                orders = new ArrayList<>();
                while (resultSet.next()) {
                    orders.add(createOrderFromResult(resultSet));
                }
            }
            return orders;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Client> findAllClientsByIdTrainer(long idTrainer) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_CLIENTS_BY_ID_TRAINER)) {

            statement.setLong(1, idTrainer);
            List<Client> clients;
            try (ResultSet resultSet = statement.executeQuery()) {
                clients = new ArrayList<>();
                while (resultSet.next()) {
                    clients.add(createClientFromResult(resultSet));
                }
            }
            return clients;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void updateDiscount(long idClient, double discount) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_DISCOUNT)) {

            statement.setDouble(1, discount);
            statement.setLong(2, idClient);
            statement.executeUpdate();
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    private User createUserFromClient(Client client) {
        return new User(client.getId(), client.getRole(), client.getName(), client.getSurname(),
                client.getGender(), client.getYearOld(),
                client.getEmail(), client.getPassword(), client.isBlocked());
    }

    private Client createClientFromResult(ResultSet resultSet) throws SQLException, DaoException {
        long id = resultSet.getLong(ID);
        Optional<User> user = new UserDaoImpl().findUserById(id);
        return new Client(user.get(), resultSet.getLong(ID),
                resultSet.getDouble(DISCOUNT), null);
    }

    User selectUserFromClientTable(long idUser, String sufctFinal) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sufctFinal)) {

            statement.setLong(1, idUser);
            User user;
            try (ResultSet resultSet = statement.executeQuery()) {
                user = new User();
                if (resultSet.next()) {
                    user.setId(resultSet.getLong(ID));
                }
            }
            return user;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    private Order createOrderFromResult(ResultSet resultSet) throws SQLException {
        return new Order(resultSet.getLong(ID),
                resultSet.getString(TYPE_OF_WORKOUT),
                resultSet.getInt(NUMBER_OF_WORKOUT),
                resultSet.getLong(ID_TRAINER),
                resultSet.getString(EQUIPMENT),
                resultSet.getString(DESCRIPTION),
                resultSet.getLong(ID_CLIENT),
                resultSet.getBoolean(IS_PAID));
    }
}
