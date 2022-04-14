package edu.goncharova.connection;

import java.sql.*;

public final class DatabaseConnector {

    private final String connectUrl;
    private final String connectUsername;
    private final String connectPassword;

    public DatabaseConnector(String connectUrl, String connectUsername, String connectPassword) {
        this.connectUrl = connectUrl;
        this.connectUsername = connectUsername;
        this.connectPassword = connectPassword;
    }

    public Connection openConnection() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try {
            return DriverManager.getConnection(connectUrl, connectUsername, connectPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
