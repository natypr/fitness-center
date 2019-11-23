package by.naty.fitnesscenter.model.dao;

import by.naty.fitnesscenter.model.entity.User;
import by.naty.fitnesscenter.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    void createUser(User user) throws DaoException;

    List<User> findAllUsers() throws DaoException;

    Optional<User> findUserById(long id) throws DaoException;

    User findUserByEmailAndPassword(String email, String password) throws DaoException;

    User updateUser(User user) throws DaoException;

    void deleteUserById(long id) throws DaoException;
}