package by.naty.fitnesscenter.model.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreator {

    private static String url = "jdbc:mysql://localhost:3306/fitnesscenter";
    private static String userName = "root";
    private static String password = "pass";
    public static String poolSize = "35";

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }
}