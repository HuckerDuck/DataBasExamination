package se.fredrik.databashantering.Tools;

import java.util.Scanner;

public class InputHandler {
    //! Attribut för scanner
    private final Scanner scanner;

    //! Konstruktor för scanner
    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    //! Metod för att läsa Strängar
    //! Gör det mer modulärt och under prompt kan du skriva texten som kommer upp för användaren
    public String stringReader() {
        System.out.print("Ditt val: ");
        return scanner.nextLine();
    }

    public String stringReader(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int intReader(String prompt) {
        System.out.print(prompt);
        int value = scanner.nextInt();

        //! Scanner nextLine för att undvika problem

        scanner.nextLine();
        return value;
    }


    public double doubleReader(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Felaktig inmatning, vänligen skriv in en siffra (double)");
        }
        //! Returnerar ett scanner med en double tillbaka
        return scanner.nextDouble();
    }
}
