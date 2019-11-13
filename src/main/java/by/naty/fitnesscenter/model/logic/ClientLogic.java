package by.naty.fitnesscenter.model.logic;

import by.naty.fitnesscenter.model.dao.ClientDAO;
import by.naty.fitnesscenter.model.dao.impl.ClientDAOImpl;
import by.naty.fitnesscenter.model.entity.Client;
import by.naty.fitnesscenter.model.entity.User;
import by.naty.fitnesscenter.model.entity.Workout;
import by.naty.fitnesscenter.model.exception.DAOfcException;
import by.naty.fitnesscenter.model.exception.LogicFCException;
import by.naty.fitnesscenter.model.util.MD5;

import java.util.List;

public class ClientLogic {

    public void addClient(Client client) throws LogicFCException{
        ClientDAO clientDAO = new ClientDAOImpl();
        try {
            client.setPassword(MD5.encrypt(client.getPassword()));
            clientDAO.createClient(client);
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }

    public List<Client> findAllClients()  throws LogicFCException {
        ClientDAO clientDAO = new ClientDAOImpl();
        try {
            return clientDAO.findAllClients();
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }

    public Client findClientById(int id)  throws LogicFCException {
        ClientDAO clientDAO = new ClientDAOImpl();
        try {
            return clientDAO.findClientById(id).get();
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }

    public Client findClientByEmail(String email) throws LogicFCException {
        ClientDAO clientDAO = new ClientDAOImpl();
        try {
            return clientDAO.findClientByEmail(email).get();
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }

    public User updateClient(Client client) throws LogicFCException {
        ClientDAO clientDAO = new ClientDAOImpl();
        try {
            return clientDAO.updateClient(client);
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }

    public void deleteClientById(long id) throws LogicFCException{
        ClientDAO clientDAO = new ClientDAOImpl();
        try {
            clientDAO.deleteClientById(id);
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }


    public List<Workout> findAllWorkoutForClients(long idClient)  throws LogicFCException {
        ClientDAO clientDAO = new ClientDAOImpl();
        try {
            return clientDAO.findAllWorkoutByIdClient(idClient);
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }

    public List<Client> findAllClientsByIdTrainer(long idTrainer) throws LogicFCException {
        ClientDAO clientDAO = new ClientDAOImpl();
        try {
            return clientDAO.findAllClientsByIdTrainer(idTrainer);
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }
}
