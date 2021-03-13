package programm;

import programm.grundstücke.Grundbuch;
import programm.karten.Kartenmanager;
import programm.system.*;
import programm.system.core.Organisator;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // Setup, damit es von den wichtigen Objekten nur jeweils eins gibt
        Würfel würfel = new Würfel();
        ArrayList<Spieler> testSpieler = new ArrayList<>(Arrays.asList(
                new Spieler("Günther", '#', Felder.Los, false, 1500),
                new Spieler("Monika", '?', Felder.Los, false, 500),
                new Spieler("Detlef", '!', Felder.Los, false, 15)
        ));
        Spielleiter spielleiter = new Spielleiter(testSpieler);
        Darsteller darsteller = new Darsteller(spielleiter);
        Grundbuch grundbuch = new Grundbuch();
        Kartenmanager kartenmanager = new Kartenmanager();
        Organisator organisator = new Organisator(spielleiter, darsteller, würfel, grundbuch, kartenmanager);

        // eigentliches Spiel starten
        // TODO implemtierung, dass man am Anfang wirft um die Reihenfolge zu entscheiden
        darsteller.brettZeichnen();         // Am Anfang einmal das Spielbrett zeichnen
        organisator.gameLoop();
    }
}
