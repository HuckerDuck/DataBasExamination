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
    //! Information från application.properties filen

    private static Properties properties = new Properties();

    //! Attribut för att koppla mig mot servern
    private static String DATABAS_URL;
    private static String DATABASE_USER;
    private static String DATABASE_PASSWORD;
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
                connection = null;
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

    //! Stäng kopplingen mot servern
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
                System.out.println("Databasen stänges framgångsrikt. Woop Woop!");
            }
        } catch (SQLException e) {
            System.err.println("Misslyckades med att stänga databasen " + e.getMessage());
            e.printStackTrace();
        }
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



    //! Getter

    public static Properties getProperties() {return properties;}

    //! Setter

    public static void setProperties(Properties properties) {JDBCUtility.properties = properties;}
    public static void setConnection(Connection connection) {JDBCUtility.connection = connection;}



}
