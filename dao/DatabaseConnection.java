package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;
    private static final String url = "jdbc:mysql://localhost:3306/librarydb";
    private static final String username = "root";
    private static final String password = "password";

    private DatabaseConnection() {}

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                System.out.println("Error connecting to MySQL database");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
