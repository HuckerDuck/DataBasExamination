package se.fredrik.databashantering.DAO;

import org.junit.jupiter.api.*;
import se.fredrik.databashantering.Tools.JDBCUtility;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class DAOImplicatorTest {
    private static Connection connection;

    @BeforeAll
    static void setUp() {
        // Ladda test.properties för H2-databasen
        JDBCUtility.loadProperties("test.properties");

        try {
            connection = JDBCUtility.getConnection();
            try (Statement stmt = connection.createStatement()) {
                // Skapa tabell för testerna
                stmt.execute("CREATE TABLE work_role (" +
                        "role_id INT PRIMARY KEY AUTO_INCREMENT, " +
                        "title VARCHAR(100), " +
                        "description VARCHAR(255), " +
                        "salary DOUBLE, " +
                        "creation_date DATE)");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to set up the test database: " + e.getMessage());
        }
    }

    @AfterAll
    static void tearDown() throws SQLException {
        JDBCUtility.closeConnection();
    }

    @Test
    void testDatabaseConnection() {
        Assertions.assertNotNull(connection, "Database connection should be established.");
    }
}
