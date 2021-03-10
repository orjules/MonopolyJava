package programm;

import programm.grundstücke.Grundbuch;
import programm.karten.Kartenmanager;
import programm.system.Darsteller;
import programm.system.Organisator;
import programm.system.Spielleiter;
import programm.system.Würfel;

public class Main {

    public static void main(String[] args) {
        // Setup, damit es von den wichtigen Objekten nur jeweils eins gibt
        Würfel würfel = new Würfel();
        Spielleiter spielleiter = new Spielleiter();
        Darsteller darsteller = new Darsteller(spielleiter);
        Grundbuch grundbuch = new Grundbuch();
        Kartenmanager kartenmanager = new Kartenmanager();
        Organisator organisator = new Organisator(spielleiter, darsteller, würfel, grundbuch, kartenmanager);
        // eigentliches Spiel starten
        organisator.gameLoop();
    }
}
