package se.fredrik.databashantering.Main;

import se.fredrik.databashantering.DAO.DAOImplicator;
import se.fredrik.databashantering.Tools.InputHandler;
import se.fredrik.databashantering.Tools.JDBCUtility;
import se.fredrik.databashantering.Tools.SimpleText;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


public class main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner (System.in);
        InputHandler inputhandler = new InputHandler(scanner);
        SimpleText simpleText = new SimpleText();

        //! Try-catch
        //! Med resources för att stänga ner automatiskt
        try (Connection connection = JDBCUtility.getConnection()) {
            DAOImplicator dao = new DAOImplicator(connection);
            boolean loggedIn = false;

            //! Loop som sätter loggIn till falsk
            //! Lyckas man logga in så skickas man till nästa class

            while(!loggedIn){
                simpleText.introText();
                String input = inputhandler.stringReader("Ditt val: ");
                switch (input){
                    //! Inloggning
                    case "1" -> loggedIn = true;

                    //! Avsluta
                    case "2" -> {
                        simpleText.introTextChooseExit();
                        System.exit(0);

                    }

                    //! Felhantering
                    default -> simpleText.introTextWrongChoice();

                }
            }


        }

        //! Skriver ut om du ej kan koppla dig mot servern
        catch (SQLException e){
            System.out.println("Could not connect to the server" + e.getMessage());
        }
    }
}