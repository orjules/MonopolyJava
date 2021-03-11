package programm.system.spieler;

import programm.system.Felder;

public class Spielleiter {
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
        getGeradeDran().aktuellePos = Felder.values()[neuePos];
    }

    public void spielerSetzten(Felder feld){
        getGeradeDran().aktuellePos = feld;
    }

    // Menge darf positiv oder negativ sein
    public void spielerKapitalÄndern(int menge){
        int neuerWert = getGeradeDran().kapital + menge;
        if (neuerWert >= 0){
            getGeradeDran().kapital = neuerWert;
        }else {
            throw new IllegalArgumentException("Mit dieser Menge wäre das Kapital unter null");
        }
    }

    public boolean spielLäuft(){
        if (alleSpieler.length > 0){
            return true;
        }
        return false;
    }
}
