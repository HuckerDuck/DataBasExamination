package se.fredrik.databashantering.Tools;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


//! Hjälp klass för att koppla sig mot servern
public class JDBCUtility {

    //! Attribut
    //! Information från properties filen

    private static Properties properties = new Properties();

    //! Attribut för att koppla mig mot servern
    private static final String DATABAS_URL;
    private static final String DATABASE_USER;
    private static final String DATABASE_PASSWORD;
    private static Connection connection;

    //! Metod för att skapa en koppling mot servern
    //! Drivern är för att koppla sig mot min MySQL server

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABAS_URL, DATABASE_USER, DATABASE_PASSWORD);
    }

    //! Metod för att stänga en koppling

    public static void CloseConnection() throws SQLException {
        try {
            //! Om kopplingen inte är tom
            //! Allså om kopplingen är igång
            //! -> Stäng kopplingen

            if (connection != null) {
                connection.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    //! Commit - Metod för att skicka datan till servern
    public static void commit (Connection connection) throws SQLException {
        try {
            if (connection != null) {
                connection.commit();
                System.out.println("Commit Successful");
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    //! Rollback - Metod om det är så att man har gjort något fel

    public static void rollBack (Connection connection) throws SQLException {
        try {
            //! Om kopplingen finns så rulla tillbaka förändringen
            //! Skriv sedan ut att den har lyckats
            if (connection != null) {
                connection.rollback();
                System.out.println("Rollback Succesful");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    //! Metod för att stänga ett resultSet
    //! resultSet används när du behöver hämta information från den aktiva databasen

    public static void resultSetClose(ResultSet resultSet) throws SQLException {
        try {
            if (resultSet != null) {
                resultSet.close();
                System.out.println("Closing the resultSet - Succesful");
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    //! Static metod för att hämta information från properties filen till servern

    static {
        //! Laddar properties filen
        try (InputStream input = JDBCUtility.class.getClassLoader().getResourceAsStream("properties")) {
            if (input == null) {
                //? Skriver ut felet om den inte hittar properties filen
                throw new IOException("Unable to find the properties file");
            }
            properties.load(input);

            //? Ladda informationen från properties till Attributen i början
            DATABAS_URL = properties.getProperty("db.url");
            DATABASE_USER = properties.getProperty("db.user");
            DATABASE_PASSWORD = properties.getProperty("db.password");

        } catch (IOException e) {
            throw new ExceptionInInitializerError("Unable to read the properties file");
        }
    }

    //! Getter

    public static Properties getProperties() {return properties;}

    //! Setter

    public static void setProperties(Properties properties) {JDBCUtility.properties = properties;}
    public static void setConnection(Connection connection) {JDBCUtility.connection = connection;}


}
