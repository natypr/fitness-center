package by.naty.fitnesscenter.model.service;

import by.naty.fitnesscenter.model.dao.ClientDao;
import by.naty.fitnesscenter.model.dao.OrderDao;
import by.naty.fitnesscenter.model.dao.impl.ClientDaoImpl;
import by.naty.fitnesscenter.model.dao.impl.OrderDaoImpl;
import by.naty.fitnesscenter.model.entity.Client;
import by.naty.fitnesscenter.model.entity.Order;
import by.naty.fitnesscenter.model.exception.DaoException;
import by.naty.fitnesscenter.model.exception.LogicException;
import by.naty.fitnesscenter.model.util.MD5;

import java.util.List;
import java.util.Optional;

public class ClientService {

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

    public List<Order> findAllUnpaidOrderByIdClients(long idClient) throws LogicException {
        ClientDao clientDAO = new ClientDaoImpl();
        try {
            return clientDAO.findAllUnpaidOrderByIdClient(idClient);
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

    public double calculateNewDiscountByIdClient(long idClient, long idOrder) throws LogicException {
        double maxDiscount = 40.0;

        ClientDao clientDAO = new ClientDaoImpl();
        try {
            Client client = clientDAO.findClientById(idClient).get();
            double discount = client.getDiscount();

            double discountOfThisOrder = findDiscountOfThisOrder(idOrder);
            double newDiscount = discount + discountOfThisOrder;

            return Math.min(newDiscount, maxDiscount);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    private int findDiscountOfThisOrder(long idOrder) throws LogicException {
        OrderDao orderDAO = new OrderDaoImpl();
        try {
            Optional<Order> order = orderDAO.findOrderById(idOrder);
            int numberOfWorkout = order.map(Order::getNumberOfWorkout).orElse(1);
            return numberOfWorkout / 10;
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }
}
