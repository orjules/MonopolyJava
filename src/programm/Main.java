package programm;

import programm.consoleUI.ConsoleHandler;
import programm.consoleUI.Controller;
import programm.consoleUI.Presenter;
import programm.grundstücke.Grundbuch;
import programm.grundstücke.GrundstückFactory;
import programm.karten.KartenFactory;
import programm.karten.Kartenmanager;
import programm.statemashine.Kontext;
import programm.statemashine.io.NeuesAusgabeModell;
import programm.statemashine.states.*;
import programm.system.*;
import programm.system.core.Org_Hilfe;
import programm.system.core.Organisator;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // Setup, damit es von den wichtigen Objekten nur jeweils eins gibt
        Würfel würfel = new Würfel(new Random());
        ArrayList<Spieler> testSpieler = new ArrayList<>(Arrays.asList(
                new Spieler("Günther", '#', Felder.Los, false, 15),
                new Spieler("Monika", '?', Felder.Los, false, 5),
                new Spieler("Detlef", '!', Felder.Los, false, 15)
        ));
        Spielleiter spielleiter = new Spielleiter(testSpieler);
        Darsteller darsteller = new Darsteller(spielleiter);
        Grundbuch grundbuch = new Grundbuch(GrundstückFactory.erstelleAlleGrundstücke(), GrundstückFactory.erstelleAlleFarbgruppen());
        Org_Hilfe orgHilfe = new Org_Hilfe(darsteller, grundbuch, spielleiter);
        Kartenmanager kartenmanager = new Kartenmanager(KartenFactory.erstelleFesteKarten(spielleiter),
                KartenFactory.erstelleRandomKarten(spielleiter, grundbuch), orgHilfe);
        Organisator organisator = new Organisator(spielleiter, darsteller, würfel, grundbuch, kartenmanager, orgHilfe);

        /*
        // eigentliches Spiel starten
        // TODO implemtierung, dass man am Anfang wirft um die Reihenfolge zu entscheiden
        darsteller.brettZeichnen();         // Am Anfang einmal das Spielbrett zeichnen
        organisator.gameLoop();
        */

        // Setup 2
        ConsoleHandler consoleHandler = new ConsoleHandler();
        Kontext kontext = new Kontext();
        kontext.statesReingeben(
                new AllesErledigt(kontext),
                new BesetzesGrundstück(kontext),
                new ErsterWurf(kontext,spielleiter,würfel,grundbuch,kartenmanager),
                new FreiesGrundstück(kontext),
                new ImGefängnis(kontext),
                new KarteZiehen(kontext),
                new Versteigern(kontext),
                new ZuWenigGeld(kontext),
                new Übersicht(kontext)
        );
        Controller controller = new Controller(kontext, consoleHandler);
        Presenter presenter = new Presenter(consoleHandler);


        boolean spielläuft = true;
        while (spielläuft){
            NeuesAusgabeModell ausgabe = controller.eingabeErfragen();
            presenter.present(ausgabe);
        }
    }
}
