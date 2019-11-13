package by.naty.fitnesscenter.model.logic;

import by.naty.fitnesscenter.model.dao.UserDAO;
import by.naty.fitnesscenter.model.dao.impl.UserDAOImpl;
import by.naty.fitnesscenter.model.entity.User;
import by.naty.fitnesscenter.model.exception.DAOfcException;
import by.naty.fitnesscenter.model.exception.LogicFCException;
import by.naty.fitnesscenter.model.util.MD5;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserLogic {
    private static final String EN = "en_US";
    private static final String RU = "ru_RU";
    private static final String REG_JSP = "/jsp.+";

    public void addUser(User user) throws LogicFCException {
        UserDAO userDAO = new UserDAOImpl();
        try {
            user.setPassword(MD5.encrypt(user.getPassword()));
            userDAO.createUser(user);
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }

    public User addUser(String role, String name, String surname, String email, String password) throws LogicFCException {
        UserDAO userDAO = new UserDAOImpl();
        try {
            String encryptedPassword = MD5.encrypt(password);
            User user = new User(0, role, name,  surname, email, encryptedPassword);
            userDAO.createUser(user);
            return user;
        } catch (DAOfcException exc) {
            throw new LogicFCException(exc);
        }
    }

    public List<User> findAllUsers()  throws LogicFCException {
        UserDAO userDAO = new UserDAOImpl();
        try {
            return userDAO.findAllUsers();
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }

    public User findUserById(int id)  throws LogicFCException {
        UserDAO userDAO = new UserDAOImpl();
        try {
            return userDAO.findUserById(id).get();
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }

    public User findUserByEmailAndPassword(String login, String password) throws LogicFCException {
        UserDAO userDAO = new UserDAOImpl();
        try {
            return userDAO.findUserByEmailAndPassword(login, MD5.encrypt(password));
        }
        catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }

    public User updateUser(User user) throws LogicFCException {
        UserDAO userDAO = new UserDAOImpl();
        try {
            return userDAO.updateUser(user);
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }

    public void deleteUser(long id) throws LogicFCException{
        UserDAO userDAO = new UserDAOImpl();
        try {
            userDAO.deleteUserById(id);
        } catch (DAOfcException e) {
            throw new LogicFCException(e);
        }
    }


    public String changeLanguage(String locale){
        if (locale == null){
            return EN;
        }
        return RU.equals(locale) ? RU : EN;
    }

    public String returnSamePage(String pagePath){
        String page = null;
        Pattern pattern = Pattern.compile(REG_JSP);
        Matcher matcher = pattern.matcher(pagePath);
        if(matcher.find()){
            page = matcher.group();
        }
        return page;
    }
}
