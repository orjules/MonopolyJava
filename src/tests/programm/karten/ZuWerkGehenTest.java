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

    int miete;
    int gesamtWurf;

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
        setUp(null, false);

        mieteIst0();
        korrekteBeschreibungWirdErstellt(beschreibung+"\n"+"Du wirst auf dem " + nächstesWerk.name()
                + " landen. Es gehört noch niemandem.");
        beiBestätigungSetzenMit0(false);
        korrekteBestätigungBeiFrei(false);
        korrektesReset();
    }

    @Test
    public void zuWerkNochFreiMitLos(){
        setUp(null, true);

        mieteIst0();
        korrekteBeschreibungWirdErstellt(beschreibung+"\n"+"Du wirst über Los gehen und 200€ einziehen. " +
                "Du wirst auf dem " + nächstesWerk.name() + " landen. " + "Es gehört noch niemandem.");

        beiBestätigungSetzenMit0(true);
        korrekteBestätigungBeiFrei(true);
        korrektesReset();
    }

    @Test
    public void zuWerkBesetztOhneLos(){
        setUp(spieler2, false);

        mieteIst10malWurf();
        korrekteBeschreibungWirdErstellt(beschreibung+"\n"+"Du wirst auf dem "+nächstesWerk.name()+
                " landen, welches "+spieler2Name+ " gehört. Weil du eine "+gesamtWurf+" gewürfelt hast, musst du "
                +miete+" zahlen.");
        beiBestätigungSetzenMitMiete(false);
        korrekteBestätigungBesetzt(false);
        korrektesReset();
    }

    @Test
    public void zuWerkBesetztMitLos(){
        setUp(spieler2, true);

        mieteIst10malWurf();
        korrekteBeschreibungWirdErstellt(beschreibung+"\n"+"Du wirst über Los gehen und 200€ einziehen. " +
                "Du wirst auf dem "+ nächstesWerk.name()+" landen, welches "+spieler2Name+ " gehört. Weil du eine "+
                gesamtWurf+ " gewürfelt hast, musst du "+miete+" zahlen.");
        beiBestätigungSetzenMitMiete(true);
        korrekteBestätigungBesetzt(true);
        korrektesReset();
    }

    private void setUp(Spieler spieler, boolean mitLos){
        if (mitLos)
            when(spieler1.getAktuellePos()).thenReturn(aktuellePosDahinter);
        when(grundbuch.getBesitzerVon(nächstesGrundstück)).thenReturn(spieler);
    }
    private void mieteIst0(){
        assertEquals(0, zuWerkGehen.getWert());
    }
    private void korrekteBeschreibungWirdErstellt(String erwartet){
        assertEquals(erwartet, zuWerkGehen.getBeschreibung());
    }
    private void mieteIst10malWurf(){
        miete = zuWerkGehen.getWert();
        gesamtWurf = zuWerkGehen.getWurf()[2];
        assertEquals(gesamtWurf*10, miete);
    }
    private void beiBestätigungSetzenMit0(boolean mitLos){
        zuWerkGehen.bestätigen();
        if (mitLos)
            verify(spielleiter, times(1)).kapitalÄndernVon(spieler1, 200);
        verify(spielleiter, times(1)).spielerSetzten(nächstesWerk);
        verify(spielleiter, times(1)).kapitalÄndernVon(spieler1, 0);
    }
    private void beiBestätigungSetzenMitMiete(boolean mitLos){
        zuWerkGehen.bestätigen();
        if (mitLos)
            verify(spielleiter, times(1)).kapitalÄndernVon(spieler1, 200);
        verify(spielleiter, times(1)).kapitalÄndernVon(spieler1, -miete);
        verify(spielleiter, times(1)).kapitalÄndernVon(spieler2, miete);
        verify(spielleiter, times(1)).spielerSetzten(nächstesWerk);
    }
    private void korrekteBestätigungBeiFrei(boolean mitLos){
        if (mitLos){
            assertEquals("Du bist über Los geganen und hast 200€ eingezogen. "+spieler1Name+" ist nun auf dem "
                    +nächstesWerk.name()+".", zuWerkGehen.getBestätigung());
        }else
            assertEquals(spieler1Name+" ist nun auf dem "+nächstesWerk.name()+".", zuWerkGehen.getBestätigung());
    }
    private void korrekteBestätigungBesetzt(boolean mitLos){
        if (mitLos){
            assertEquals("Du bist über Los geganen und hast 200€ eingezogen. "+spieler1Name + " ist auf dem "
                            + nächstesWerk.name() + " von " + spieler2Name + " gelandet und hat " + miete
                            + "€ gezahlt.", zuWerkGehen.getBestätigung());
        }else
            assertEquals(spieler1Name + " ist auf dem " + nächstesWerk.name() + " von " + spieler2Name +
                    " gelandet und hat " + miete + "€ gezahlt.", zuWerkGehen.getBestätigung());
    }
    private void korrektesReset(){
        assertNull(zuWerkGehen.getBesitzer());
    }
}