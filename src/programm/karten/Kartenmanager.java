package programm.karten;

import programm.system.Darsteller;
import programm.system.Felder;
import programm.system.core.Org_Hilfe;
import programm.system.spieler.Spielleiter;

import java.util.HashMap;
import java.util.Random;

public class Kartenmanager {

    Felder[] zufälligeFelder;
    HashMap<Felder, Ereigniskarte> festeKartenMitFeld;
    Ereigniskarte[] randomKarten;
    Org_Hilfe orgHilfe;

    public Kartenmanager(HashMap<Felder, Ereigniskarte> festeKartenMitFeld, Ereigniskarte[]randomKarten, Org_Hilfe orgHilfe) {
        zufälligeFelder = new Felder[]{
                Felder.Gemeinschaftsfeld1,
                Felder.Ereignisfeld1
        };
        this.festeKartenMitFeld = festeKartenMitFeld;
        this.randomKarten = randomKarten;
        this.orgHilfe = orgHilfe;
    }

    public boolean karteVonFeldBearbeitet(Felder feld, Darsteller darsteller, Spielleiter spielleiter){
        Ereigniskarte karte = karteZiehen(feld);
        if (karte == null){
            return false;
        }
        eingabeAbwarten(karte, darsteller);

        if (spielleiter.jemandHatGeradeAufgegeben()){
            return true;
        }

        if (NormaleKarte.class.isAssignableFrom(karte.getClass())){
            ((NormaleKarte)karte).aktionAusführen();
        }else if(karte instanceof ZuWerkGehen){
            ((ZuWerkGehen) karte).bestätigen();
        }else if (karte instanceof Gefängnisfrei){

        }else {
            throw new IllegalArgumentException("Typ von Ereigniskarte nicht erkannt.");
        }

        darsteller.brettZeichnen();
        darsteller.ausgabe(karte.getBestätigung());
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
        Random rand = new Random();
        return randomKarten[rand.nextInt(randomKarten.length)];
    }

    private void eingabeAbwarten(Ereigniskarte karte, Darsteller darsteller){
        int wert = 0;
        if (karte instanceof MussZahlen)
            wert = ((MussZahlen) karte).getWert();  // Ist leider so gekoppelt, dass getWert vor getBeschreibung aufgerufen werden muss
        orgHilfe.bezahlenOderZuWenigGeld(karte.getBeschreibung(), "\n'a' um zu bestätigen", wert);
    }
}
