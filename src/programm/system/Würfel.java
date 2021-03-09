package programm.system;
import java.util.Random;

public class Würfel {
    public int[] würfeln(){
        Random rnd = new Random();
        int ersteZahl = rnd.nextInt(6) + 1;
        int zweiteZahl = rnd.nextInt(6) + 1;
        int summe = ersteZahl + zweiteZahl;
        int [] ausgabe = {ersteZahl, zweiteZahl, summe};
        return ausgabe;
    }
}
