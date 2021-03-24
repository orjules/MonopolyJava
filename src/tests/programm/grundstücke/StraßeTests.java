package programm.grundstücke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programm.system.spieler.Spieler;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StraßeTests {

    Straße testStraße;
    int mieteAllein = 10;
    int miete4Haus = 100;
    int mieteHotel= 500;

    @BeforeEach
    public void initStraße(){
        testStraße = new Straße("test", null, 0,0,null,mieteAllein,
                0,0,0,miete4Haus,mieteHotel,0);
    }

    @Test
    public void ohneAusbau(){
        assertEquals("Kein Haus", testStraße.getAusbauLevel());
    }

    // Hier fehlt noch die Möglichkeit das AusbauLevel zu beeinflussen
    /*
    @Test
    public void vollerHausAusbau(){
        // AusbauLevel beeinflussen
        assertEquals("4 Häuser", testStraße.getAusbauLevel());
    }

    @Test
    public void hotelAusbau(){
        // AusbauLevel beeinflussen
        assertEquals("Ein Hotel", testStraße.getAusbauLevel());
    }
     */

    @Test
    public void mieteBerechnenOhneHaus(){
        assertEquals(mieteAllein, testStraße.mieteBerechnen(mock(Spieler.class), mock(Grundbuch.class), 0));
    }

    // Hier fehlt noch die Möglichkeit das AusbauLevel zu beeinflussen
    /*
    @Test
    public void mieteBerechnen4Haus(){
        // Auf 4 Häuser hochschrauben
        assertEquals(miete4Haus, testStraße.mieteBerechnen(mock(Spieler.class), mock(Grundbuch.class), 0));
    }

    @Test
    public void mieteBerechnenHotel(){
        // Auf 4 Häuser hochschrauben und Hotel bauen
        assertEquals(mieteHotel, testStraße.mieteBerechnen(mock(Spieler.class), mock(Grundbuch.class), 0));
    }
     */

}