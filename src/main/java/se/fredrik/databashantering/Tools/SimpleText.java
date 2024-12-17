package se.fredrik.databashantering.Tools;

public class SimpleText {
    public void loggedInText(){
        System.out.println();
        System.out.println("1 - Meny för Anställda");
        System.out.println("2 - Meny för Arbetsroller");
        System.out.println("3 - Logga ut");
        System.out.println();
    }

    public void wrongChoiceTwo(){
        System.out.println();
        System.out.println("Vänligen skriv ut en siffra mellan 1-2");
        System.out.println();
    }
    public void wrongChoiceThree(){
        System.out.println();
        System.out.println("Vänligen skriv ut en siffra mellan 1-3");
        System.out.println();
    }

    public void wrongChoiceSix(){
        System.out.println();
        System.out.println("Vänligen skriv ut en siffra mellan 1-6");
        System.out.println();
    }


    public void introText(){
        System.out.println();
        System.out.println("1: Logga in");
        System.out.println("2: Avsluta");
        System.out.println();
    }

    public void introTextChooseExit(){
        System.out.println("Programmet avslutas...");
        System.out.println("..........");
        System.out.println("Välkommen åter");
        System.out.print(".");
        System.out.print("....");
        System.out.println();
    }

    public void logOutMenu(){
        System.out.println();
        System.out.println("Loggar ut dig");
        System.out.println();
    }

    public void employeeIntroText(){
        System.out.println();
        System.out.println("1 - Skapa en ny anställd");
        System.out.println("2 - Uppdatera en anställd");
        System.out.println("3 - Hämta alla anställda");
        System.out.println("4 - Hämta en specifik anställd");
        System.out.println("5 - Ta bort en anställd");
        System.out.println("6 - Gå tillbaka till huvudmenyn");
        System.out.println();
    }

    public void workRoleIntroText(){
        System.out.println();
        System.out.println("1 - Skapa en ny arbetsroll");
        System.out.println("2 - Uppdatera en arbetsroll");
        System.out.println("3 - Hämta alla arbetsroller");
        System.out.println("4 - Hämta en specifik arbetsroll");
        System.out.println("5 - Ta bort en arbetsroll");
        System.out.println("6 - Gå tillbaka till huvudmenyn");
        System.out.println();
    }
}
