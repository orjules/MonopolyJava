package programm.system;
import java.util.Random;

public class Würfel {
    private boolean hatPasch = false;
    private int anzahlPasche = 0;
    private int letzterWurf = 0;

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
        letzterWurf = summe;
        return ausgabe;
    }

    public boolean darfNochmalWerfen(){
        if (hatPasch && anzahlPasche < 3){
            return true;
        }
        return false;
    }

    public boolean mussInsGefängnis(){
        if (anzahlPasche < 3){
            return false;
        }
        return true;
    }

    public void reset(){
        hatPasch = false;
        anzahlPasche = 0;
    }

    public int getLetztenWurf(){
        return letzterWurf;
    }
}
