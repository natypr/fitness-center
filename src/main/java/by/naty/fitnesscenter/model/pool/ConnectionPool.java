package by.naty.fitnesscenter.model.pool;

import by.naty.fitnesscenter.model.exception.PoolFCException;
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
    private final int POOL_SIZE = Integer.parseInt(ConnectionCreator.poolSize);
    private BlockingQueue<ProxyConnection> pool;

    private ConnectionPool() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            LOG.error("Driver not registered: " + e);
        }
        try {
            pool = new ArrayBlockingQueue<>(POOL_SIZE);
            for (int i = 0; i < POOL_SIZE; i++) {
                ProxyConnection connection = new ProxyConnection(ConnectionCreator.createConnection());
                pool.put(connection);
            }
        } catch (SQLException | InterruptedException e) {
            LOG.fatal("No connections added: " + e);
            throw new RuntimeException();
        }
    }

    public static ConnectionPool getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new ConnectionPool();
            }
        } finally {
            lock.unlock();
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
            pool = new ArrayBlockingQueue<>(POOL_SIZE);
        } catch (SQLException e) {
            LOG.error("All connections are not closed: " + e);
            throw new PoolFCException(e);
        }
    }
}

