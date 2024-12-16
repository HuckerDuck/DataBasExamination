package se.fredrik.databashantering.Tools;

public class Utils {
    public static void waitTimer(int seconds) {
        try{
            Thread.sleep(seconds * 1000);
        }
        catch(InterruptedException e){
            System.out.println("Pausen avbröts av ett fel" + e.getMessage());
        }
    }
}
