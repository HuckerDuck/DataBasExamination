package se.fredrik.databashantering.Main;

import se.fredrik.databashantering.DAO.DAOImplicator;
import se.fredrik.databashantering.Tools.JDBCUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class main {
    public static void main(String[] args) throws SQLException {


        //! Try med resources
        //! Använder sig av AutoCloseable
        //! När try blocket avslutas så kommer den automatiskt att kalla på metoden close()
        try{
            DAOImplicator dao = new DAOImplicator(JDBCUtility.getConnection());

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}