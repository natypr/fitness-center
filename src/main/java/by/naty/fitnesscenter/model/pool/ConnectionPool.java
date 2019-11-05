package by.naty.fitnesscenter.model.pool;

import by.naty.fitnesscenter.model.exception.PoolFCException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final Logger LOG = LogManager.getLogger();

    private static ConnectionPool instance;
    private BlockingQueue<ProxyConnection> pool;

    private ConnectionPool(PoolConfig config) {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            int poolSize = config.getPoolSize();
            pool = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                ProxyConnection connection = new ProxyConnection(createConnection(config));
                pool.put(connection);
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection createConnection(PoolConfig config) throws SQLException {
        return DriverManager.getConnection(config.getUrl(), config.getUserName(),  config.getPassword());
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            PropertyLoader propertyLoader = new PropertyLoader();
            Properties properties = propertyLoader.loadFile("database.properties");
//            PoolConfig config = new PoolConfig();     //FIXME get size from properties
//            instance = new ConnectionPool(config.getPoolSize());
        }
        return instance;
    }

    public ProxyConnection getConnection() throws PoolFCException {
        ProxyConnection connection;
        try {
            connection = pool.take();
        } catch ( InterruptedException e) {
            LOG.error("Connection not received: " + e);
            throw new PoolFCException(e);
        }
        return connection;
    }

    public void releaseConnection(ProxyConnection connection) throws PoolFCException {
        try {
            pool.put(connection);
        } catch (InterruptedException e) {
            LOG.error("Connection not released: " + e);
            throw new PoolFCException(e);
        }
    }

    public void closeConnection(ProxyConnection connection) throws PoolFCException {
        pool.offer(connection);
        try {
            connection.reallyClose();
        }catch (SQLException e){
            LOG.error("Connection not closed: " + e);
            throw new PoolFCException(e);
        }
    }

    public void closeAllConnections() throws PoolFCException {
        try {
            for (Connection connection : pool) {
                ProxyConnection proxyConnection = (ProxyConnection) connection;
                proxyConnection.reallyClose();
            }
            //pool = new ArrayBlockingQueue<>(poolSize); //FIXME
        } catch (SQLException e) {
            LOG.error("All connections are not closed: " + e);
            throw new PoolFCException(e);
        }
    }
}

