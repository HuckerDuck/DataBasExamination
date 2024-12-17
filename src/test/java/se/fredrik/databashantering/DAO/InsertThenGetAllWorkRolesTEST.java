package se.fredrik.databashantering.DAO;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.fredrik.databashantering.JobHive.WorkRole;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


import java.sql.*;
import java.util.Properties;

import static org.junit.Assert.*;

public class InsertThenGetAllWorkRolesTEST {
    private static String DATABAS_URL;
    private static String DATABASE_USER;
    private static String DATABASE_PASSWORD;
    private static Properties properties = new Properties();

    private static Connection connection;
    private static DAOImplicator dao;


    @BeforeAll
    static void startUp() throws SQLException, IOException {
        // Load the properties file from the classpath
        try (InputStream input = PropertiesFileTest.class.getClassLoader().getResourceAsStream("test.properties")) {
            if (input == null) {
                throw new IOException("Kunde ej hitta properties filen: ");
            }
            // Load properties from the input stream
            properties.load(input);

            // Get properties for DB connection
            DATABAS_URL = properties.getProperty("db.url");
            DATABASE_USER = properties.getProperty("db.user");
            DATABASE_PASSWORD = properties.getProperty("db.password");

        } catch (IOException e) {
            throw new IOException("Could not load properties file: " + e.getMessage(), e);
        }
        //! Instansera Connection
        connection = DriverManager.getConnection(DATABAS_URL, DATABASE_USER, DATABASE_PASSWORD);

        //! Skapa en ny dao och använd kopplingen du precis har skapat ovan
        dao = new DAOImplicator(connection);

        try (Statement statement = connection.createStatement()) {
            statement.execute("""
                         CREATE TABLE IF NOT EXISTS work_role (
                         role_id INT AUTO_INCREMENT PRIMARY KEY,
                         title VARCHAR(50),
                         description VARCHAR(50),
                           salary DOUBLE,
                           creation_date DATE
                         )
                         """);
        }
    }

    @Test
    void insertWorkRoleAndTryRecievingThem() throws SQLException {
        //! Skapa en ny workrole in i tabellen

        WorkRole workRole1 = new WorkRole ("Programmer", "Useful Guy", 50000, new java.sql.Date(System.currentTimeMillis()));
        WorkRole workRole2 = new WorkRole ("UX-Designer", "Cool Guy", 10000, new java.sql.Date(System.currentTimeMillis()));

        //! Lägg in WorkRoles
        dao.insertWorkRole(workRole1);
        dao.insertWorkRole(workRole2);

        //! Hämta ut alla WorkRoles
        List<WorkRole> workRoles = dao.getAllWorkRoles();
        // Använd forEach direkt för att skriva ut formaterad text
        workRoles.stream()
                .forEach(role -> System.out.println("""
                 Title: %s
                 Description: %s
                 Salary: %s
                 CreationDate: %s
                 """.formatted(role.getTitle(),
                        role.getDescription(),
                        role.getSalary(),
                        role.getCreationDate())));

        //! Kör själva testet och kolla att allt fungerar som det ska


        //? Kontrollera om det faktiskt finns två WorkRoles i listan eller inte
        assertEquals(2, workRoles.size());

        //? Kontrollera att det faktiskt finns workRoles med namnet Programmer och UX-Designer
        //? Använder mig av stream för att kolla igenom listan

        assertTrue(workRoles.stream().anyMatch(role -> "Programmer".equalsIgnoreCase(role.getTitle())));
        System.out.println("Hittade Programmer i WorkRole");
        System.out.println("Test lyckades");
        System.out.println();

        assertTrue(workRoles.stream().anyMatch(role -> "UX-Designer".equalsIgnoreCase(role.getTitle())));
        System.out.println("Hittade UX-Designer i WorkRole");
        System.out.println("Test lyckades");
        System.out.println();

        //? Testar med ett test för skojs skull där jag testar en falsk boolean

        Assertions.assertFalse(workRoles.stream().anyMatch(role -> "Speldesigner".equalsIgnoreCase(role.getTitle())));
        System.out.println("Hittade inte Speldesigner i WorkRole");
        System.out.println("Test lyckades");
        System.out.println();

    }

    //? Rensar databasen efter varje test
    @AfterEach
    void cleanDatabase() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("TRUNCATE TABLE work_role");
            System.out.println("Lyckades TRUNCATE Tabellerna");
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Kunde ej TRUNCATE tabellerna" + e.getMessage());
        }
    }

    //! Stänger kopplingen när allt är klart
    @AfterAll
    static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }

    }
}
