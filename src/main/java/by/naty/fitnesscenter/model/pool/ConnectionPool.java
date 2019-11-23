package by.naty.fitnesscenter.model.pool;

import by.naty.fitnesscenter.model.exception.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger LOG = LogManager.getLogger();

    private static ConnectionPool instance;
    private static ReentrantLock lock = new ReentrantLock();
    private BlockingQueue<ProxyConnection> pool;

    private ConnectionPool(PoolConfig config) {
        try {
            Class.forName(config.getDriverName());
        } catch (ClassNotFoundException e) {
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

    public static ConnectionPool getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                PoolConfig config = new PoolConfig();
                instance = new ConnectionPool(config);
            }
        } finally {
            lock.unlock();
        }
        return instance;
    }

    private Connection createConnection(PoolConfig config) throws SQLException {
        return DriverManager.getConnection(config.getUrl(), config.getUserName(), config.getPassword());
    }

    public ProxyConnection getConnection() throws PoolException {
        ProxyConnection connection;
        try {
            connection = pool.take();
        } catch (InterruptedException e) {
            LOG.error("Connection not received: ", e);
            throw new PoolException(e);
        }
        return connection;
    }

    void releaseConnection(ProxyConnection connection) throws PoolException {
        try {
            pool.put(connection);
        } catch (InterruptedException e) {
            LOG.error("Connection not released: ", e);
            throw new PoolException(e);
        }
    }

    public void closeConnection(ProxyConnection connection) throws PoolException {
        pool.offer(connection);
        try {
            connection.reallyClose();
        } catch (SQLException e) {
            LOG.error("Connection not closed: ", e);
            throw new PoolException(e);
        }
    }

    public void closeAllConnections() throws PoolException {
        try {
            for (Connection connection : pool) {
                ProxyConnection proxyConnection = (ProxyConnection) connection;
                proxyConnection.reallyClose();
            }
            PoolConfig config = new PoolConfig();
            pool = new ArrayBlockingQueue<>(config.getPoolSize());
        } catch (SQLException e) {
            LOG.error("All connections are not closed: ", e);
            throw new PoolException(e);
        }
    }
}

