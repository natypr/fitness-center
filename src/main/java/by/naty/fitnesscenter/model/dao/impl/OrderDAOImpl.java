package by.naty.fitnesscenter.model.dao.impl;

import by.naty.fitnesscenter.model.dao.DAOConstant;
import by.naty.fitnesscenter.model.dao.OrderDAO;
import by.naty.fitnesscenter.model.entity.Order;
import by.naty.fitnesscenter.model.exception.DAOfcException;
import by.naty.fitnesscenter.model.exception.PoolFCException;
import by.naty.fitnesscenter.model.pool.ConnectionPool;
import by.naty.fitnesscenter.model.pool.ProxyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDAOImpl implements OrderDAO {

    private static final String CREATE_ORDER = "INSERT INTO order (id, id_client) VALUES (?, ?);";

    private static final String FIND_ALL_ORDERS = "SELECT id, id_client FROM order ORDER BY id;";

    private static final String FIND_ORDER_BY_ID = "SELECT id, id_client FROM order  WHERE id=?;";

    private static final String FIND_ORDER_BY_EMAIL =
            "SELECT `order`.`id`, `order`.`id_client` FROM `order` " +
                    "JOIN `client` ON `client`.`id`=`order`.`id_client` " +
                    "JOIN `user` ON `user`.`id`=`client`.`id` WHERE `user`.`email`=?;";

    private static final String UPDATE_ORDER = "UPDATE order SET id=?, id_client=? WHERE id=?;";

    private static final String DELETE_ORDER_BY_ID = "DELETE FROM order WHERE id=?;";


    @Override
    public void createOrder(Order order) throws DAOfcException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_ORDER)) {

            statement.setLong(1, order.getId());
            statement.setLong(2, order.getIdClient());
            statement.executeUpdate();
        } catch (SQLException | PoolFCException e) {
            throw new DAOfcException(e);
        }
    }

    @Override
    public List<Order> findAllOrders() throws DAOfcException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeQuery(FIND_ALL_ORDERS);
            ResultSet resultSet = statement.getResultSet();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(createOrderFromResult(resultSet));
            }
            return orders;
        } catch (SQLException | PoolFCException e) {
            throw new DAOfcException(e);
        }
    }

    private Order createOrderFromResult(ResultSet resultSet) throws SQLException {
        return new Order(resultSet.getLong(DAOConstant.ID),resultSet.getLong(DAOConstant.ID_CLIENT));
    }

    @Override
    public Optional<Order> findOrderById(long id) throws DAOfcException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ORDER_BY_ID)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<Order> orderOptional = Optional.empty();
            if (resultSet.next()) {
                Order order = createOrderFromResult(resultSet);
                orderOptional = Optional.of(order);
            }
            return orderOptional;
        } catch (SQLException | PoolFCException e) {
            throw new DAOfcException(e);
        }
    }

    @Override
    public Optional<Order> findOrderByEmailClient(String email) throws DAOfcException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ORDER_BY_EMAIL)) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            Optional<Order> orderOptional = Optional.empty();
            if (resultSet.next()) {
                Order order = createOrderFromResult(resultSet);
                orderOptional = Optional.of(order);
            }
            return orderOptional;
        } catch (SQLException | PoolFCException e) {
            throw new DAOfcException(e);
        }
    }

    @Override
    public Order updateOrder(Order order) throws DAOfcException {
        try(ProxyConnection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER)){

            statement.setLong(1, order.getId());
            statement.setLong(2, order.getIdClient());
            statement.executeUpdate();
            return order;
        } catch (SQLException | PoolFCException e) {
            throw new DAOfcException(e);
        }
    }

    @Override
    public void deleteOrderById(long id) throws DAOfcException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ORDER_BY_ID)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException | PoolFCException e) {
            throw new DAOfcException(e);
        }
    }
}
