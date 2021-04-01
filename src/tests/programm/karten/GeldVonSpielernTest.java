package programm.karten;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

class GeldVonSpielernTest {

    String beschreibung = "Testbeschreibung";
    Spielleiter spielleiter = mock(Spielleiter.class);
    int wert = 10;
    GeldVonSpielern geldVONSpielern;
    GeldVonSpielern geldANSpieler;

    Spieler[] alleSpieler;
    Spieler spieler1 = mock(Spieler.class);
    Spieler spieler2 = mock(Spieler.class);
    Spieler spieler3 = mock(Spieler.class);
    String spieler1Name = "spieler1 (?)";

    @BeforeEach
    public void intit(){
        geldVONSpielern = new GeldVonSpielern(beschreibung, spielleiter, 10, true);
        geldANSpieler = new GeldVonSpielern(beschreibung, spielleiter, 10, false);
        alleSpieler = new Spieler[]{
                spieler1, spieler2, spieler3
        };
        when(spielleiter.getAlleSpieler()).thenReturn(alleSpieler);
        when(spielleiter.getGeradeDran()).thenReturn(spieler1);
        when(spieler1.toString()).thenReturn(spieler1Name);
    }

    @Test
    public void aktionFürBekommen(){
        geldVONSpielern.aktionAusführen();
        verify(spielleiter, times(1)).kapitalÄndernVon(spieler2, -wert);
        verify(spielleiter, times(1)).kapitalÄndernVon(spieler3, -wert);
        verify(spielleiter, times(1)).kapitalÄndernVon(spieler1, 2 * wert);
    }

    @Test
    public void aktionFürZahlen(){
        geldANSpieler.aktionAusführen();
        verify(spielleiter, times(1)).kapitalÄndernVon(spieler2, wert);
        verify(spielleiter, times(1)).kapitalÄndernVon(spieler3, wert);
        verify(spielleiter, times(1)).kapitalÄndernVon(spieler1, -2 * wert);
    }

    @Test
    public void bestätigungFürBekommen(){
        geldVONSpielern.aktionAusführen();
        assertEquals(spieler1Name + " hat insgesamt " + (2*wert) + "€ bekommen.", geldVONSpielern.getBestätigung());
    }

    @Test
    public void bestätigungFürZahlen(){
        geldANSpieler.aktionAusführen();
        assertEquals(spieler1Name + " hat insgesamt " + (-2*wert) + "€ bezahlt.", geldANSpieler.getBestätigung());    }

    @Test
    public void beschreibungOhneAktion(){
        assertThrows(IllegalStateException.class, ()->geldVONSpielern.getBestätigung());
        assertThrows(IllegalStateException.class, ()->geldANSpieler.getBestätigung());
    }
}