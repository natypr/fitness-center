package by.naty.fitnesscenter.model.dao;

import by.naty.fitnesscenter.model.entity.Order;
import by.naty.fitnesscenter.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * The Interface OrderDao.
 */
public interface OrderDao {

    /**
     * Creates the order.
     *
     * @param order the order
     * @throws DaoException the dao exception
     */
    void createOrder(Order order) throws DaoException;

    /**
     * Finds all orders. (deprecated)
     *
     * @return the list of orders
     * @throws DaoException the dao exception
     */
    List<Order> findAllOrders() throws DaoException;

    /**
     * Finds all orders by id trainer. (deprecated)
     *
     * @param id the id of trainer
     * @return the list of orders by id trainer
     * @throws DaoException the dao exception
     */
    List<Order> findAllOrdersByIdTrainer(long id) throws DaoException;

    /**
     * Finds order by id.
     *
     * @param id the id of order
     * @return the optional order
     * @throws DaoException the dao exception
     */
    Optional<Order> findOrderById(long id) throws DaoException;

    /**
     * Updates order.
     *
     * @param order the order
     * @throws DaoException the dao exception
     */
    void updateOrder(Order order) throws DaoException;

    /**
     * Deletes order by id.
     *
     * @param id the id of order
     * @throws DaoException the dao exception
     */
    void deleteOrderById(long id) throws DaoException;

    /**
     * Payment for order.
     *
     * @param order the order
     * @throws DaoException the dao exception
     */
    void payOrder(Order order) throws DaoException;
}