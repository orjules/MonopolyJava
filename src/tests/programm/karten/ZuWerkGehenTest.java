package programm.karten;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programm.grundstücke.Grundbuch;
import programm.grundstücke.Werk;
import programm.system.Felder;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ZuWerkGehenTest {

    String beschreibung = "Testbeschreibung";
    Spielleiter spielleiter = mock(Spielleiter.class);
    Grundbuch grundbuch = mock(Grundbuch.class);
    Spieler spieler1 = mock(Spieler.class);
    Spieler spieler2 = mock(Spieler.class);
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
    }

    @Test
    public void getWerkNochFrei(){
        when(grundbuch.getBesitzerVon(nächstesGrundstück)).thenReturn(null);
        assertEquals(0, zuWerkGehen.getWert());
        assertEquals(beschreibung+"\n"+"Du bist auf dem " + nächstesWerk.name() + " gelandet. Es geöhrt noch niemandem.",
                zuWerkGehen.getBeschreibung());
    }

    @Test
    public void getWerkBesetzt(){
        when(grundbuch.getBesitzerVon(nächstesGrundstück)).thenReturn(spieler2);
        int miete = zuWerkGehen.getWert();
        assertTrue(zuWerkGehen.getWurf()[2]>1 && zuWerkGehen.getWurf()[2]<13);
    }
}