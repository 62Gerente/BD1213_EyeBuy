package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import main.LoginData;

public class ConnectionFactory {
    String driverClassName = "oracle.jdbc.driver.OracleDriver";

    LoginData loginAccounts = new LoginData();
	String url = loginAccounts.getURL();
    String username = loginAccounts.getUsername();
    String password = loginAccounts.getPassword();

    private static ConnectionFactory connectionFactory = null;

    public ConnectionFactory() {
            try {
                    Class.forName(driverClassName);
            } catch (ClassNotFoundException e) {
                    e.printStackTrace();
            }
    }

    public Connection getConnection() throws SQLException {
            Connection conn = null;
            conn = DriverManager.getConnection(url, username, password);
            return conn;
    }

    public static ConnectionFactory getInstance() {
            if (connectionFactory == null) {
                    connectionFactory = new ConnectionFactory();
            }
            return connectionFactory;
    }
}