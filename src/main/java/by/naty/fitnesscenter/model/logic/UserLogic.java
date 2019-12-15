package by.naty.fitnesscenter.model.logic;

import by.naty.fitnesscenter.model.dao.UserDao;
import by.naty.fitnesscenter.model.dao.impl.UserDaoImpl;
import by.naty.fitnesscenter.model.entity.User;
import by.naty.fitnesscenter.model.exception.DaoException;
import by.naty.fitnesscenter.model.exception.LogicException;
import by.naty.fitnesscenter.model.util.MD5;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserLogic {
    private final Pattern pattern = Pattern.compile("/jsp.+");

    public List<User> findAllUsers() throws LogicException {
        UserDao userDAO = new UserDaoImpl();
        try {
            return userDAO.findAllUsers();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public User findUserById(long id) throws LogicException {
        UserDao userDAO = new UserDaoImpl();
        try {
            return userDAO.findUserById(id).get();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public User findUserByEmailAndPassword(String login, String password) throws LogicException {
        UserDao userDAO = new UserDaoImpl();
        try {
            return userDAO.findUserByEmailAndPassword(login, MD5.encrypt(password));
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public User updateUser(User user) throws LogicException {
        UserDao userDAO = new UserDaoImpl();
        try {
            return userDAO.updateUser(user);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public void deleteUser(long id) throws LogicException {
        UserDao userDAO = new UserDaoImpl();
        try {
            userDAO.deleteUserById(id);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public void blockUserById(long id) throws LogicException {
        UserDao userDAO = new UserDaoImpl();
        try {
            userDAO.blockUserById(id);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public void unblockUserById(long id) throws LogicException {
        UserDao userDAO = new UserDaoImpl();
        try {
            userDAO.unblockUserById(id);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public String returnSamePage(String pagePath) {
        String page = null;
        Matcher matcher = pattern.matcher(pagePath);
        if (matcher.find()) {
            page = matcher.group();
        }
        return page;
    }
}
