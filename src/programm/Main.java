package programm;

import programm.consoleUI.ConsoleHandler;
import programm.consoleUI.Presenter;
import programm.grundstücke.Grundbuch;
import programm.grundstücke.GrundstückFactory;
import programm.karten.KartenFactory;
import programm.karten.Kartenmanager;
import programm.statemashine.Kontext;
import programm.statemashine.io.AusgabeModell;
import programm.system.*;
import programm.system.brett.Brett;
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
        Grundbuch grundbuch = new Grundbuch(GrundstückFactory.erstelleAlleGrundstücke(), GrundstückFactory.erstelleAlleFarbgruppen());
        Kartenmanager kartenmanager = new Kartenmanager(KartenFactory.erstelleFesteKarten(spielleiter),
                KartenFactory.erstelleRandomKarten(spielleiter, grundbuch));
        ConsoleHandler consoleHandler = new ConsoleHandler();
        Kontext kontext = new Kontext();
        Brett brett = new Brett();
        /*kontext.statesFüllen(

                new ErsterWurf(kontext, würfel, spielleiter, brett),
                new Übersicht(kontext, würfel, spielleiter, brett),
                new AllesErledigt(kontext, würfel, spielleiter, brett)
        );
         */
        Presenter presenter = new Presenter(kontext, consoleHandler);


        boolean spielläuft = true;
        while (spielläuft){
           // Do something
        }
    }
}
