package by.naty.fitnesscenter.model.logic;

import by.naty.fitnesscenter.model.dao.OrderDAO;
import by.naty.fitnesscenter.model.dao.impl.OrderDAOImpl;
import by.naty.fitnesscenter.model.entity.Order;
import by.naty.fitnesscenter.model.exception.DAOfcException;
import by.naty.fitnesscenter.model.exception.LogicFCException;

import java.util.List;
import java.util.Locale;

public class OrderLogic {

    public void createOrder(Order order) throws LogicFCException {
        OrderDAO orderDAO = new OrderDAOImpl();
        try {
            orderDAO.createOrder(order);
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }

    public List<Order> findAllOrders(String email) throws LogicFCException {
        OrderDAO orderDAO = new OrderDAOImpl();
        try {
            return orderDAO.findAllOrders();
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }

    public Order findOrderById(Long id) throws LogicFCException {
        OrderDAO orderDAO = new OrderDAOImpl();
        try {
            return orderDAO.findOrderById(id).get();
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }

    public Order findOrderByEmailClient(String email) throws LogicFCException {
        OrderDAO orderDAO = new OrderDAOImpl();
        try {
            return orderDAO.findOrderByEmailClient(email).get();
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }

    public Order updateOrder(Order order) throws LogicFCException {
        OrderDAO orderDAO = new OrderDAOImpl();
        try {
            return orderDAO.updateOrder(order);
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }

    public void deleteOrderById(Order order) throws LogicFCException {
        OrderDAO orderDAO = new OrderDAOImpl();
        try {
            orderDAO.deleteOrderById(order.getId());
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }
}
