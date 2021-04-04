package programm.karten;

import programm.system.Felder;

import java.util.HashMap;
import java.util.Random;

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

    public Ereigniskarte karteZiehen(Felder feld){
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
        Random rand = new Random();
        return randomKarten[rand.nextInt(randomKarten.length)];
    }
}
