package tests.system;

import org.junit.jupiter.api.Test;
import programm.system.Organisator2;
import programm.system.Spieler;
import programm.system.Würfel;
import programm.system.enums.Felder;
import tests.system.Mocks.*;

import static org.junit.jupiter.api.Assertions.*;

public class OrganisatorTest {
    DarstellerMock darsteller;


    @Test
    public void landetAufNeuemGrundstück(){
        // Grundstück wird erstellt, Ereignisfeld ist null
        GrundstückMock testGrund = new GrundstückMock();
        Spieler testSpieler = new Spieler("Günther", '#', Felder.Turmstraße, false, 1500);
        Organisator2 testOrg = setUp(testSpieler, testGrund, true, null, "w");
        testOrg.feldAbarbeiten();
        assertEquals("Debug: Endpunkt, Kaufen passiert hier.", darsteller.getAusgabeErgebis());
    }

    @Test
    public void landetAufBesetztemGrundstück(){
        // Grundstück wird erstellt, Ereignisfeld ist null
        GrundstückMock testGrund = new GrundstückMock();
        Spieler testSpieler = new Spieler("Günther", '#', Felder.Turmstraße, false, 1500);
        Organisator2 testOrg = setUp(testSpieler, testGrund, false, null, "w");
        testOrg.feldAbarbeiten();
        assertEquals("Debug: Endpunkt, Miete zahlen passiert hier.", darsteller.getAusgabeErgebis());
    }

    @Test
    public void landetAufEreignisfeld(){
        // Ereignisfeld wird erstellt, Grundstück ist null
        EreigniskarteMock testkarte = new EreigniskarteMock();
        Spieler testSpieler = new Spieler("Günther", '#', Felder.Ereignisfeld1, false, 1500);
        Organisator2 testOrg = setUp(testSpieler, null, false, testkarte, "w");
        testOrg.feldAbarbeiten();
        assertEquals("Dies ist eine Testkarte.", darsteller.getAusgabeErgebis());
    }

    @Test
    public void landetAufFreiemFeld(){
        // Grundstück wird erstellt, Ereignisfeld ist null
        GrundstückMock testGrund = new GrundstückMock();
        Spieler testSpieler = new Spieler("Günther", '#', Felder.Los, false, 1500);
        Organisator2 testOrg = setUp(testSpieler, testGrund, false, null, "w");
        testOrg.feldAbarbeiten();
        assertEquals("Du bist auf " + Felder.Los.name() + " gelandet. Hier passiert nichts weiter.", darsteller.getAusgabeErgebis());
    }

    private Organisator2 setUp(Spieler geradeDran, GrundstückMock testGrund, Boolean istZuverkaufen,
                               EreigniskarteMock karte, String festeEingabe){
        SpielleiterMock spielleiter = new SpielleiterMock(geradeDran);
        darsteller = new DarstellerMock(festeEingabe);
        Würfel würfel = new Würfel();
        GrundbuchMock testBuch = new GrundbuchMock(testGrund, istZuverkaufen);
        KartenmanagerMock testManager = new KartenmanagerMock(karte);
        return new Organisator2(spielleiter, darsteller, würfel, testBuch, testManager);
    }
}
