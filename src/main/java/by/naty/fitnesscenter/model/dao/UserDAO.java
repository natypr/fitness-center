package by.naty.fitnesscenter.model.dao;

import by.naty.fitnesscenter.model.entity.User;
import by.naty.fitnesscenter.model.exception.DAOfcException;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    void create(User user) throws DAOfcException;

    List<User> findAllUsers() throws DAOfcException;

    Optional<User> findUserByEmail(String email) throws DAOfcException;

    User updateUserByUser(User user) throws DAOfcException;

    void deleteUser(long id) throws DAOfcException;
}