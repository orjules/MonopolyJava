package programm.system.spieler;

import programm.system.Felder;

import java.util.ArrayList;
import java.util.Arrays;


public class Spielleiter {
    private ArrayList<Spieler> alleSpieler = new ArrayList<>();
    private Spieler geradeDran;

    public Spielleiter(ArrayList<Spieler> alleSpieler) {
        this.alleSpieler = alleSpieler;
        geradeDran = alleSpieler.get(0);
    }


    public Spieler getGeradeDran(){
        return geradeDran;
    }

    public Spieler[] getAlleSpieler() {
        return alleSpieler.toArray(new Spieler[alleSpieler.size()]);
    }

    public void weiter(){
        int neuerWert = alleSpieler.indexOf(geradeDran) + 1;
        if (neuerWert >= alleSpieler.size()){
            neuerWert -= alleSpieler.size();
        }
        geradeDran = alleSpieler.get(neuerWert);
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
        int alterIndex = alleSpieler.indexOf(geradeDran);
        alleSpieler.remove(geradeDran);
        if (alleSpieler.size() == 0){
            return;
        }else {
            // Wenn es der letzte Spieler war, bleibt dieser aber noch der Spieler, der gerade dran ist
            if (alterIndex == alleSpieler.size()){
                // Für den Fall, dass der letzte in der Reihenfolge rausgelöscht wurde
                alterIndex --;
            }
            geradeDran = alleSpieler.get(alterIndex);
        }
    }

    public boolean spielLäuft(){
        if (alleSpieler.size() > 1){
            return true;
        }
        return false;
    }
}
