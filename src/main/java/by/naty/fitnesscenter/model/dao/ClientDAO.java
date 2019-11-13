package by.naty.fitnesscenter.model.dao;

import by.naty.fitnesscenter.model.entity.Client;
import by.naty.fitnesscenter.model.entity.Workout;
import by.naty.fitnesscenter.model.exception.DAOfcException;

import java.util.List;
import java.util.Optional;

public interface ClientDAO {

    void createClient(Client client) throws DAOfcException;

    List<Client> findAllClients() throws DAOfcException;

    Optional<Client> findClientById(long id) throws DAOfcException;

    Optional<Client> findClientByEmail(String email) throws DAOfcException;

    Client updateClient(Client client) throws DAOfcException;

    void deleteClientById(long id) throws DAOfcException;


    List<Workout> findAllWorkoutByIdClient(long idClient) throws DAOfcException;

    List<Client> findAllClientsByIdTrainer(long idTrainer) throws DAOfcException;
}
