package se.fredrik.databashantering.DAO;

import se.fredrik.databashantering.JobHive.Employee;
import se.fredrik.databashantering.JobHive.WorkRole;

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

    //! Hämta alla anställda och lägg de i en lista
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
            throw new SQLException("Error when getting all employees", e);
        }

        return employees;
    }

    //! ___________________________________________________________________________________________________________________________________________
    //! ___________________________________________________________________________________________________________________________________________
    //!                                                     EMPLOYEE

    //! Hämta specifik anställd via ID
    @Override
    public Employee getEmployeeByID(int employeeId) throws SQLException {
        String sql = "SELECT * FROM employee WHERE employee_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1, employeeId);
            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    return new Employee(
                            rs.getInt("employee_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getInt("role_id")
                    );
                }
                else {
                    throw new SQLException("Employee was not found" + employeeId);
                }

            }

        }
        catch (SQLException e){
            throw new SQLException("Error when getting employee by ID");
        }
    }

    //! Lägg till en anställd
    @Override
    public void insertEmployee(Employee employee) throws SQLException {
        String sql = "Insert into employee(name, email, password, role_id) values(?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getEmail());
            pstmt.setString(3, employee.getPassword());
            pstmt.setInt(4, employee.getRoleId());
            pstmt.executeUpdate();
        }
        catch (SQLException e){
            throw new SQLException("Error when inserting employee", e);
        }
    }

    //! Uppdatera en anställd
    @Override
    public void updateEmployee(Employee employee) throws SQLException {
        //! Textblock, introducerad i JAVA 15
        String sql = """
          UPDATE employee
          SET NAME = ?,
              email = ?,
              password = ?
          WHERE employee_id = ?
        """;
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getEmail());
            pstmt.setString(3, employee.getPassword());
            pstmt.setInt(4, employee.getEmployeeId());
            pstmt.executeUpdate();
        }

        catch (SQLException e){
            throw new SQLException("Error when updating employee", e);
        }
    }

    //! Ta bort en anställd
    @Override
    public void deleteEmployee(int employeeId) throws SQLException {
        String sql =
        """
        DELETE FROM employee 
        WHERE employee_id = ?
        """;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1, employeeId);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 0){
                throw new SQLException("Ingen anställd med de Id:t" + employeeId + " hittades");
            }
        }

        catch (SQLException e){
            throw new SQLException("Error when deleting employee");
        }
    }

    //! ___________________________________________________________________________________________________________________________________________
    //! ___________________________________________________________________________________________________________________________________________
    //!                                                     WORKROLE

    //! Hämta alla workroles
    @Override
    public List<WorkRole> getAllWorkRoles() throws SQLException {
        List<WorkRole> workRoles = new ArrayList<>();
        String sql = "SELECT * FROM work_role";

        //! Try-with-resources
        //! Stänger automatiskt när det är klart
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                WorkRole workRole = new WorkRole(
                        rs.getInt("role_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDouble("salary"),
                        rs.getDate("creation_date")
                );
                workRoles.add(workRole);
            }



        }
        catch (SQLException e){
            throw new SQLException("Error when getting all work roles");
        }

        return workRoles;
    }

    @Override
    public WorkRole getWorkRoleByID(int workRoleId) throws SQLException {
        String sql = "SELECT * FROM work_role WHERE role_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1, workRoleId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()){
                    return new WorkRole(
                            rs.getInt("role_id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getDouble("salary"),
                            rs.getDate("creation_date")
                    );

                }

                else {
                    throw new SQLException("Work role was not found" + workRoleId);
                }
            }
            catch (SQLException e){
                throw new SQLException("Error when getting work role by ID", e);
            }
        }
    }

    @Override
    public void insertWorkRole(WorkRole workRole) throws SQLException {
        String sql = "Insert into work_role(title, description, salary, creation_date) values(?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, workRole.getTitle());
            pstmt.setString(2, workRole.getDescription());
            pstmt.setDouble(3, workRole.getSalary());
            pstmt.setDate(4, workRole.getCreationDate());
            pstmt.executeUpdate();

        }
        catch (SQLException e){
            throw new SQLException("Error when inserting work role", e);
        }
    }

    @Override
    public void updateWorkRole(WorkRole workRole) throws SQLException {
        String sql = "Update work_role SET title = ?, description = ?, salary = ?, creation_date = ? WHERE role_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, workRole.getTitle());
            pstmt.setString(2, workRole.getDescription());
            pstmt.setDouble(3, workRole.getSalary());
            pstmt.setDate(4, workRole.getCreationDate());
            pstmt.setInt(5, workRole.getRoleId());
            pstmt.executeUpdate();
        }
        catch (SQLException e){
            throw new SQLException("Error when updating work role", e);
        }

    }

    @Override
    public void deleteWorkRole(int workroleId) throws SQLException {
        String sql = "DELETE FROM work_role WHERE role_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, workroleId);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("No work role found with ID: " + workroleId);
            }
        }
        catch (SQLException e) {
            throw new SQLException("Error when deleting work role with ID: " + workroleId, e);
        }
    }

    //! ___________________________________________________________________________________________________________________________________________
    //! ___________________________________________________________________________________________________________________________________________
    //!                                                     Inloggning


    @Override
    public Employee Login(String Email, String Password) throws SQLException {
        String sql =
        //! TextBlock för SQL
                //! Gör att du slipper skriva en massa + överallt
        """
        SELECT e.*, w.Title, w.Description, w.Salary, w.Creation_date
        FROM employee e
        INNER JOIN work_role w ON e.role_id = w.role_id
        WHERE e.email = ? AND e.password = ?;
    """;

        //! Kolla dig mot servern och kolla om det fungerar
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, Email);
            pstmt.setString(2, Password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    //! Skapa en ny workRole och Sen Employee
                    //! Kolla om det sedan finns i MySQL-Databasen

                    WorkRole workRole = new WorkRole(
                            rs.getInt("role_id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getDouble("salary"),
                            rs.getDate("creation_date")
                    );

                    Employee employee = new Employee(
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getInt("role_id"),
                            workRole
                    );

                    return employee;

                } else {
                    throw new SQLException("Inloggningen misslyckades" + "\n " + "Felaktigt email eller lösenord");

                }
            }
        }


        //! Kastar SQLException om du ej kommer in på servern
        catch (SQLException e){
            throw new SQLException("Error when trying to login", e);
        }

    }
}
