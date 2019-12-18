package by.naty.fitnesscenter.model.dao.impl;

import by.naty.fitnesscenter.model.dao.OrderDao;
import by.naty.fitnesscenter.model.entity.Order;
import by.naty.fitnesscenter.model.exception.DaoException;
import by.naty.fitnesscenter.model.exception.PoolException;
import by.naty.fitnesscenter.model.pool.ConnectionPool;
import by.naty.fitnesscenter.model.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.*;

public class OrderDaoImpl implements OrderDao {
    private static final Logger LOG = LogManager.getLogger();

    private static final String CREATE_ORDER =
            "INSERT INTO `order` (type_of_workout, number_of_workout, id_trainer, equipment, description, id_client, is_paid) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_MAX_ID_FROM_ORDER = "SELECT max(id) FROM `order`;";

    private static final String FIND_ALL_ORDERS =
            "SELECT id, type_of_workout, number_of_workout, id_trainer, equipment, description, id_client, is_paid " +
                    "FROM `order` ORDER BY id;";

    private static final String FIND_ALL_ORDERS_BY_ID_TRAINER =
            "SELECT id, type_of_workout, number_of_workout, id_trainer, equipment, description, id_client, is_paid " +
                    "FROM `order` WHERE id_trainer=? " +
                    "ORDER BY id;";

    private static final String FIND_ORDERS_OF_CLIENTS_BY_ID_TRAINER = //TODO!!!
            "SELECT id, type_of_workout, number_of_workout, id_trainer, equipment, description, id_client, is_paid " +
                    "FROM `order` WHERE id_client=? AND id_trainer=? " +
                    "ORDER BY id;";

    private static final String FIND_ORDER_BY_ID =
            "SELECT id, type_of_workout, number_of_workout, id_trainer, equipment, description, id_client, is_paid " +
                    "FROM `order` WHERE `order`.id=?;";

    private static final String UPDATE_ORDER = "UPDATE `order` SET equipment=?, description=? WHERE id=?;";

    private static final String DELETE_ORDER_BY_ID = "DELETE FROM `order` WHERE `order`.id=?;";

    private static final String PAY_ORDER = "UPDATE `order` SET is_paid=1 WHERE id=?;";

    private static Byte modifyIsPaidToByte(boolean bool) {
        return bool ? (byte) 1 : (byte) 0;
    }

    @Override
    public void createOrder(Order order) throws DaoException {
        new OrderDaoImpl().createOrderWithMaxId(order);
    }

    @Override
    public List<Order> findAllOrders() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeQuery(FIND_ALL_ORDERS);
            List<Order> orders;
            try (ResultSet resultSet = statement.getResultSet()) {
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
    public List<Order> findAllOrdersByIdTrainer(long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeQuery(FIND_ALL_ORDERS_BY_ID_TRAINER);
            List<Order> orders;
            try (ResultSet resultSet = statement.getResultSet()) {
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

    /*@Override
    public List<Order> findOrdersOfClientsByIdTrainer(long idClient, long idTrainer) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ORDERS_OF_CLIENTS_BY_ID_TRAINER)) {

            statement.setLong(1, idClient);
            statement.setLong(2, idTrainer);
            //statement.executeQuery(FIND_ORDERS_OF_CLIENTS_BY_ID_TRAINER);
            List<Order> orders;
            try (ResultSet resultSet = statement.getResultSet()) {
                orders = new ArrayList<>();
                try {
                    while (resultSet.next()) {
                        orders.add(createOrderFromResult(resultSet));
                    }
                } catch (NullPointerException e){
                    LOG.debug("Null.." + e);
                }

            }
            return orders;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }
*/
/*
    @Override
    public Optional<List<Order>> findOrdersOfClientsByIdTrainer(long idClient, long idTrainer) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ORDERS_OF_CLIENTS_BY_ID_TRAINER)) {

            statement.setLong(1, idClient);
            statement.setLong(2, idTrainer);
            Optional<List<Order>> ordersOptional;
            try (ResultSet resultSet = statement.executeQuery()) {
                ordersOptional = Optional.empty();
                if (resultSet.next()) {
                    Order order = createOrderFromResult(resultSet);
                    ordersOptional = Optional.of(order);
                }
            }
            return orderOptional;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }
*/

    @Override
    public Optional<Order> findOrderById(long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ORDER_BY_ID)) {

            statement.setLong(1, id);
            Optional<Order> orderOptional;
            try (ResultSet resultSet = statement.executeQuery()) {
                orderOptional = Optional.empty();
                if (resultSet.next()) {
                    Order order = createOrderFromResult(resultSet);
                    orderOptional = Optional.of(order);
                }
            }
            return orderOptional;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void updateOrder(Order order) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER)) {

            statement.setString(1, order.getEquipment());
            statement.setString(2, order.getDescription());
            statement.setLong(3, order.getId());
            statement.executeUpdate();
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteOrderById(long id) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ORDER_BY_ID)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void payOrder(Order order) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(PAY_ORDER)) {

            statement.setLong(1, order.getId());
            statement.executeUpdate();
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    private void createOrderWithMaxId(Order order) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_ORDER);
             PreparedStatement preStatement = connection.prepareStatement(SELECT_MAX_ID_FROM_ORDER)) {

            setPreparedStatement(order, statement);
            try (ResultSet resultSet = preStatement.executeQuery()) {
                if (resultSet.next()) {
                    order.setId(resultSet.getLong(1));
                }
            }
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    private void setPreparedStatement(Order order, PreparedStatement statement) throws SQLException {
        statement.setString(1, order.getTypeOfWorkout());
        statement.setInt(2, order.getNumberOfWorkout());
        statement.setLong(3, order.getIdTrainer());
        statement.setString(4, "Water");
        statement.setString(5, "For health");
        statement.setLong(6, order.getIdClient());
        statement.setByte(7, modifyIsPaidToByte(order.isPaid()));
        statement.executeUpdate();
    }

    //from db to entity
    private Order createOrderFromResult(ResultSet resultSet) throws SQLException {
        return new Order(resultSet.getLong(ID),
                resultSet.getString(TYPE_OF_WORKOUT),
                resultSet.getInt(NUMBER_OF_WORKOUT),
                resultSet.getLong(ID_TRAINER),
                resultSet.getLong(ID_CLIENT),
                resultSet.getBoolean(IS_PAID));
    }
}
