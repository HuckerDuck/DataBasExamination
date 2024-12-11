package se.fredrik.databashantering.DAO;

import se.fredrik.databashantering.JobHive.Employee;
import se.fredrik.databashantering.JobHive.WorkRole;

import java.sql.SQLException;
import java.util.List;

public interface DAO {
    //! Metoder för Employee
    List<Employee> getAllEmployees() throws SQLException;

    Employee getEmployeeByID(int employeeId) throws SQLException;
    void insertEmployee(Employee employee) throws SQLException;
    void updateEmployee(Employee employee) throws SQLException;
    void deleteEmployee(int employeeId) throws SQLException;

    //! Metoder för Workrole
    List<WorkRole> getAllWorkRoles() throws SQLException;

    WorkRole getWorkRoleByID(int workRoleId) throws SQLException;
    void insertWorkRole(WorkRole workRole)throws SQLException;
    void updateWorkRole(WorkRole workRole)throws SQLException;
    void deleteWorkRole(int workroleId)throws SQLException;

    //! Metod för Login
    Employee Login(String Email, String Password) throws SQLException;
}
