package by.naty.fitnesscenter.model.dao.impl;


import by.naty.fitnesscenter.model.dao.DAOConstant;
import by.naty.fitnesscenter.model.dao.UserDAO;
import by.naty.fitnesscenter.model.entity.User;
import by.naty.fitnesscenter.model.exception.DAOfcException;
import by.naty.fitnesscenter.model.exception.PoolFCException;
import by.naty.fitnesscenter.model.pool.ConnectionPool;
import by.naty.fitnesscenter.model.pool.ProxyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    private static final String CREATE_USER_WITH_ID = "INSERT INTO `user` " +
            "(`role`, `name`, `surname`, `sex`, `years_old`, `email`, `password`)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?);";

    private static final String FIND_ALL_USERS = "SELECT `iduser`, `name`, `surname`, " +
            "`years_old`, `sex`, `email`, `password`, `role_name` AS `role` " +
            "FROM `user` LEFT JOIN `role` ON `user`.`role` = `role` ORDER BY `user`.`iduser`;";

    private static final String DELETE_USER_BY_ID = "DELETE FROM `user` WHERE `iduser`;";   //FIXME =id

    @Override
    public void create(User user) throws DAOfcException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_USER_WITH_ID)) {

            statement.setString(1, user.getRole());
            statement.setString(2, user.getName());
            statement.setString(3, user.getSurname());
            statement.setString(4, user.getSex());
            statement.setInt(5, user.getYearOld());
            statement.setString(6, user.getEmail());
            statement.setString(7, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException | PoolFCException e) {
            throw new DAOfcException(e);
        }
    }

    @Override
    public List<User> findAllUsers() throws DAOfcException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeQuery(FIND_ALL_USERS);
            ResultSet resultSet = statement.getResultSet();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(createUserFromResult(resultSet));
            }
            return users;
        } catch (SQLException | PoolFCException e) {
            throw new DAOfcException(e);
        }
    }

    private User createUserFromResult(ResultSet resultSet) throws SQLException {
        User user = new User(resultSet.getLong(DAOConstant.ID_USER), resultSet.getString(DAOConstant.ROLE),
                resultSet.getString(DAOConstant.NAME), resultSet.getString(DAOConstant.SURNAME),
                resultSet.getString(DAOConstant.SEX), resultSet.getInt(DAOConstant.YEARS_OLD),
                resultSet.getString(DAOConstant.EMAIL), resultSet.getString(DAOConstant.PASSWORD));
        return user;
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DAOfcException {
        return Optional.empty();
    }

    @Override
    public User updateUserByUser(User user) throws DAOfcException {
        return null;
    }

    @Override
    public void deleteUser(long id) throws DAOfcException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID)) {

            //...
            preparedStatement.executeUpdate();
        } catch (SQLException | PoolFCException e) {
            throw new DAOfcException(e);
        }
    }
}
