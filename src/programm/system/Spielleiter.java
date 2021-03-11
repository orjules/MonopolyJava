package programm.system;

import programm.system.enums.Felder;
import programm.system.interfaces.ISpielleiter;

public class Spielleiter implements ISpielleiter {
    private Spieler[] alleSpieler = new Spieler[] {
            new Spieler("Günther", '#', Felder.Los, false, 1500),
            new Spieler("Monika", '?', Felder.Los, false, 1500),
            new Spieler("Detlef", '!', Felder.Los, false, 1500),
    };
    private int geradeDran = 0;

    public Spieler getGeradeDran(){
        return alleSpieler[geradeDran];
    }

    public Spieler[] getAlleSpieler() {
        return alleSpieler;
    }

    public void weiter(){
        int neuerWert = geradeDran + 1;
        if (neuerWert >= alleSpieler.length){
            neuerWert -= alleSpieler.length;
        }
        geradeDran = neuerWert;
    }

    public void spielerBewegen(int wert){
        int neuePos = getGeradeDran().getAktuellePos().ordinal() + wert;
        if (neuePos >= Felder.values().length){
            neuePos -= Felder.values().length;
            // TODO hier das über los gehen erkennen
        }
        getGeradeDran().setAktuellePos(Felder.values()[neuePos]);
    }

    public boolean spielLäuft(){
        if (alleSpieler.length > 0){
            return true;
        }
        return false;
    }
}
