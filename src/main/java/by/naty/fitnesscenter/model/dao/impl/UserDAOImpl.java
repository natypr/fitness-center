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

    private static final String CREATE_USER = "INSERT INTO `user` " +
            "(`role`, `name`, `surname`, `email`, `password`)" +
            " VALUES (?, ?, ?, ?, ?);";

    private static final String FIND_ALL_USERS = "SELECT `id_user`, `role_name` AS `role`, " +
            "`name`, `surname`, `email`, `password`,  " +
            "FROM `user` LEFT JOIN `role_legend` " +
            "ON `user`.`role` = `role_legend`.`role` ORDER BY `user`.`iduser`;";

    private static final String FIND_USER_BY_ID = "SELECT `user`.`id_user`, `role_name` AS `role`, " +
            "`user`.`name`, `user`.`surname`, `user`.`email`, `user`.`password`,  " +
            "FROM `user` LEFT JOIN `role_legend` " +
            "ON `user`.`role` = `role_legend`.`role` WHERE `user`.`id_user`=?;";

    private static final String FIND_USER_BY_SURNAME_AND_NAME = "SELECT `id_user`, `role_name` AS `role`, " +
            "`name`, `surname`, `email`, `password` " +
            "FROM `user` LEFT JOIN `role_legend` " +
            "ON `user`.`role` = `role_legend`.`role` WHERE `surname`=? AND `name`=?;";

    private static final String UPDATE_USER = "UPDATE `user` SET `id_user`=?, `role`=?, " +
            "`name`=?, `surname`=?, `email`=?, `password`=?, WHERE `id_user`=?;";

    private static final String DELETE_USER_BY_ID = "DELETE FROM `user` WHERE `id_user`=?;";


    @Override
    public void createUser(User user) throws DAOfcException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_USER)) {

            statement.setString(1, user.getRole());
            statement.setString(2, user.getName());
            statement.setString(3, user.getSurname());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
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
                resultSet.getString(DAOConstant.EMAIL), resultSet.getString(DAOConstant.PASSWORD));
        return user;
    }

    @Override
    public Optional<User> findUserById(long id) throws DAOfcException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<User> userOptionalById = Optional.empty();
            if (resultSet.next()) {
                User user = createUserFromResult(resultSet);
                userOptionalById = Optional.of(user);
            }
            return userOptionalById;
        } catch (SQLException | PoolFCException e) {
            throw new DAOfcException(e);
        }
    }

    @Override
    public User findUserBySurnameAndName(String surname, String name) throws DAOfcException {
        try(ProxyConnection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_SURNAME_AND_NAME)){

            User user = null;
            statement.setString(1, surname);
            statement.setString(2, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                user = new User();
                setUserFromResultSet(resultSet, user);
            }
            return user;
        } catch (SQLException | PoolFCException e) {
            throw new DAOfcException(e);
        }
    }

    void setUserFromResultSet(ResultSet resultSet, User user) throws SQLException {
        user.setIdUser(resultSet.getLong("id_user"));
        user.setRole(resultSet.getString("role"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
    }

    @Override
    public User updateUser(User user) throws DAOfcException {
        try(ProxyConnection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER)){

            statement.setLong(1, user.getIdUser());
            statement.setString(2, user.getName());
            statement.setString(3, modifyRole(user.getRole()));
            statement.setString(4,user.getSurname());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPassword());
            statement.executeUpdate();
            return user;
        } catch (SQLException | PoolFCException e) {
            throw new DAOfcException(e);
        }
    }

    private static String modifyRole(String string){
        switch (string){
            case "client":
                return String.valueOf(2);
            case "trainer":
                return String.valueOf(1);
        }
        return string;
    }

    @Override
    public void deleteUserById(long id) throws DAOfcException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | PoolFCException e) {
            throw new DAOfcException(e);
        }
    }
}
