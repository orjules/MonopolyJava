package programm.grundstücke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.configuration.IMockitoConfiguration;
import programm.system.spieler.Spieler;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WerkTest {

    Spieler spieler;
    Grundbuch grundbuch;
    Werk testWerk;

    @BeforeEach
    public void init(){
        spieler = mock(Spieler.class);
        grundbuch = mock(Grundbuch.class);
        testWerk = new Werk("werk", null, 0, 0);
    }

    @Test
    public void einWerk(){
        when(grundbuch.beideWerke(spieler)).thenReturn(false);

        assertEquals(24, testWerk.mieteBerechnen(spieler, grundbuch, 6));
    }

    @Test
    public void beideWerke(){
        when(grundbuch.beideWerke(spieler)).thenReturn(true);

        assertEquals(60, testWerk.mieteBerechnen(spieler, grundbuch, 6));
    }

    @Test
    public void illegalerWurf(){
        when(grundbuch.beideWerke(spieler)).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () ->{
            testWerk.mieteBerechnen(spieler, grundbuch, 13);
        });
        assertThrows(IllegalArgumentException.class, () ->{
            testWerk.mieteBerechnen(spieler, grundbuch, 1);
        });
    }

}