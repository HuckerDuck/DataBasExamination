package se.fredrik.databashantering.JobHive;

import java.util.Date;

public class WorkRole {

    //! Privata attribut

    private int roleId;
    private String title;
    private String description;
    private double salary;
    private Date creationDate;

    //! Konstruktor

    public WorkRole(int roleId, String title, String description, double salary, Date creationDate) {
        this.roleId = roleId;
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.creationDate = creationDate;
    }

    //! Getter

    public int getRoleId() {return roleId;}
    public String getTitle() {return title;}
    public String getDescription() {return description;}
    public double getSalary() {return salary;}
    public Date getCreationDate() {return creationDate;}

    //! Setter

    public void setRoleId(int roleId) {this.roleId = roleId;}
    public void setTitle(String title) {this.title = title;}
    public void setDescription(String description) {this.description = description;}
    public void setSalary(double salary) {this.salary = salary;}
    public void setCreationDate(Date creationDate) {this.creationDate = creationDate;}
}
