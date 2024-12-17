package se.fredrik.databashantering.DAO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileTest {

    private static String DATABAS_URL;
    private static String DATABASE_USER;
    private static String DATABASE_PASSWORD;
    private static Properties properties = new Properties();

    public static void loadProperties(String fileName) throws IOException {
        // Load the properties file from the classpath
        try (InputStream input = PropertiesFileTest.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IOException("Kunde ej hitta properties filen: " + fileName);
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
    }

    public static void main(String[] args) {
        try {
            // Load properties from the "test.properties" file
            loadProperties("test.properties");
        } catch (IOException e) {
            System.out.println("Kunde ej ladda properties filen: " + e.getMessage());
            return;
        }

        // Output the database connection details
        System.out.println("Database URL: " + DATABAS_URL);
        System.out.println("Database User: " + DATABASE_USER);
    }
}
