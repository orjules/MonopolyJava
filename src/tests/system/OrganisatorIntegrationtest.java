package tests.system;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import programm.grundstücke.Grundbuch;
import programm.karten.Kartenmanager;
import programm.system.Felder;
import programm.system.core.Organisator;
import programm.system.spieler.Spieler;
import tests.system.mocks.MockDarsteller;
import tests.system.mocks.MockSpielleiter;
import tests.system.mocks.MockWürfel;

import java.util.ArrayList;
import java.util.Arrays;

public class OrganisatorIntegrationtest {
    /*
    - Spieler kommt auf freies Feld
    - Spieler kommt auf Grundstück
        - frei
            - Genug Geld
            - Nicht genug Geld
        - Besetzt
            - Genug Geld
            - Nicht genug Geld
    - Spieler kommt Auf Karte (später)
     */

    MockDarsteller darsteller;

    public Organisator setUp(int schritte, ArrayList<String> eingaben){
        // Spieler definieren
        ArrayList<Spieler> testSpieler = new ArrayList<>(Arrays.asList(
                new Spieler("Günther", '#', Felder.Los, false, 1500),
                new Spieler("Monika", '?', Felder.Los, false, 1500),
                new Spieler("Detlef", '!', Felder.Los, false, 1500)
        ));
        // Würfel definieren mit schritte als festen Wert
        MockWürfel würfel = new MockWürfel(schritte);
        // Alles für den Organisator erstellen
        MockSpielleiter spielleiter = new MockSpielleiter(testSpieler);
        Grundbuch grundbuch = new Grundbuch();
        Kartenmanager kartenmanager = new Kartenmanager();
        darsteller = new MockDarsteller(spielleiter, eingaben);
        return new Organisator(spielleiter, darsteller, würfel, grundbuch, kartenmanager);
    }

    @Test
    public void aufFreiesFeld(){
        // Eigenes Setup
        ArrayList<String> eingaben = new ArrayList<>(Arrays.asList("w", "z"));
        Organisator org = setUp(10, eingaben);
        // Ausführen
        org.gameLoop();
        assertEquals("Günther (#) ist dran.Als erstes musst du würfeln. Drücke 'w'Günther hat eine 5, 5 gewürfelt." +
                "Du bist auf Gefängnis_bzw_Besuch gelandet. Hier passiert nichts weiter." +
                "Günther ist fertig mit den wichtigen Dingen aber noch dran." +
                "'ü' um die Übersicht zu öffnen\n'w' um nochmal zu würfeln\n", darsteller.getMitschrift());
    }

    // Testweise gehören alle Grundstücje dem Detlef
        /*
        grundbuch.übertragenAn(grundbuch.grundstückVon(Felder.Turmstraße), testSpieler.get(2));
        grundbuch.übertragenAn(grundbuch.grundstückVon(Felder.Badstraße), testSpieler.get(2));
        grundbuch.übertragenAn(grundbuch.grundstückVon(Felder.Südbahnhof), testSpieler.get(2));
        grundbuch.übertragenAn(grundbuch.grundstückVon(Felder.Chaussestraße), testSpieler.get(2));
        grundbuch.übertragenAn(grundbuch.grundstückVon(Felder.Elisenstraße), testSpieler.get(2));
        grundbuch.übertragenAn(grundbuch.grundstückVon(Felder.Poststraße), testSpieler.get(2));
         */


    // TODO überarbeiten, dass ich nicht auf die Strings angewiesen bin sondern mit anzahl von Funktionsaufrufen arbeite
}
