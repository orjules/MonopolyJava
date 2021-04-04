package programm.statemashine.states;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programm.grundstücke.Grundbuch;
import programm.grundstücke.Grundstück;
import programm.karten.Ereigniskarte;
import programm.karten.Kartenmanager;
import programm.statemashine.Kontext;
import programm.statemashine.io.*;
import programm.statemashine.enums.FehlerMeldungen;
import programm.system.Felder;
import programm.system.Würfel;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ErsterWurfTest {

    Kontext kontext = mock(Kontext.class);
    Spielleiter spielleiter = mock(Spielleiter.class);
    Spieler spieler1 = mock(Spieler.class);
    Spieler spieler2 = mock(Spieler.class);
    Würfel würfel = mock(Würfel.class);
    Grundbuch grundbuch = mock(Grundbuch.class);
    Grundstück grundstück = mock(Grundstück.class);
    Kartenmanager kartenmanager = mock(Kartenmanager.class);
    Ereigniskarte karte = mock(Ereigniskarte.class);
    Felder freiesGrundstückFeld = Felder.Badstraße;
    Felder besetzesGrundstückFeld = Felder.Turmstraße;
    Felder karteZiehenFeld = Felder.Ereignisfeld1;
    FreiesGrundstück freiesGrundstück = mock(FreiesGrundstück.class);
    BesetzesGrundstück besetzesGrundstück = mock(BesetzesGrundstück.class);
    KarteZiehen karteZiehen = mock(KarteZiehen.class);

    int[] wurfOhnePasch = new int[]{2,3,5};

    ErsterWurf ersterWurf;

    @BeforeEach
    public void init(){
        when(spielleiter.getGeradeDran()).thenReturn(spieler1);
        ersterWurf = new ErsterWurf(kontext, spielleiter, würfel, grundbuch, kartenmanager);
    }

    @Test
    public void übersichtIstNichtErlaubt(){
        gehtNichtWurdeKorrektErstellt(ersterWurf.übersicht());
    }
    @Test
    public void bestätigenIstNichtErlaubt(){
        gehtNichtWurdeKorrektErstellt(ersterWurf.bestätigen());
    }
    @Test
    public void zurückIstNichtErlaubt(){
        gehtNichtWurdeKorrektErstellt(ersterWurf.zurück());
    }
    private void gehtNichtWurdeKorrektErstellt(NeuesAusgabeModell eigentlicheAusgabe){
        assertEquals(FehlerMeldungen.gehtGeradeNicht, eigentlicheAusgabe.getFehlerMeldung());
        assertEquals(spieler1, eigentlicheAusgabe.getGeradeDran());
    }


    @Test
    public void werfenBisFreiesGrundstück(){
        when(kontext.getFreiesGrundstück()).thenReturn(freiesGrundstück);
        when(würfel.würfeln()).thenReturn(wurfOhnePasch);
        when(spieler1.getAktuellePos()).thenReturn(freiesGrundstückFeld);
        when(grundbuch.grundstückVon(freiesGrundstückFeld)).thenReturn(grundstück);
        when(grundbuch.getBesitzerVon(grundstück)).thenReturn(null);

        NeuesAusgabeModell eigentlicheAusgabe = ersterWurf.werfen();

        ArrayList<Ausgabe> ausgaben = eigentlicheAusgabe.getAusgaben();
        assertTrue(ausgaben.get(0) instanceof A_Gewürfelt);
        assertTrue(ausgaben.get(1) instanceof A_FreiesGrundstück);
        assertEquals(spieler1, eigentlicheAusgabe.getGeradeDran());
        verify(spielleiter, times(1)).spielerBewegen(wurfOhnePasch[2]);
        verify(kontext, times(1)).setAktuellerState(freiesGrundstück);
    }
    @Test
    public void werfenBisBesetztesGrundstück(){
        when(kontext.getBesetzesGrundstück()).thenReturn(besetzesGrundstück);
        when(würfel.würfeln()).thenReturn(wurfOhnePasch);
        when(spieler1.getAktuellePos()).thenReturn(besetzesGrundstückFeld);
        when(grundbuch.grundstückVon(besetzesGrundstückFeld)).thenReturn(grundstück);
        when(grundbuch.getBesitzerVon(grundstück)).thenReturn(spieler2);

        NeuesAusgabeModell eigentlicheAusgabe = ersterWurf.werfen();

        ArrayList<Ausgabe> ausgaben = eigentlicheAusgabe.getAusgaben();
        assertTrue(ausgaben.get(0) instanceof A_Gewürfelt);
        assertTrue(ausgaben.get(1) instanceof A_BesetztesGrundstück);
        assertEquals(spieler1, eigentlicheAusgabe.getGeradeDran());
        verify(spielleiter, times(1)).spielerBewegen(wurfOhnePasch[2]);
        verify(kontext, times(1)).setAktuellerState(besetzesGrundstück);
    }
    @Test
    public void werfenBisKarte(){
        when(kontext.getKarteZiehen()).thenReturn(karteZiehen);
        when(würfel.würfeln()).thenReturn(wurfOhnePasch);
        when(spieler1.getAktuellePos()).thenReturn(karteZiehenFeld);
        when(grundbuch.grundstückVon(besetzesGrundstückFeld)).thenReturn(null);
        when(kartenmanager.karteZiehen(karteZiehenFeld)).thenReturn(karte);

        NeuesAusgabeModell eigentlicheAusgabe = ersterWurf.werfen();

        ArrayList<Ausgabe> ausgaben = eigentlicheAusgabe.getAusgaben();
        assertTrue(ausgaben.get(0) instanceof A_Gewürfelt);
        assertTrue(ausgaben.get(1) instanceof A_KarteZiehen);
        assertEquals(spieler1, eigentlicheAusgabe.getGeradeDran());
        verify(kontext, times(1)).setAktuellerState(karteZiehen);
    }

    // Werfen beim dritten Pasch Test schreiben

}