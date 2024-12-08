package se.fredrik.databashantering.DAO;

import se.fredrik.databashantering.JobHive.WorkRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    //! Attribut
    private Connection connection;

    //! Konstruktor
    public DAO(Connection connection) {
        this.connection = connection;
    }

    //! Metod för att lägga till i WorkRole
    public void InsertWorkRole(WorkRole workRole) {
        String sql = "Insert into work_role(title, description, salary, creation_date) values(?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString (1, workRole.getTitle()); // Arbetstitel
            preparedStatement.setString (2, workRole.getDescription()); // Arbetsbeskrivning
            preparedStatement.setDouble (3, workRole.getSalary()); // Lön
            preparedStatement.setDate(4, new java.sql.Date(new java.util.Date().getTime())); // Datum vid skapande
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            //! För felsökning
            e.printStackTrace();
        }
    }

    //! Metod för att hämta alla i Workrole
    public List<WorkRole> GetAllWorkRoles() {}>

    {
        String sql = "Select * from work_role";
        //! Skapa en lista som kommer spara allt
        List<WorkRole> workRoles = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)); {

        }
        catch (SQLException e){
            e.printStackTrace();
        }



    }



}
