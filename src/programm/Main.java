package programm;

import programm.grundstücke.Grundbuch;
import programm.grundstücke.GrundstückFactory;
import programm.karten.KartenFactory;
import programm.karten.Kartenmanager;
import programm.system.*;
import programm.system.core.Org_Hilfe;
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
                new Spieler("Günther", '#', Felder.Los, false, 15),
                new Spieler("Monika", '?', Felder.Los, false, 5),
                new Spieler("Detlef", '!', Felder.Los, false, 15)
        ));
        Spielleiter spielleiter = new Spielleiter(testSpieler);
        Darsteller darsteller = new Darsteller(spielleiter);
        Grundbuch grundbuch = new Grundbuch(GrundstückFactory.erstelleAlleGrundstücke(), GrundstückFactory.erstelleAlleFarbgruppen());
        KartenFactory factory = new KartenFactory(spielleiter, grundbuch);
        Org_Hilfe orgHilfe = new Org_Hilfe(darsteller, grundbuch, spielleiter);
        Kartenmanager kartenmanager = new Kartenmanager(factory.erstelleFesteKarten(), factory.erstelleRandomKarten(), orgHilfe);
        Organisator organisator = new Organisator(spielleiter, darsteller, würfel, grundbuch, kartenmanager, orgHilfe);

        // eigentliches Spiel starten
        // TODO implemtierung, dass man am Anfang wirft um die Reihenfolge zu entscheiden
        darsteller.brettZeichnen();         // Am Anfang einmal das Spielbrett zeichnen
        organisator.gameLoop();
    }
}
