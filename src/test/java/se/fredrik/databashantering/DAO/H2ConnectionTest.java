package se.fredrik.databashantering.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2ConnectionTest {

    public static void main(String[] args) {
        String url = "jdbc:h2:tcp://localhost:9191/~/testdb"; // URL för H2-servern
        String user = "sa"; // Standardanvändare
        String password = ""; // Standardlösenord (ofta tomt)

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                System.out.println("Anslutning till H2-servern lyckades!");
            }
        } catch (SQLException e) {
            System.err.println("Misslyckades med att ansluta till H2-servern: " + e.getMessage());
        }
    }
}
