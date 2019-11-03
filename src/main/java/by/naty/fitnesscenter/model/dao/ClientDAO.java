package by.naty.fitnesscenter.model.dao;

import by.naty.fitnesscenter.model.entity.Client;
import by.naty.fitnesscenter.model.entity.Workout;
import by.naty.fitnesscenter.model.exception.DAOfcException;

import java.util.List;
import java.util.Optional;

public interface ClientDAO {

    void create(Client client) throws DAOfcException;

    List<Client> findAllClients() throws DAOfcException;

    Optional<Client> findClientById(long id) throws DAOfcException;

    Client updateClientByClient(Client client) throws DAOfcException;

    void deleteClient(long id) throws DAOfcException;


    List<Workout> findAllWorkoutForClientById(long idClient) throws DAOfcException;

    List<Client> findAllClientsOfThisTrainer(int idTrainer) throws DAOfcException;
}
