package by.naty.fitnesscenter.model.pool;

import java.util.Properties;

public class PoolConfig {

    private final String driverName;
    private final String url;
    private final String userName;
    private final String password;
    private final int poolSize;

    public PoolConfig() {
        PropertyLoader propertyLoader = new PropertyLoader();
        Properties properties = propertyLoader.loadFile("config/database.properties");

        this.driverName = properties.getProperty("db.driverClassName");
        this.url = properties.getProperty("db.url");
        this.userName = properties.getProperty("db.userName");
        this.password = properties.getProperty("db.password");
        this.poolSize = Integer.parseInt(properties.getProperty("db.poolSize"));
    }

    String getDriverName() {
        return driverName;
    }

    String getUrl() {
        return url;
    }

    String getUserName() {
        return userName;
    }

    String getPassword() {
        return password;
    }

    public int getPoolSize() {
        return poolSize;
    }
}
