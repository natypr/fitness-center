package by.naty.fitnesscenter.model.dao;

import by.naty.fitnesscenter.model.entity.Order;
import by.naty.fitnesscenter.model.exception.DAOfcException;

import java.util.List;
import java.util.Optional;

public interface OrderDAO {

    void createOrder(Order order) throws DAOfcException;

    List<Order> findAllOrders() throws DAOfcException;

    Optional<Order> findOrderById(long id) throws DAOfcException;

    Optional<Order>  findOrderByEmailClient(String email) throws DAOfcException;

    Order updateOrder(Order order) throws DAOfcException;

    void deleteOrderById(long id) throws DAOfcException;
}