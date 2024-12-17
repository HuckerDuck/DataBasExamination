package se.fredrik.databashantering.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2ConnectionTest {

    public static void main(String[] args) {
        // H2 database URL (in-memory database for testing)
        String url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";  // in-memory database
        String user = "sa";
        String password = "";

        // Test the connection
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                System.out.println("Connection to H2 database successful!");
            }
            else {
                System.out.println("Failed to connect to H2 database.");
            }
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}
