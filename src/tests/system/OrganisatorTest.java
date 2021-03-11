package tests.system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programm.system.Organisator2;
import programm.system.Spieler;
import programm.system.Würfel;
import programm.system.Felder;
import tests.system.Mocks.*;

import static org.junit.jupiter.api.Assertions.*;

public class OrganisatorTest {
    DarstellerMock darsteller;
    SpielleiterMock spielleiter;
    GrundbuchMock testBuch;
    KartenmanagerMock testManager;
    Organisator2 organisator2;

    @BeforeEach
    public void setUp(){
        spielleiter = new SpielleiterMock();
        darsteller = new DarstellerMock();
        Würfel würfel = new Würfel();
        testBuch = new GrundbuchMock();
        testManager = new KartenmanagerMock();
        organisator2 = new Organisator2(spielleiter, darsteller, würfel, testBuch, testManager);
    }

    @Test
    public void landetAufNeuemGrundstück(){
        // Grundstück wird erstellt, Ereignisfeld ist null
        GrundstückMock testGrund = new GrundstückMock(Felder.Turmstraße, 100);
        testBuch.init(testGrund, true);
        Spieler testSpieler = new Spieler("Günther", '#', Felder.Turmstraße, false, 1500);
        spielleiter.init(testSpieler);
        darsteller.init("w");

        organisator2.feldAbarbeiten();
        assertEquals("Debug: Endpunkt, Kaufen passiert hier.", darsteller.getAusgabeErgebis());
    }

    @Test
    public void landetAufBesetztemGrundstück(){
        // Grundstück wird erstellt, Ereignisfeld ist null
        GrundstückMock testGrund = new GrundstückMock(Felder.Turmstraße, 100);
        testBuch.init(testGrund, false);
        Spieler testSpieler = new Spieler("Günther", '#', Felder.Turmstraße, false, 1500);
        spielleiter.init(testSpieler);
        darsteller.init("w");

        organisator2.feldAbarbeiten();
        assertEquals("Debug: Endpunkt, Miete zahlen passiert hier.", darsteller.getAusgabeErgebis());
    }

    @Test
    public void landetAufEreignisfeld(){
        // Grundstück wird erstellt, Ereignisfeld ist null
        EreigniskarteMock testkarte = new EreigniskarteMock();
        testManager.init(testkarte);
        Spieler testSpieler = new Spieler("Günther", '#', Felder.Turmstraße, false, 1500);
        spielleiter.init(testSpieler);
        darsteller.init("w");

        organisator2.feldAbarbeiten();
        assertEquals("Dies ist eine Testkarte.", darsteller.getAusgabeErgebis());
    }

    @Test
    public void landetAufFreiemFeld(){
        // Grundstück wird erstellt, Ereignisfeld ist null aber Feld ist kein Grundstück
        Spieler testSpieler = new Spieler("Günther", '#', Felder.Los, false, 1500);
        spielleiter.init(testSpieler);
        darsteller.init("w");

        organisator2.feldAbarbeiten();
        assertEquals("Du bist auf " + Felder.Los.name() + " gelandet. Hier passiert nichts weiter.", darsteller.getAusgabeErgebis());
    }

    @Test
    public void gameloopAufNeuemGrundstück(){
        // Grundstück wird erstellt, Ereignisfeld ist null
        GrundstückMock testGrund = new GrundstückMock(Felder.Turmstraße, 100);
        testBuch.init(testGrund, true);
        Spieler testSpieler = new Spieler("Günther", '#', Felder.Turmstraße, false, 1500);
        spielleiter.init(testSpieler);
        spielleiter.setSpielLäuft(false);
        darsteller.init("w");

        organisator2.gameLoop();
        assertEquals("Debug: Endpunkt, Kaufen passiert hier.", darsteller.getAusgabeErgebis());
    }

    @Test
    public void gameloopAufBesetztemGrundstück(){
        // Grundstück wird erstellt, Ereignisfeld ist null
        GrundstückMock testGrund = new GrundstückMock(Felder.Turmstraße, 100);
        testBuch.init(testGrund, false);
        Spieler testSpieler = new Spieler("Günther", '#', Felder.Turmstraße, false, 1500);
        spielleiter.init(testSpieler);
        spielleiter.setSpielLäuft(false);
        darsteller.init("w");

        organisator2.gameLoop();
        assertEquals("Debug: Endpunkt, Miete zahlen passiert hier.", darsteller.getAusgabeErgebis());
    }

    @Test
    public void gameloopAufEreignisfeld(){
        // Grundstück wird erstellt, Ereignisfeld ist null
        EreigniskarteMock testkarte = new EreigniskarteMock();
        testManager.init(testkarte);
        Spieler testSpieler = new Spieler("Günther", '#', Felder.Turmstraße, false, 1500);
        spielleiter.init(testSpieler);
        spielleiter.setSpielLäuft(false);
        darsteller.init("w");

        organisator2.gameLoop();
        assertEquals("Dies ist eine Testkarte.", darsteller.getAusgabeErgebis());
    }

    @Test
    public void gameloopAufFreiemFeld(){
        // Grundstück wird erstellt, Ereignisfeld ist null aber Feld ist kein Grundstück
        Spieler testSpieler = new Spieler("Günther", '#', Felder.Los, false, 1500);
        spielleiter.init(testSpieler);
        spielleiter.setSpielLäuft(false);
        darsteller.init("w");

        organisator2.gameLoop();
        assertEquals("Du bist auf " + Felder.Los.name() + " gelandet. Hier passiert nichts weiter.", darsteller.getAusgabeErgebis());
    }

}
