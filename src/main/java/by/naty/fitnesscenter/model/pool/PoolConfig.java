package by.naty.fitnesscenter.model.pool;

public class PoolConfig {

    private final String driverName;
    private final String url;
    private final String userName;
    private final String password;
    private final int poolSize;

    public PoolConfig(String driverName, String url, String userName, String password, int poolSize) {
        this.driverName = driverName;
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.poolSize = poolSize;
    }

    public String getDriverName(){
        return driverName;
    }

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getPoolSize() {
        return poolSize;
    }
}
