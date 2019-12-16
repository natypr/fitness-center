package by.naty.fitnesscenter.model.logic;

import by.naty.fitnesscenter.model.dao.OrderDao;
import by.naty.fitnesscenter.model.dao.TrainerDao;
import by.naty.fitnesscenter.model.dao.impl.OrderDaoImpl;
import by.naty.fitnesscenter.model.dao.impl.TrainerDaoImpl;
import by.naty.fitnesscenter.model.entity.Order;
import by.naty.fitnesscenter.model.entity.Trainer;
import by.naty.fitnesscenter.model.exception.DaoException;
import by.naty.fitnesscenter.model.exception.LogicException;

import java.util.List;
import java.util.Optional;

public class OrderLogic {

    public void createOrder(Order order) throws LogicException {
        OrderDao orderDAO = new OrderDaoImpl();
        try {
            orderDAO.createOrder(order);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public List<Order> findAllOrders() throws LogicException {
        OrderDao orderDAO = new OrderDaoImpl();
        try {
            return orderDAO.findAllOrders();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public List<Order> findAllOrdersByIdTrainer(long id) throws LogicException {
        OrderDao orderDAO = new OrderDaoImpl();
        try {
            return orderDAO.findAllOrdersByIdTrainer(id);
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

    public void updateOrder(Order order) throws LogicException {
        OrderDao orderDAO = new OrderDaoImpl();
        try {
            orderDAO.updateOrder(order);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public void deleteOrderById(long id) throws LogicException {
        OrderDao orderDAO = new OrderDaoImpl();
        try {
            orderDAO.deleteOrderById(id);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public void payOrder(Order order) throws LogicException {
        OrderDao orderDAO = new OrderDaoImpl();
        try {
            orderDAO.payOrder(order);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public double calculatePriceByIdOrder(long idOrder) throws LogicException {
        TrainerDao trainerDAO = new TrainerDaoImpl();
        try {
            Order order = findOrderById(idOrder);
            int numberOfWorkout = order.getNumberOfWorkout();

            long idTrainer = order.getIdTrainer();
            Optional<Trainer> trainer = trainerDAO.findTrainerById(idTrainer);
//            double costPerOneWorkout = trainer.getCostPerOneWorkout();  //TODO Optional<Trainer>

            return numberOfWorkout * 25;//*costPerOneWorkout);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }
}
