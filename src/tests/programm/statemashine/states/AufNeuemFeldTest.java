package programm.statemashine.states;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programm.statemashine.Kontext;
import programm.statemashine.io.AusgabeModell;
import programm.system.Würfel;
import programm.system.brett.Brett;
import programm.system.spieler.Spielleiter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AufNeuemFeldTest {

    Kontext kontext = mock(Kontext.class);
    Würfel würfel = mock(Würfel.class);
    Spielleiter spielleiter = mock(Spielleiter.class);
    Brett brett = mock(Brett.class);

    AusgabeModell letztesAusgabeModell = mock(AusgabeModell.class);

    AufNeuemFeld aufNeuemFeld;

    @BeforeEach
    public void init(){
        aufNeuemFeld = new AufNeuemFeld(kontext, würfel, spielleiter, brett);
        when(kontext.getLetzteAusgabe()).thenReturn(letztesAusgabeModell);
    }

    @Test
    public void werfenGehtNicht(){
        assertEquals(letztesAusgabeModell, aufNeuemFeld.werfen());
    }

    @Test
    public void bestätigenBeiFrei(){

        assertTrue(false);
    }

    @Test
    public void bestätigenBeiBesetzt(){
        assertTrue(false);
    }

    @Test
    public void bestätigenBeiEigenem(){
        assertTrue(false);
    }

    @Test
    public void bestätigenBeiKarte(){
        assertTrue(false);
    }

    @Test
    public void übersicht(){
        assertTrue(false);
    }

    @Test
    public void zurückBeiFreiem(){
        assertTrue(false);
    }

    @Test
    public void zurückSonst(){
        assertTrue(false);
    }

}