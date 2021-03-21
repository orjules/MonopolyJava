package tests.grundstücke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import programm.grundstücke.*;
import programm.system.spieler.Spieler;

import static org.mockito.Mockito.when;

public class BahnhofTest {

    Grundbuch grundbuch;
    Spieler spieler;
    Bahnhof bahnhof;

    public void initMit(int anzahlBanhöfe){
        grundbuch = Mockito.mock(Grundbuch.class);
        spieler = Mockito.mock(Spieler.class);
        bahnhof = new Bahnhof("bahnhof", null);
        when(grundbuch.bahnhöfeVon(spieler)).thenReturn(anzahlBanhöfe);
    }



    @Test
    public void einBahnhof(){
        initMit(1);
        assertEquals(25, bahnhof.mieteBerechnen(spieler, grundbuch, 0));
    }
    @Test
    public void zweiBahnhof(){
        initMit(2);
        assertEquals(50, bahnhof.mieteBerechnen(spieler, grundbuch, 0));
    }
    @Test
    public void dreiBahnhof(){
        initMit(3);
        assertEquals(100, bahnhof.mieteBerechnen(spieler, grundbuch, 0));
    }
    @Test
    public void vierBahnhof(){
        initMit(4);
        assertEquals(200, bahnhof.mieteBerechnen(spieler, grundbuch, 0));
    }
    @Test
    public void ungültigeBahnhöfe(){
        initMit(5);
        assertThrows(IllegalStateException.class, () -> {bahnhof.mieteBerechnen(spieler, grundbuch,0);});
        initMit(0);
        assertThrows(IllegalStateException.class, () -> {bahnhof.mieteBerechnen(spieler, grundbuch,0);});
    }


}
