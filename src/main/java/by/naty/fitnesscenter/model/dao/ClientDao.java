package by.naty.fitnesscenter.model.dao;

import by.naty.fitnesscenter.model.entity.Client;
import by.naty.fitnesscenter.model.entity.Order;
import by.naty.fitnesscenter.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * The Interface ClientDao.
 */
public interface ClientDao {

    /**
     * Creates the client.
     *
     * @param client the client
     * @throws DaoException the dao exception
     */
    void createClient(Client client) throws DaoException;

    /**
     * Finds all clients.
     *
     * @return the list of clients
     * @throws DaoException the dao exception
     */
    List<Client> findAllClients() throws DaoException;

    /**
     * Finds client by id.
     *
     * @param id the id of client
     * @return the optional client
     * @throws DaoException the dao exception
     */
    Optional<Client> findClientById(long id) throws DaoException;

    /**
     * Finds client by email.
     *
     * @param email the email of client
     * @return the optional client
     * @throws DaoException the dao exception
     */
    Optional<Client> findClientByEmail(String email) throws DaoException;

    /**
     * Updates client.
     *
     * @param client the client
     * @throws DaoException the dao exception
     */
    void updateClient(Client client) throws DaoException;

    /**
     * Deletes client by id.
     *
     * @param id the id of client
     * @throws DaoException the dao exception
     */
    void deleteClientById(long id) throws DaoException;


    /**
     * Finds orders by id client.
     *
     * @param idClient the id client
     * @return the list of orders
     * @throws DaoException the dao exception
     */
    List<Order> findAllOrderByIdClient(long idClient) throws DaoException;

    /**
     * Finds all unpaid orders by id client.
     *
     * @param idClient the id client
     * @return the list of unpaid orders
     * @throws DaoException the dao exception
     */
    List<Order> findAllUnpaidOrderByIdClient(long idClient) throws DaoException;

    /**
     * Finds all clients by id trainer.
     *
     * @param idTrainer the id trainer
     * @return the list of clients
     * @throws DaoException the dao exception
     */
    List<Client> findAllClientsByIdTrainer(long idTrainer) throws DaoException;

    /**
     * Update discount for client.
     *
     * @param idClient the id client
     * @param discount the discount
     * @throws DaoException the dao exception
     */
    void updateDiscount(long idClient, double discount) throws DaoException;
}
