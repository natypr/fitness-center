package by.naty.fitnesscenter.model.logic;

import by.naty.fitnesscenter.model.dao.ClientDao;
import by.naty.fitnesscenter.model.dao.impl.ClientDaoImpl;
import by.naty.fitnesscenter.model.entity.Client;
import by.naty.fitnesscenter.model.entity.Order;
import by.naty.fitnesscenter.model.exception.DaoException;
import by.naty.fitnesscenter.model.exception.LogicException;
import by.naty.fitnesscenter.model.util.MD5;

import java.util.List;

public class ClientLogic {

    public void createClient(Client client) throws LogicException {
        ClientDao clientDAO = new ClientDaoImpl();
        try {
            client.setPassword(MD5.encrypt(client.getPassword()));
            clientDAO.createClient(client);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public List<Client> findAllClients() throws LogicException {
        ClientDao clientDAO = new ClientDaoImpl();
        try {
            return clientDAO.findAllClients();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public Client findClientById(long id) throws LogicException {
        ClientDao clientDAO = new ClientDaoImpl();
        try {
            return clientDAO.findClientById(id).get();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public Client findClientByEmail(String email) throws LogicException {
        ClientDao clientDAO = new ClientDaoImpl();
        try {
            return clientDAO.findClientByEmail(email).get();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public void updateClient(Client client) throws LogicException {
        ClientDao clientDAO = new ClientDaoImpl();
        try {
            clientDAO.updateClient(client);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public void deleteClientById(long id) throws LogicException {
        ClientDao clientDAO = new ClientDaoImpl();
        try {
            clientDAO.deleteClientById(id);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }


    public List<Order> findAllOrderByIdClients(long idClient) throws LogicException {
        ClientDao clientDAO = new ClientDaoImpl();
        try {
            return clientDAO.findAllOrderByIdClient(idClient);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public List<Client> findAllClientsByIdTrainer(long idTrainer) throws LogicException {
        ClientDao clientDAO = new ClientDaoImpl();
        try {
            return clientDAO.findAllClientsByIdTrainer(idTrainer);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }
}
