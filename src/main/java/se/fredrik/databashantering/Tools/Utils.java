package se.fredrik.databashantering.Tools;

public class Utils {
    public static void waitTimer(int milliseconds) {
        try {
            Thread.sleep(milliseconds); // Pausar i angivet antal millisekunder
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Pausen avbröts.");
        }
    }

}
