package programm.karten;

import programm.system.Darsteller;
import programm.system.Felder;

import java.util.HashMap;

public class Kartenmanager {

    Felder[] zufälligeFelder;
    HashMap<Felder, Ereigniskarte> festeKartenMitFeld;
    Ereigniskarte[] randomKarten;

    public Kartenmanager(HashMap<Felder, Ereigniskarte> festeKartenMitFeld, Ereigniskarte[]randomKarten) {
        zufälligeFelder = new Felder[]{
                Felder.Gemeinschaftsfeld1,
                Felder.Ereignisfeld1
        };
        this.festeKartenMitFeld = festeKartenMitFeld;
        this.randomKarten = randomKarten;
    }

    public boolean karteVonFeldBearbeitet(Felder feld, Darsteller darsteller){
        Ereigniskarte karte = karteZiehen(feld);
        if (karte == null){
            return false;
        }

        bestätigungAbarbeiten(karte, darsteller);

        if (karte.getClass().equals(NormaleKarte.class)){
            ((NormaleKarte)karte).aktionAusführen();
        }else if(karte.getClass().equals(ZuWerkGehen.class)){

        }else if (karte.getClass().equals(Gefängnisfrei.class)){

        }else {
            throw new IllegalArgumentException("Typ von Ereigniskarte nicht erkannt.");
        }

        // TODO Bestätigung von der Karte fordern

        return true;
    }

    private Ereigniskarte karteZiehen(Felder feld){
        // Feste Felder überprüfen
        for (Felder fest : festeKartenMitFeld.keySet()){
            if (fest.equals(feld))
                return festeKartenMitFeld.get(fest);
        }
        // Random Felder überprüfen
        for (Felder zufall : zufälligeFelder){
            if (feld.equals(zufall)){
                return getRandomKarte();
            }
        }
        // Ansonsten null zurückgeben
        return null;
    }

    private Ereigniskarte getRandomKarte(){
        return randomKarten[0];
    }

    private void bestätigungAbarbeiten(Ereigniskarte karte, Darsteller darsteller){
        darsteller.ausgabe(karte.toString());
        // TODO Eingabe abfragen 'a' für bestätigen und 'ü' für übersicht
    }
}
