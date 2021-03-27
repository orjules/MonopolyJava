package programm.karten;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programm.grundstücke.Grundbuch;
import programm.grundstücke.Werk;
import programm.system.Felder;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

class ZuWerkGehenTest {

    String beschreibung = "Testbeschreibung";
    Spielleiter spielleiter = mock(Spielleiter.class);
    Grundbuch grundbuch = mock(Grundbuch.class);
    Spieler spieler1 = mock(Spieler.class);
    String spieler1Name = "spieler1 (!)";
    Spieler spieler2 = mock(Spieler.class);
    String spieler2Name = "spieler2 (?)";
    Felder aktuellePosDavor = Felder.Los;
    Felder aktuellePosDahinter = Felder.Westbahnhof;
    Felder nächstesWerk = Felder.Elektrizitätswerk;
    Werk nächstesGrundstück = mock(Werk.class);

    ZuWerkGehen zuWerkGehen;

    @BeforeEach
    public void init(){
        zuWerkGehen = new ZuWerkGehen(beschreibung, spielleiter, grundbuch);
        when(spielleiter.getGeradeDran()).thenReturn(spieler1);
        when(spieler1.getAktuellePos()).thenReturn(aktuellePosDavor);
        when(grundbuch.grundstückVon(nächstesWerk)).thenReturn(nächstesGrundstück);
        when(spieler1.toString()).thenReturn(spieler1Name);
        when(spieler2.toString()).thenReturn(spieler2Name);
    }

    @Test
    public void zuWerkNochFreiOhneLos(){
        when(grundbuch.getBesitzerVon(nächstesGrundstück)).thenReturn(null);
        // Miete ist null
        assertEquals(0, zuWerkGehen.getWert());
        // Korrekter String erstellt "Du wirst landen auf, sie gehört niemandem"
        assertEquals(beschreibung+"\n"+"Du wirst auf dem " + nächstesWerk.name() + " landen. " +
                "Es gehört noch niemandem.", zuWerkGehen.getBeschreibung());

        // Bei Bestätigung auf Feld springen ohne was anderes zu machen (dann weiter mit kaufen auf Feld)
        zuWerkGehen.bestätigen();
        verify(spielleiter, times(1)).spielerSetzten(nächstesWerk);
        verify(spielleiter, times(1)).kapitalÄndernVon(spieler1, 0);
        // Korrekte Bestätigung bekommen
        assertEquals(spieler1Name+" ist nun auf dem "+nächstesWerk.name()+".", zuWerkGehen.getBestätigung());
        // Karte wurde resettet
        assertNull(zuWerkGehen.getBesitzer());
    }

    @Test
    public void zuWerkNochFreiMitLos(){
        when(spieler1.getAktuellePos()).thenReturn(aktuellePosDahinter);
        when(grundbuch.getBesitzerVon(nächstesGrundstück)).thenReturn(null);
        // Miete ist null
        assertEquals(0, zuWerkGehen.getWert());
        // Korrekter String erstellt "Du wirst landen auf, du wirst 200 einziehen, sie gehört niemandem"
        assertEquals(beschreibung+"\n"+"Du wirst über Los gehen und 200€ einziehen. Du wirst auf dem " + nächstesWerk.name() + " landen. " +
                "Es gehört noch niemandem.", zuWerkGehen.getBeschreibung());

        // Bei Bestätigung auf Feld springen und 200 geben (dann weiter mit kaufen auf Feld)
        zuWerkGehen.bestätigen();
        verify(spielleiter, times(1)).spielerSetzten(nächstesWerk);
        verify(spielleiter, times(1)).kapitalÄndernVon(spieler1, 200);
        verify(spielleiter, times(1)).kapitalÄndernVon(spieler1, 0);
        // Korrekte Bestätigung bekommen
        assertEquals("Du bist über Los geganen und hast 200€ eingezogen. "+spieler1Name+" ist nun auf dem "
                +nächstesWerk.name()+".", zuWerkGehen.getBestätigung());
        // Karte wurde resettet
        assertNull(zuWerkGehen.getBesitzer());
    }

    @Test
    public void zuWerkBesetztOhneLos(){
        when(grundbuch.getBesitzerVon(nächstesGrundstück)).thenReturn(spieler2);
        // Miete ist wurf * 10
        int miete = zuWerkGehen.getWert();
        int gesamtWurf = zuWerkGehen.getWurf()[2];
        assertEquals(gesamtWurf*10, miete);
        // Korrekter String erstellt
        assertEquals(beschreibung+"\n"+"Du wirst auf dem "+nächstesWerk.name()+" landen, welches "+spieler2Name+
                " gehört. Weil du eine "+gesamtWurf+" gewürfelt hast, musst du "+miete+" zahlen.",
                zuWerkGehen.getBeschreibung());
        // Bei Bestätigung wird Geld übertragen und auf Feld gesprungen
        zuWerkGehen.bestätigen();
        verify(spielleiter, times(1)).kapitalÄndernVon(spieler1, -miete);
        verify(spielleiter, times(1)).kapitalÄndernVon(spieler2, miete);
        verify(spielleiter, times(1)).spielerSetzten(nächstesWerk);
        // Korrekte Bestätigung bekommen
        assertEquals(spieler1Name + " ist auf dem " + nächstesWerk.name() + " von " + spieler2Name +
                " gelandet und hat " + miete + "€ gezahlt.", zuWerkGehen.getBestätigung());
        // Karte wurde resettet
        assertNull(zuWerkGehen.getBesitzer());
    }

    @Test
    public void zuWerkBesetztMitLos(){
        when(grundbuch.getBesitzerVon(nächstesGrundstück)).thenReturn(spieler2);
        when(spieler1.getAktuellePos()).thenReturn(aktuellePosDahinter);
        // Miete ist wurf * 10
        int miete = zuWerkGehen.getWert();
        int gesamtWurf = zuWerkGehen.getWurf()[2];
        assertEquals(gesamtWurf*10, miete);
        // Korrekter String "Du wirst landen auf, du wirst 200 einziehen, sie gehört..., miete ist..."
        assertEquals(beschreibung+"\n"+"Du wirst über Los gehen und 200€ einziehen. Du wirst auf dem "+
                        nächstesWerk.name()+" landen, welches "+spieler2Name+ " gehört. Weil du eine "+gesamtWurf+
                        " gewürfelt hast, musst du "+miete+" zahlen.", zuWerkGehen.getBeschreibung());
        // Bei Bestätigung wird 200 übertragen, dann springen und dann miete abziehen
        zuWerkGehen.bestätigen();
        verify(spielleiter, times(1)).kapitalÄndernVon(spieler1, 200);
        verify(spielleiter, times(1)).kapitalÄndernVon(spieler1, -miete);
        verify(spielleiter, times(1)).kapitalÄndernVon(spieler2, miete);
        verify(spielleiter, times(1)).spielerSetzten(nächstesWerk);
        // Korrekte Bestätigung bekommen
        assertEquals("Du bist über Los geganen und hast 200€ eingezogen. "+spieler1Name + " ist auf dem " + nächstesWerk.name() + " von " + spieler2Name +
                " gelandet und hat " + miete + "€ gezahlt.", zuWerkGehen.getBestätigung());
        // Karte wurde resettet
        assertNull(zuWerkGehen.getBesitzer());
    }
}