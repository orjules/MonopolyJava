package programm.karten;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programm.system.Darsteller;
import programm.system.Felder;
import programm.system.core.Org_Hilfe;
import programm.system.spieler.Spielleiter;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KartenmanagerTest {

    HashMap<Felder, Ereigniskarte> festeKartenMitFeld;
    Ereigniskarte[] randomKarten;
    Org_Hilfe orgHilfe = mock(Org_Hilfe.class);
    Darsteller darsteller = mock(Darsteller.class);
    Spielleiter spielleiter = mock(Spielleiter.class);

    NormaleKarte normalFest = mock(NormaleKarte.class);
    NormaleKarte normalRandom = mock(NormaleKarte.class);

    Kartenmanager kartenmanager;

    private void setUp(){
        festeKartenMitFeld = new HashMap<>();
        festeKartenMitFeld.put(Felder.Einkommenssteuer, normalFest);

        randomKarten = new Ereigniskarte[]{
                normalRandom
        };
        kartenmanager = new Kartenmanager(festeKartenMitFeld, randomKarten, orgHilfe);
    }

    private void setUpSpeziell(Ereigniskarte karte){
        festeKartenMitFeld = new HashMap<>();
        randomKarten = new Ereigniskarte[]{
                karte
        };
        kartenmanager = new Kartenmanager(festeKartenMitFeld, randomKarten, orgHilfe);
    }


    @Test
    public void istKeinKartenfeld(){
        setUp();
        assertFalse(kartenmanager.karteVonFeldBearbeitet(Felder.Badstraße, darsteller, spielleiter));
    }


    @Test
    public void normaleFesteKarteWirdAusgeführt(){
        setUp();
        assertTrue(kartenmanager.karteVonFeldBearbeitet(Felder.Einkommenssteuer, darsteller, spielleiter));
        verify(normalFest, times(1)).aktionAusführen();
        verify(darsteller, times(1)).brettZeichnen();
        verify(normalFest, times(1)).getBestätigung();
    }

    @Test
    public void normaleRandomKarteWirdAusgeführt(){
        setUp();
        assertTrue(kartenmanager.karteVonFeldBearbeitet(Felder.Ereignisfeld1, darsteller, spielleiter));
        verify(normalRandom, times(1)).aktionAusführen();
        verify(darsteller, times(1)).brettZeichnen();
        verify(normalRandom, times(1)).getBestätigung();
    }

    @Test
    public void zuWerkGehenWirdAusgeführt(){
        ZuWerkGehen zuWerkGehen = mock(ZuWerkGehen.class);
        setUpSpeziell(zuWerkGehen);

        assertTrue(kartenmanager.karteVonFeldBearbeitet(Felder.Ereignisfeld1, darsteller, spielleiter));
        verify(zuWerkGehen, times(1)).bestätigen();
        verify(darsteller, times(1)).brettZeichnen();
        verify(zuWerkGehen, times(1)).getBestätigung();
    }

    @Test
    public void jemandGibtAuf(){
        BankGeld bankGeld = mock(BankGeld.class);
        setUpSpeziell(bankGeld);
        String beschreibung = "Bankbeschreibung";
        when(spielleiter.jemandHatGeradeAufgegeben()).thenReturn(true);
        when(bankGeld.getBeschreibung()).thenReturn(beschreibung);
        when(bankGeld.getWert()).thenReturn(100);

        assertTrue(kartenmanager.karteVonFeldBearbeitet(Felder.Ereignisfeld1, darsteller, spielleiter));
        verify(bankGeld, times(1)).getWert();
        verify(orgHilfe, times(1)).bezahlenOderZuWenigGeld(beschreibung,
                "\n'a' um zu bestätigen", 100);
    }

    // TODO Test für den Fall von Gefängnisfrei
}