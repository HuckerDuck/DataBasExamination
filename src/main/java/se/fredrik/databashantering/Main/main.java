package se.fredrik.databashantering.Main;

import se.fredrik.databashantering.DAO.DAOImplicator;
import se.fredrik.databashantering.Tools.InputHandler;
import se.fredrik.databashantering.Tools.JDBCUtility;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


public class main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner (System.in);
        InputHandler inputhandler = new InputHandler(scanner);


        try (Connection connection = JDBCUtility.getConnection()) {
            DAOImplicator dao = new DAOImplicator(connection);
            boolean loggedIn = false;

            while


        }

        //! Skriver ut om du ej kan koppla dig mot servern
        catch (SQLException e){
            System.out.println("Could not connect to the server" + e.getMessage());
        }
    }
}