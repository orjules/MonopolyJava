package programm.system.spieler;

import programm.system.Felder;


public class Spielleiter {
    private Spieler[] alleSpieler = new Spieler[] {
            new Spieler("Günther", '#', Felder.Los, false, 1500),
            new Spieler("Monika", '?', Felder.Los, false, 1500),
            new Spieler("Detlef", '!', Felder.Los, false, 1),
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
    public void kapitalÄndernVon(Spieler spieler, int menge){
        int neuerWert = spieler.kapital + menge;
        if (neuerWert >= 0){
            spieler.kapital = neuerWert;
        }else {
            throw new IllegalArgumentException("Mit dieser Menge wäre das Kapital unter null");
        }
    }

    public void geldÜbertragen(Spieler von, Spieler an, int menge){
        kapitalÄndernVon(von, -menge);
        kapitalÄndernVon(an, menge);
    }

    public void aufgeben(){
        // dafür muss erst der Array zu ArrayList gemacht werden.
    }

    public boolean spielLäuft(){
        if (alleSpieler.length > 0){
            return true;
        }
        return false;
    }
}
