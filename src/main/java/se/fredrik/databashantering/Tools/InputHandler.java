package se.fredrik.databashantering.Tools;

import java.util.Scanner;

public class InputHandler {
    //! Attribut för scanner
    private Scanner scanner;

    //! Konstruktor för scanner
    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    //! Metod för att läsa Strängar
    //! Gör det mer modulärt och under promt kan du skriva texten som kommer upp för användaren
    public String stringReader(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public int intReader(String prompt) {
        System.out.println(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Felaktig inmatning, vänligen skriv in en siffra (int)");
            //! Scanner.next används för att rensa det som användaren skrev in
            scanner.next();
        }
        //! Returnerar en scanner med en int tillbaka
        return scanner.nextInt();
    }

    public double doubleReader(String prompt) {
        System.out.println(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Felaktig inmatning, vänligen skriv in en siffra (double)");
        }
        //! Returnerar ett scanner med en double tillbaka
        return scanner.nextDouble();
    }
}
