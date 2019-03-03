package converter.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private String driver;
    private String hostname;
    private int port;
    private String user;
    private String password;
    private String database;

    public DatabaseConnection(String driver, String hostname, int port, String user, String password, String database) {
        this.driver = driver;
        this.hostname = hostname;
        this.port = port;
        this.user = user;
        this.password = password;
        this.database = database;
    }

    public Connection connect() throws Exception {
        if(this.driver == null || this.hostname == null || this.user == null || this.password == null || this.database == null || this.driver.equals("") || this.hostname.equals("") || this.user.equals("") || this.password.equals("") || this.database.equals("") ) {
            throw new Exception("Database fields cannot be empty!");
        }

        String url = "jdbc:" + this.driver + "://" + this.hostname + ":" + this.port + "/" + this.database;
        Connection connection = DriverManager.getConnection(url, this.user, this.password);

        return connection;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }


}
