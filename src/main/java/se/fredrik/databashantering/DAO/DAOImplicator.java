package se.fredrik.databashantering.DAO;

import se.fredrik.databashantering.JobHive.Employee;
import se.fredrik.databashantering.JobHive.WorkRole;
import se.fredrik.databashantering.JobHive.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOImplicator implements DAO{
    //! Attribut
    private Connection connection;


    //! Konstruktor
    public DAOImplicator(Connection connection) {
        this.connection = connection;
    }


    //! Getter
    public Connection getConnection() {return connection;}


    @Override
    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee");
            ResultSet rs= statement.executeQuery();
            while(rs.next()){
                Employee employee = new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("role_id")
                );

                employees.add(employee);

            }

        }
        catch (SQLException e){
            throw new SQLException("Error when getting all employees");
        }

        return employees;
    }



    @Override
    public Employee getEmployeeByID(int employeeId) throws SQLException {
        return null;
    }

    @Override
    public void insertEmployee(Employee employee) throws SQLException {

    }

    @Override
    public void updateEmployee(Employee employee) throws SQLException {

    }

    @Override
    public void deleteEmployee(int employeeId) throws SQLException {

    }

    @Override
    public List<WorkRole> getAllWorkRoles() throws SQLException {
        return List.of();
    }

    @Override
    public WorkRole getWorkRoleByID(int workRoleId) throws SQLException {
        return null;
    }

    @Override
    public void insertWorkRole(WorkRole workRole) throws SQLException {

    }

    @Override
    public void updateWorkRole(WorkRole workRole) throws SQLException {

    }

    @Override
    public void deleteWorkRole(int workroleId) throws SQLException {

    }
}
