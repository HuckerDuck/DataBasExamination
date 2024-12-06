package se.fredrik.databashantering.JobHive;

public class Employee {

    //! Private Attribut

    private int employeeId;
    private String name;
    private String email;
    private String password;
    private int roleId;

    //! Konstruktor

    public Employee(int employeeId, String name, String email, String password, int roleId) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }

    //! Getter

    public int getEmployeeId() {return employeeId;}
    public String getName() {return name;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public int getRoleId() {return roleId;}

    //! Setter

    public void setEmployeeId(int employeeId) {this.employeeId = employeeId;}
    public void setName(String name) {this.name = name;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}
    public void setRoleId(int roleId) {this.roleId = roleId;}
}
