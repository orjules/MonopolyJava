package programm.grundstücke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programm.system.brett.Brett;
import programm.system.spieler.Spieler;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WerkTests {

    Spieler spieler;
    Brett brett;
    Werk testWerk;

    @BeforeEach
    public void init(){
        spieler = mock(Spieler.class);
        brett = mock(Brett.class);
        testWerk = new Werk("werk", null, 0, 0);
    }

    @Test
    public void einWerk(){
        when(brett.hatBeideWerke(spieler)).thenReturn(false);

        assertEquals(24, testWerk.mieteBerechnen(spieler, brett, 6));
    }

    @Test
    public void beideWerke(){
        when(brett.hatBeideWerke(spieler)).thenReturn(true);

        assertEquals(60, testWerk.mieteBerechnen(spieler, brett, 6));
    }

    @Test
    public void illegalerWurf(){
        when(brett.hatBeideWerke(spieler)).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () ->{
            testWerk.mieteBerechnen(spieler, brett, 13);
        });
        assertThrows(IllegalArgumentException.class, () ->{
            testWerk.mieteBerechnen(spieler, brett, 1);
        });
    }

}