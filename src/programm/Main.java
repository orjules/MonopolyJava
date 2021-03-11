package programm;

import programm.grundstücke.Grundbuch;
import programm.karten.Kartenmanager;
import programm.system.*;

public class Main {

    public static void main(String[] args) {
        // Setup, damit es von den wichtigen Objekten nur jeweils eins gibt
        Würfel würfel = new Würfel();
        Spielleiter spielleiter = new Spielleiter();
        Darsteller darsteller = new Darsteller(spielleiter);
        Grundbuch grundbuch = new Grundbuch();
        Kartenmanager kartenmanager = new Kartenmanager();
        Organisator organisator = new Organisator(spielleiter, darsteller, würfel, grundbuch, kartenmanager);
        Organisator2 organisator2 = new Organisator2(spielleiter, darsteller, würfel, grundbuch, kartenmanager);

        // eigentliches Spiel starten
        // TODO implemtierung, dass man am Anfang wirft um die Reihenfolge zu entscheiden
        darsteller.brettZeichnen();         // Am Anfang einmal das Spielbrett zeichnen
        organisator2.gameLoop();
    }
}
