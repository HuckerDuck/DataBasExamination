package se.fredrik.databashantering.Tools;

public class SimpleText {
    public void loggedInText(){
        System.out.println();
        System.out.println("1: Skriv ut allt i databasen");
        System.out.println("2: Välj en specifik data i databasen utifrån personID");
        System.out.println("3: Lägg in en ny person");
        System.out.println("4: Uppdatera en person");
        System.out.println("5: Ta bort en person");
        System.out.println("6: Avsluta och spara förändringar");
    }

    public void wrongChoice(){
        System.out.println("Vänligen skriv ut en siffra mellan 1-6");
    }

    public void introText(){
        System.out.println();
        System.out.println("1: Logga in");
        System.out.println("2: Avsluta");
    }

    public void introTextWrongChoice(){
        System.out.println();
        System.out.println("Vänligen välj en siffra mellan 1-2");
        System.out.println();
    }

    public void introTextChooseExit(){
        System.out.println("Programmet avslutas...");
        System.out.println("..........");
        System.out.println("Välkommen åter");
        System.out.print(".");
        System.out.print("....");
    }
}
