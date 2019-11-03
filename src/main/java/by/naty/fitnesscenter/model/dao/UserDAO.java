package by.naty.fitnesscenter.model.dao;

import by.naty.fitnesscenter.model.entity.User;
import by.naty.fitnesscenter.model.exception.DAOfcException;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    void createUser(User user) throws DAOfcException;

    List<User> findAllUsers() throws DAOfcException;

    Optional<User> findUserById(long id) throws DAOfcException;

    User findUserBySurnameAndName(String surname, String name) throws DAOfcException;

    User updateUser(User user) throws DAOfcException;

    void deleteUserById(long id) throws DAOfcException;
}