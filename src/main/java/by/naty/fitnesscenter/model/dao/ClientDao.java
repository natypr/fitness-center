package by.naty.fitnesscenter.model.dao;

import by.naty.fitnesscenter.model.entity.Client;
import by.naty.fitnesscenter.model.entity.Workout;
import by.naty.fitnesscenter.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface ClientDao {

    void createClient(Client client) throws DaoException;

    List<Client> findAllClients() throws DaoException;

    Optional<Client> findClientById(long id) throws DaoException;

    Optional<Client> findClientByEmail(String email) throws DaoException;

    Client updateClient(Client client) throws DaoException;

    void deleteClientById(long id) throws DaoException;


    List<Workout> findAllWorkoutByIdClient(long idClient) throws DaoException;

    List<Client> findAllClientsByIdTrainer(long idTrainer) throws DaoException;
}
