package by.naty.fitnesscenter.model.dao;

import by.naty.fitnesscenter.model.entity.User;
import by.naty.fitnesscenter.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * The Interface UserDao.
 */
public interface UserDao {

    /**
     * Finds all users.
     *
     * @return the list of users
     * @throws DaoException the dao exception
     */
    List<User> findAllUsers() throws DaoException;

    /**
     * Finds user by id.
     *
     * @param id the id of user
     * @return the optional user
     * @throws DaoException the dao exception
     */
    Optional<User> findUserById(long id) throws DaoException;

    /**
     * Finds user by email and password.
     *
     * @param email the email of user
     * @param password the password of user
     * @return the user
     * @throws DaoException the dao exception
     */
    User findUserByEmailAndPassword(String email, String password) throws DaoException;

    /**
     * Updates user.
     *
     * @param user the user
     * @return the updated user
     * @throws DaoException the dao exception
     */
    User updateUser(User user) throws DaoException;

    /**
     * Deletes user by id.
     *
     * @param id the id of user
     * @throws DaoException the dao exception
     */
    void deleteUserById(long id) throws DaoException;

    /**
     * Blocks user by id.
     *
     * @param id the id of user
     * @throws DaoException the dao exception
     */
    void blockUserById(long id) throws DaoException;

    /**
     * Unblocks user by id.
     *
     * @param id the id of user
     * @throws DaoException the dao exception
     */
    void unblockUserById(long id) throws DaoException;
}