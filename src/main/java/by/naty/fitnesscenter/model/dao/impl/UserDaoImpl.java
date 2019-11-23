package by.naty.fitnesscenter.model.dao.impl;

import by.naty.fitnesscenter.model.dao.UserDao;
import by.naty.fitnesscenter.model.entity.User;
import by.naty.fitnesscenter.model.exception.DaoException;
import by.naty.fitnesscenter.model.exception.PoolException;
import by.naty.fitnesscenter.model.pool.ConnectionPool;
import by.naty.fitnesscenter.model.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.*;

public class UserDaoImpl implements UserDao {

    private static final String CREATE_USER =
            "INSERT INTO `user` (role, `name`, surname, email, password) " +
                    "VALUES (?, ?, ?, ?, ?);";

    private static final String SELECT_MAX_ID_FROM_USER = "SELECT max(id) FROM `user`;";

    private static final String FIND_ALL_USERS =
            "SELECT id, role, `name`, surname, email, password " +
                    "FROM `user` ORDER BY `user`.id;";

    private static final String FIND_USER_BY_ID =
            "SELECT id, role, `name`, surname, email, password " +
                    "FROM `user` WHERE `user`.id=?;";

    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD =
            "SELECT id, role, `name`, surname, email, password " +
                    "FROM `user` WHERE email=? AND password=?;";

    private static final String UPDATE_USER =
            "UPDATE `user` SET id=?, role=?, `name`=?, surname=?, email=?, password=? WHERE id=?;";

    private static final String DELETE_USER_BY_ID = "DELETE FROM `user` WHERE id=?;";

    private static String modifyRole(String string) {
        switch (string) {
            case "client":
                return String.valueOf(2);
            case "trainer":
                return String.valueOf(1);
        }
        return string;
    }

    public User createUserWithMaxId(User user) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_USER);
             PreparedStatement preStatement = connection.prepareStatement(SELECT_MAX_ID_FROM_USER)) {

            setPreparedStatement(user, statement);
            ResultSet resultSet = preStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
            return user;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    private void setPreparedStatement(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getRole());
        statement.setString(2, user.getName());
        statement.setString(3, user.getSurname());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getPassword());
//        statement.setTimestamp(6, new Timestamp(0));
        //       statement.setTimestamp(6, user.getDate());
        statement.executeUpdate();
    }

    @Override
    public void createUser(User user) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_USER)) {

            setPreparedStatement(user, statement);
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> findAllUsers() throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeQuery(FIND_ALL_USERS);
            ResultSet resultSet = statement.getResultSet();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(createUserFromResult(resultSet));
            }
            return users;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    private User createUserFromResult(ResultSet resultSet) throws SQLException {
        User user = new User(resultSet.getLong(ID), resultSet.getString(ROLE),
                resultSet.getString(NAME), resultSet.getString(SURNAME),
                resultSet.getString(EMAIL), resultSet.getString(PASSWORD));
        return user;
    }

    @Override
    public Optional<User> findUserById(long id) throws DaoException {
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
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL_AND_PASSWORD)) {

            User user = null;
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                setUserFromResultSet(resultSet, user);
            }
            return user;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    private void setUserFromResultSet(ResultSet resultSet, User user) throws SQLException {
        user.setId(resultSet.getLong(ID));
        user.setRole(resultSet.getString(ROLE));
        user.setName(resultSet.getString(NAME));
        user.setSurname(resultSet.getString(SURNAME));
        user.setEmail(resultSet.getString(EMAIL));
        user.setPassword(resultSet.getString(PASSWORD));
    }

    @Override
    public User updateUser(User user) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {

            statement.setLong(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, modifyRole(user.getRole()));
            statement.setString(4, user.getSurname());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPassword());
            statement.executeUpdate();
            return user;
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteUserById(long id) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | PoolException e) {
            throw new DaoException(e);
        }
    }
}
