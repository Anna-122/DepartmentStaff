package edu.goncharova.connection;

import java.sql.Connection;

public final class MySqlConnection implements AutoCloseable {
    private static Connection connection;
    private static final DatabaseConnector databaseConnector = new DatabaseConnector(
            "jdbc:mysql://localhost:3306/DepartmentStaff",
            "root",
            "root");

    public static Connection connection() {
        try {
            connection = databaseConnector.openConnection();
            return connection;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }
}
