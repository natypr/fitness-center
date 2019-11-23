package by.naty.fitnesscenter.model.logic;

import by.naty.fitnesscenter.model.dao.OrderDao;
import by.naty.fitnesscenter.model.dao.impl.OrderDaoImpl;
import by.naty.fitnesscenter.model.entity.Order;
import by.naty.fitnesscenter.model.exception.DaoException;
import by.naty.fitnesscenter.model.exception.LogicException;

import java.util.List;

public class OrderLogic {

    public void createOrder(Order order) throws LogicException {
        OrderDao orderDAO = new OrderDaoImpl();
        try {
            orderDAO.createOrder(order);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public List<Order> findAllOrders(String email) throws LogicException {
        OrderDao orderDAO = new OrderDaoImpl();
        try {
            return orderDAO.findAllOrders();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public Order findOrderById(Long id) throws LogicException {
        OrderDao orderDAO = new OrderDaoImpl();
        try {
            return orderDAO.findOrderById(id).get();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public Order findOrderByEmailClient(String email) throws LogicException {
        OrderDao orderDAO = new OrderDaoImpl();
        try {
            return orderDAO.findOrderByEmailClient(email).get();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public Order updateOrder(Order order) throws LogicException {
        OrderDao orderDAO = new OrderDaoImpl();
        try {
            return orderDAO.updateOrder(order);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public void deleteOrderById(Order order) throws LogicException {
        OrderDao orderDAO = new OrderDaoImpl();
        try {
            orderDAO.deleteOrderById(order.getId());
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }
}
