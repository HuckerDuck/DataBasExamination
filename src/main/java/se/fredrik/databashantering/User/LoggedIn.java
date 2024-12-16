package se.fredrik.databashantering.User;

import se.fredrik.databashantering.DAO.DAOImplicator;
import se.fredrik.databashantering.JobHive.Employee;
import se.fredrik.databashantering.JobHive.WorkRole;
import se.fredrik.databashantering.Tools.InputHandler;
import se.fredrik.databashantering.Tools.SimpleText;

import java.sql.SQLException;

public class LoggedIn {
    private final InputHandler inputHandler;
    private final SimpleText simpleText;
    private final DAOImplicator dao;


    public LoggedIn(DAOImplicator dao, Employee employee, InputHandler inputhandler, SimpleText simpleText) {
        this.inputHandler = inputhandler;
        this.simpleText = simpleText;
        this.dao = dao;
    }

    //! While loop med två val
    //! Mot Anställda / eller mot Arbetsroller
    public void run() {
        boolean running = true;

        while (running) {
            String wait = inputHandler.stringReader("Tryck för att fortsätta");
            simpleText.loggedInText();
            String input = inputHandler.stringReader();

            switch (input) {
                case "1" -> employeeMenu();
                case "2" -> workRoleMenu();
                case "3" -> {
                    simpleText.logOutMenu();
                    running = false;
                }
                default -> simpleText.wrongChoiceThree();
            }
        }

    }


    //! Meny för anställda
    private void employeeMenu() {
        boolean running = true;
        while (running) {
            simpleText.employeeIntroText();
            String input = inputHandler.stringReader();

            switch (input) {
                case "1" -> createEmployee();
                case "2" -> updateEmployee();
                case "3" -> getAllEmployees();
                case "4" -> getEmployeeByID();
                case "5" -> deleteEmployee();
                case "6" -> running = false;
                default -> simpleText.wrongChoiceSix();
            }
        }
    }

    //! Meny för arbetsroller
    private void workRoleMenu() {

            boolean running = true;
            while (running) {
                simpleText.workRoleIntroText();
                String input = inputHandler.stringReader();

                switch (input) {
                    case "1" -> createWorkRole();
                    case "2" -> updateWorkRole();
                    case "3" -> getAllWorkRoles();
                    case "4" -> getworkRolebyID();
                    case "5" -> deleteworkRole();
                    case "6" -> { return; }
                    default -> simpleText.wrongChoiceSix();
                }
            }
        }

    //! __________________________________________________________________________________________________________________________
    //!
    //!                                     Metoder för anställda med Scanner

    //! Skapa en anställd
    private void createEmployee() {
        int roleID = inputHandler.intReader("Ange RoleID ");
        String name = inputHandler.stringReader("Ange namn: ");
        String email = inputHandler.stringReader("Ange Email: ");
        String password = inputHandler.stringReader("Ange Lösenord ");
        int workRoleID = inputHandler.intReader("Ange WorkRole ID: ");

        try {
            // Hämta WorkRole från databasen
            WorkRole workRole = dao.getWorkRoleByID(workRoleID);
            if (workRole == null) {
                System.out.println("Ingen WorkRole hittades med ID: " + workRoleID);
                return;
            }

            // Skapa ny Employee
            Employee newEmployee = new Employee(name, email, password, roleID, workRole);
            dao.insertEmployee(newEmployee);

            String message = """
                 name = %s
                 email = %s
                 password = %s
                 roleID = %d
                 workRole = %s
        Skapad
        """.formatted(name, email, password, roleID, workRole.getTitle());
            System.out.println(message);
            String wait = inputHandler.stringReader("Tryck för att fortsätta");
        } catch (Exception e) {
            System.out.println("Kunde ej skapa anställd: " + e.getMessage());
        }
    }

    //! Uppdatera specifik anställd
    private void updateEmployee() {
        int employeeID = inputHandler.intReader("Ange ID: ");
        String name = inputHandler.stringReader("Ange namn: ");
        String email = inputHandler.stringReader("Ange Email: ");
        String password = inputHandler.stringReader("Ange Password: ");
        int roleID = inputHandler.intReader("Ange RoleID: ");

        try {
            Employee updatedEmployee = new Employee(employeeID, name, email, password, roleID);
            dao.updateEmployee(updatedEmployee);
            System.out.println("Anställd uppdaterad" + name);
            inputHandler.stringReader("Tryck valfri knapp för att fortsätta: ");
        }
        catch (Exception e) {
            System.out.println("Kunde ej uppdatera anställd" + e.getMessage());
            inputHandler.stringReader("Tryck valfri knapp för att fortsätta: ");
        }

    }

    //! Hämta alla anställda
    private void getAllEmployees() {
        try{
            System.out.println("Alla anställda i databasen");
            System.out.println();

            //! Hämta alla anställda från Listan
            var employees = dao.getAllEmployees();

            //! Kolla om listan är tom eller inte
            if (employees.isEmpty()) {
                System.out.println("Det finns inga anställda i databasen");
                inputHandler.stringReader("Tryck valfri knapp för att fortsätta: ");
                return;
            }

            for (Employee employee : employees) {
                System.out.println(employee.toString());

            }
            inputHandler.stringReader("Tryck valfri knapp för att fortsätta: ");
            System.out.println();
        }
        catch (Exception e) {
            System.out.println("Kunde ej hämta alla anställda " + e.getMessage());
            inputHandler.stringReader("Tryck valfri knapp för att fortsätta: ");
        }
    }

    //! Hämta specifik anställd
    private void getEmployeeByID() {
        try {
            int employeeID = inputHandler.intReader("Ange ID: ");

            Employee employee = dao.getEmployeeByID(employeeID);

            System.out.println("Hittade detta i databasen:");
            System.out.println();
            System.out.println(employee.toString());
            System.out.println();

            inputHandler.stringReader("Tryck valfri knapp för att fortsätta: ");
        }
        catch (SQLException e) {
            System.out.println("Kunde ej hitta specifik anställd " + e.getMessage());
            inputHandler.stringReader("Tryck valfri knapp för att fortsätta: ");
        }


    }

    //! Ta bort Anställd
    private void deleteEmployee() {
        try {
            int employeeID = inputHandler.intReader("Ange ID som ska tas bort: ");

            //! Kolla om användaren verkligen ska tas bort
            String confirmDelete = inputHandler.stringReader("Är du säker att du vill ta bort den? ");
            System.out.println("JA / NEJ?");
            if (confirmDelete.equalsIgnoreCase("JA")) {
                dao.deleteEmployee(employeeID);
                System.out.println("Användare" + employeeID + "  är nu borttagen");
                System.out.println();

                inputHandler.stringReader("Tryck valfri knapp för att fortsätta: ");
            }
            else {
                System.out.println("Borttagningen avbröts");
                inputHandler.stringReader("Tryck valfri knapp för att fortsätta: ");
            }
        }
        catch (SQLException e) {
            System.out.println("Kunde ej ta bort specifik anställd " + e.getMessage());
        }

    }


    //! ____________________________________________________________________________________________________________________
    //!                                 Metoder för arbetsroller med Scanner


    //! Skapa en ArbetsRoll
    private void createWorkRole() {
        String titel = inputHandler.stringReader("Ange titel: ");
        String desription = inputHandler.stringReader("Ange beskrivning: ");
        double salary = inputHandler.doubleReader("Ange lön");
        java.sql.Date creationDate = new java.sql.Date(System.currentTimeMillis());


        try {
            WorkRole workrole = new WorkRole(titel, desription, salary, creationDate);

            //! Lägg till den i databasen

            dao.insertWorkRole(workrole);

            String message = """
                     Titel = %s\s
                     Description = %s
                     Salary = %s
                     creationDate = %s
                    \s
                     Skapad
                    \s""";
            System.out.println(message);
            System.out.println();
            inputHandler.stringReader("Tryck valfri knapp för att fortsätta: ");
        }
        catch (Exception e) {
            System.out.println("Kunde ej skapa anställd" + e.getMessage());
            inputHandler.stringReader("Tryck valfri knapp för att fortsätta: ");
        }

    }

    //! Uppdatera en ArbetsRoll
    private void updateWorkRole() {
        int roleId = inputHandler.intReader("Ange ID: ");
        String titel = inputHandler.stringReader("Ange titel: ");
        String desription = inputHandler.stringReader("Ange beskrivning: ");
        double salary = inputHandler.doubleReader("Ange lön");
        java.sql.Date creationDate = new java.sql.Date(System.currentTimeMillis());

        try{
            WorkRole updatedWorkrole = new WorkRole(roleId, titel, desription, salary, creationDate);
            dao.updateWorkRole(updatedWorkrole);
            System.out.printf("Arbetsroll Uppdaterad" + titel);
            System.out.println();
            inputHandler.stringReader("Tryck valfri knapp för att fortsätta: ");

        }
        catch (SQLException e) {
            System.out.println("Kunde ej uppdatera arbetsroll" + e.getMessage());
            inputHandler.stringReader("Tryck valfri knapp för att fortsätta: ");
        }
    }

    //! Hämta en specifik ArbetsRoll
    private void getworkRolebyID() {
        try{
            int workRoleID = inputHandler.intReader("Ange ID: ");

            WorkRole workrole = dao.getWorkRoleByID(workRoleID);

            System.out.println("Hittade detta i databasen");
            System.out.println();
            System.out.println(workrole.toString());
            System.out.println();
            String wait = inputHandler.stringReader("Tryck för att fortsätta");
        }
        catch (SQLException e){
            System.out.println("Kunde ej hitta specifik Arbetsroll");
            System.out.println();
            inputHandler.stringReader("Tryck valfri knapp för att fortsätta: ");
        }

    }

    //! Hämta alla ArbetsRoller
    private void getAllWorkRoles() {
        try{
            System.out.println("Alla arbetsroller i databasen");
            System.out.println();

            var workroles = dao.getAllWorkRoles();
            System.out.println(workroles);

            //! Kolla om listan är tom eller inte

            if(workroles.isEmpty()){
                System.out.println("Det finns inga arbetsroller i databasen");
                String wait = inputHandler.stringReader("Tryck för att fortsätta");
                return;

            }
            inputHandler.stringReader("Tryck valfri knapp för att fortsätta: ");
            System.out.println();
        }
        catch (SQLException e){
            System.out.println("Kunde ej hämta arbetsroller" + e.getMessage());
        }

    }

    //! Ta bort en arbetsROLL
    private void deleteworkRole() {
        try{
            int workRoleID = inputHandler.intReader("Ange ID som ska tas bort: ");

            //! Kolla om ArbetsRollen verkligen ska tas bort
            String confirmDelete = inputHandler.stringReader("Är du säker att du vill ta bort den? ");
            if (confirmDelete.equalsIgnoreCase("JA")) {
                dao.deleteWorkRole(workRoleID);
                System.out.println("Användare" + workRoleID + "  är nu borttagen");
                System.out.println();

                inputHandler.stringReader("Tryck valfri knapp för att fortsätta: ");
            }
            else {
                System.out.println("Borttagningen avbröts");
            }
        }
        catch (SQLException e) {
            System.out.println("Kunde ej ta bort specifik arbetsroll " + e.getMessage());
        }


    }
    }



