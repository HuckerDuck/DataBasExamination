package se.fredrik.databashantering.Tools;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


//! Hjälp klass för att koppla sig mot servern
public class JDBCUtility {

    //! Attribut
    //! Information från application.properties filen

    private static Properties properties = new Properties();

    //! Attribut för att koppla mig mot servern
    private static String DATABAS_URL;
    private static String DATABASE_USER;
    private static String DATABASE_PASSWORD;

    //! Metod för att skapa en koppling mot servern
    //! Drivern är för att koppla sig mot min MySQL server

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABAS_URL, DATABASE_USER, DATABASE_PASSWORD);
    }

    //! Static metod för att hämta information från application.properties filen till servern
    //! Generisk för att användas både i produktion och i test

    public static void loadProperties (String fileName) {
        try (InputStream input = JDBCUtility.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IOException("Kunde ej hitta properties filen" + fileName);
            }
            properties.load(input);

            DATABAS_URL = properties.getProperty("db.url");
            DATABASE_USER = properties.getProperty("db.user");
            DATABASE_PASSWORD = properties.getProperty("db.password");

        }
        catch (IOException e){
            throw new ExceptionInInitializerError("Could not find the application.properties file" + e);
        }
    }

}
