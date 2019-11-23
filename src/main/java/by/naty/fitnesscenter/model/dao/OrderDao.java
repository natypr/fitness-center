package by.naty.fitnesscenter.model.dao;

import by.naty.fitnesscenter.model.entity.Order;
import by.naty.fitnesscenter.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

    void createOrder(Order order) throws DaoException;

    List<Order> findAllOrders() throws DaoException;

    Optional<Order> findOrderById(long id) throws DaoException;

    Optional<Order> findOrderByEmailClient(String email) throws DaoException;

    Order updateOrder(Order order) throws DaoException;

    void deleteOrderById(long id) throws DaoException;
}