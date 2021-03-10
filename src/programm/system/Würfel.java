package programm.system;
import java.util.Random;

public class Würfel {

    private Boolean hatPasch = false;
    private int anzahlPasche = 0;

    public int[] würfeln(){
        Random rnd = new Random();
        hatPasch = false;

        int ersteZahl = rnd.nextInt(6) + 1;
        int zweiteZahl = rnd.nextInt(6) + 1;
        int summe = ersteZahl + zweiteZahl;
        if (ersteZahl == zweiteZahl){
            hatPasch = true;
            anzahlPasche ++;
        }
        int [] ausgabe = {ersteZahl, zweiteZahl, summe};
        return ausgabe;
    }

    public Boolean darfNochmalWerfen(){
        if (hatPasch && anzahlPasche < 3){
            return true;
        }
        return false;
    }

    public Boolean mussInsGefängnis(){
        if (anzahlPasche < 3){
            return false;
        }
        return true;
    }

    public void reset(){
        hatPasch = false;
        anzahlPasche = 0;
    }
}
