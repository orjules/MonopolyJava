package programm.karten;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import programm.system.Felder;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

import static org.mockito.Mockito.*;

class FeldGehenTest {

    FeldGehen gehenNormal;
    Felder zielNormal = Felder.Chaussestraße;
    FeldGehen gehenGefängnis;
    Felder zielGefängnis = Felder.Gefängnis_bzw_Besuch;
    Felder startWeit = Felder.Seestraße;

    Spieler spieler = mock(Spieler.class);
    String spielerName = "spieler (?)";
    Felder altesFeld = Felder.Los;
    Spielleiter spielleiter = mock(Spielleiter.class);
    String beschreibung = "Testbeschreibung";

    @BeforeEach
    public void init(){
        gehenNormal = new FeldGehen(beschreibung, spielleiter, zielNormal);
        gehenGefängnis = new FeldGehen(beschreibung, spielleiter, zielGefängnis, true);

        when(spielleiter.getGeradeDran()).thenReturn(spieler);
        when(spieler.toString()).thenReturn(spielerName);
    }

    @Test
    public void aktionAusführenNormal(){
        when(spieler.getAktuellePos()).thenReturn(altesFeld);
        gehenNormal.aktionAusführen();
        verify(spielleiter, times(1)).spielerSetzten(zielNormal);
        verify(spielleiter, times(0)).kapitalÄndernVon(spieler, 200);
    }

    @Test
    public void aktionAusführenÜberLos(){
        when(spieler.getAktuellePos()).thenReturn(startWeit);
        gehenNormal.aktionAusführen();
        verify(spielleiter, times(1)).spielerSetzten(zielNormal);
        verify(spielleiter, times(1)).kapitalÄndernVon(spieler, 200);
    }

    @Test
    public void aktionAusführenGefängnis(){
        when(spieler.getAktuellePos()).thenReturn(altesFeld);
        gehenGefängnis.aktionAusführen();
        verify(spielleiter, times(1)).spielerSetzten(zielGefängnis);
        verify(spielleiter, times(0)).kapitalÄndernVon(spieler, 200);
    }

    @Test
    public void bestätigungGefängnis(){
        when(spieler.getAktuellePos()).thenReturn(altesFeld);
        gehenGefängnis.aktionAusführen();
        when(spieler.getAktuellePos()).thenReturn(zielGefängnis);
        assertEquals(spielerName + " sitzt nun im Gefängnis.", gehenGefängnis.getBestätigung());
    }

    @Test
    public void bestätigungÜberLos(){
        when(spieler.getAktuellePos()).thenReturn(startWeit);
        gehenNormal.aktionAusführen();
        when(spieler.getAktuellePos()).thenReturn(zielNormal);
        assertEquals(spielerName + " ist über Los gegangen und hat 200€ eingezogen.", gehenNormal.getBestätigung());
    }

    @Test
    public void bestätigungNormal(){
        when(spieler.getAktuellePos()).thenReturn(altesFeld);
        gehenNormal.aktionAusführen();
        when(spieler.getAktuellePos()).thenReturn(zielNormal);
        assertEquals(spielerName + " ist nun auf " + zielNormal.name() + ".", gehenNormal.getBestätigung());
    }

    @Test
    public void bestätigungOhneVorherLaufen(){
        when(spieler.getAktuellePos()).thenReturn(altesFeld);
        assertThrows(IllegalStateException.class, ()->{
            gehenNormal.getBestätigung();
        });
        assertThrows(IllegalStateException.class, ()->{
            gehenGefängnis.getBestätigung();
        });
    }

    @Test
    public void zweiBestätigungenFürEinmalLaufen(){
        when(spieler.getAktuellePos()).thenReturn(altesFeld);
        gehenNormal.aktionAusführen();
        when(spieler.getAktuellePos()).thenReturn(zielNormal);
        assertEquals(spielerName + " ist nun auf " + zielNormal.name() + ".", gehenNormal.getBestätigung());

        assertThrows(IllegalStateException.class, ()->{
            gehenNormal.getBestätigung();
        });
    }

    @Test
    public void zweiBestätigungenFürZweimalLaufen(){
        when(spieler.getAktuellePos()).thenReturn(altesFeld);
        gehenNormal.aktionAusführen();
        when(spieler.getAktuellePos()).thenReturn(zielNormal);
        assertEquals(spielerName + " ist nun auf " + zielNormal.name() + ".", gehenNormal.getBestätigung());
        when(spieler.getAktuellePos()).thenReturn(altesFeld);
        gehenNormal.aktionAusführen();
        when(spieler.getAktuellePos()).thenReturn(zielNormal);
        assertEquals(spielerName + " ist nun auf " + zielNormal.name() + ".", gehenNormal.getBestätigung());
    }
}