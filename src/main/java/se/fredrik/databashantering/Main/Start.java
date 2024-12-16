package se.fredrik.databashantering.Main;

import se.fredrik.databashantering.DAO.DAOImplicator;
import se.fredrik.databashantering.JobHive.Employee;
import se.fredrik.databashantering.Tools.InputHandler;
import se.fredrik.databashantering.Tools.JDBCUtility;
import se.fredrik.databashantering.Tools.SimpleText;
import se.fredrik.databashantering.User.LoggedIn;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import se.fredrik.databashantering.Tools.Utils;



public class Start {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        InputHandler inputhandler = new InputHandler(scanner);
        SimpleText simpleText = new SimpleText();

        //! Ladda propertiesfilen
        try {
            JDBCUtility.loadProperties("application.properties");
        }
        catch (Exception e) {
            System.out.println("Kunde ej ladda properties filen" + e.getMessage());
            return;
        }


        //! Try-catch
        //! Med resources för att stänga ner automatiskt
        try (Connection connection = JDBCUtility.getConnection()) {
            DAOImplicator dao = new DAOImplicator(connection);
            boolean loggedIn = false;

            //! Loop som sätter loggIn till falsk
            //! Lyckas man logga in så skickas man till nästa class

            while(!loggedIn){
                simpleText.introText();
                String input = inputhandler.stringReader();
                switch (input){
                    case "1" -> {
                        //! Inloggning
                        //! Email

                        System.out.println();
                        String email = inputhandler.stringReader("Skriv in Email: ");

                        //! Lösenord

                        String password = inputhandler.stringReader("Skriv in Lösenord: ");
                        try {
                            //! Använder DAO-metoden och kollar om det stämmer
                            Employee employee = dao.Login(email, password);
                            System.out.println();
                            System.out.println("Inloggningen lyckades");
                            System.out.println();
                            Utils.waitTimer(1);
                            loggedIn = true;

                            //! Skriv ut information om användaren

                            System.out.println("Välkommen");
                            System.out.println();
                            System.out.println(employee);
                            Utils.waitTimer(2);


                            //! Skicka användaren till klassen när du är inloggad
                            new LoggedIn(dao, employee, inputhandler, simpleText).run();

                        }
                        catch (SQLException e){
                            System.out.println("Felaktig inloggning" + "/n" + e.getMessage());
                        }
                    }

                    //! Avsluta
                    case "2" -> {
                        simpleText.introTextChooseExit();
                        System.exit(0);

                    }

                    //! Felhantering
                    default -> simpleText.wrongChoiceTwo();

                }
            }


        }

        //! Skriver ut om du ej kan koppla dig mot servern
        catch (SQLException e){
            System.out.println("Could not connect to the server" + e.getMessage());
        }
    }
}