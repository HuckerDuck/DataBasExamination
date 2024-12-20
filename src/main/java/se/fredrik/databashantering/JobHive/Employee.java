package se.fredrik.databashantering.JobHive;
public class Employee {

    //! Private Attribut

    private int employeeId;
    private String name;
    private String email;
    private String password;
    private int roleId;

    private WorkRole workRole;

    //! Konstruktor utan workrole

    public Employee(int employeeId, String name, String email, String password, int roleId) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }

    //! Konstruktor med allt
    public Employee(String name, String email, String password, int roleId, WorkRole workRole) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
        this.workRole = workRole;
    }

    public Employee(String name, String email, String password, int roleID) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roleId = roleID;
    }



    //! ToString metod
    @Override
    public String toString() {
        return

            """
            ARBETSTAGARE
            
            employeeId = %d
            name = %s
            email = %s
            password = %s
            roleId = %d
            
            ARBETSROLL: = %s
            """
                .formatted(employeeId, name, email, password, roleId,workRole);
    }

    //! Getter

    public int getEmployeeId() {return employeeId;}
    public String getName() {return name;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public int getRoleId() {return roleId;}
    public WorkRole getWorkRole() {return workRole;}

    //! Setter

    public void setEmployeeId(int employeeId) {this.employeeId = employeeId;}
    public void setName(String name) {this.name = name;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}
    public void setRoleId(int roleId) {this.roleId = roleId;}
    public void setWorkRole(WorkRole workRole) {this.workRole = workRole;}
}
