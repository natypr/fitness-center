package by.naty.fitnesscenter.model.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreator {

    private static Properties properties;
    public final static String URL = properties.getProperty("db.url") ;
    public final static String USER_NAME = properties.getProperty("db.userName") ;
    public final static String PASSWORD = properties.getProperty("db.password") ;
    public final static String POOL_SIZE = properties.getProperty("db.poolSize") ;
    public static String poolSize = "35";

    static {
        PropertyLoader propertyLoader = new PropertyLoader();
         properties = propertyLoader.loadFile("database.properties");
    }

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER_NAME,  PASSWORD);
    }

    public static void main(String[] args) throws SQLException {
        ConnectionCreator.createConnection();
    }
}