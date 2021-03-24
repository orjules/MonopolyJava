package programm.grundstücke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import programm.system.Felder;
import programm.system.spieler.Spieler;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GrundbuchTests {

    Grundbuch grundbuch;

    Spieler spieler1 = mock(Spieler.class);
    Spieler spieler2 = mock(Spieler.class);

    Felder feldVonStraße = Felder.Turmstraße;
    Straße straße = mock(Straße.class);
    Felder feldVonBahnhof = Felder.Südbahnhof;
    Bahnhof bahnhof = mock(Bahnhof.class);
    Felder feldVonWerk = Felder.Elektrizitätswerk;
    Werk werk = mock(Werk.class);
    Werk werk2 = mock(Werk.class);

    Farbgruppen farbgruppe = mock(Farbgruppen.class);

    Grundstück[] alleGrundstücke;


    private Map<Grundstück, Spieler> erwarteteMapErstellen(Spieler fürStraße, Spieler fürBahnhof, Spieler fürWerk, Spieler fürWerk2){
        Map<Grundstück, Spieler> erwartet = new HashMap<>();
        erwartet.put(straße, fürStraße);
        erwartet.put(bahnhof, fürBahnhof);
        erwartet.put(werk, fürWerk);
        erwartet.put(werk2, fürWerk2);
        return erwartet;
    }

    private boolean arraysSindGleich(Grundstück[] erstes, Grundstück[] zweites){
        außenloop: for (Grundstück g : erstes){
            for (Grundstück f : zweites){
                if (g.equals(f))
                    continue außenloop;
            }
            return false;
        }
        return true;
    }

    @BeforeEach
    public void init(){
        alleGrundstücke = new Grundstück[]{
                straße,
                bahnhof,
                werk,
                werk2
        };
        Farbgruppen[] alleFarbgruppen = new Farbgruppen[]{farbgruppe};
        grundbuch = new Grundbuch(alleGrundstücke, alleFarbgruppen);

        when(straße.getFeld()).thenReturn(feldVonStraße);
        when(bahnhof.getFeld()).thenReturn(feldVonBahnhof);
        when(werk.getFeld()).thenReturn(feldVonWerk);
        when(werk2.getFeld()).thenReturn(feldVonWerk);
    }


    @Test
    public void einmalÜbertragenAnSpieler(){
        grundbuch.übertragenAn(straße, spieler1);
        assertEquals(erwarteteMapErstellen(spieler1, null, null, null), grundbuch.getGrundbuch());
    }
    @Test
    public void allesÜbertragenAnSpieler(){
        grundbuch.übertragenAn(straße, spieler1);
        grundbuch.übertragenAn(bahnhof, spieler1);
        grundbuch.übertragenAn(werk, spieler1);
        grundbuch.übertragenAn(werk2, spieler1);
        assertEquals(erwarteteMapErstellen(spieler1, spieler1, spieler1, spieler1), grundbuch.getGrundbuch());
    }
    @Test
    public void beiVollNochmalÜbertragen(){
        grundbuch.übertragenAn(straße, spieler1);
        grundbuch.übertragenAn(bahnhof, spieler1);
        grundbuch.übertragenAn(werk, spieler1);
        grundbuch.übertragenAn(werk2, spieler1);
        grundbuch.übertragenAn(straße, spieler2);
        assertEquals(erwarteteMapErstellen(spieler2, spieler1, spieler1, spieler1), grundbuch.getGrundbuch());
    }

    @Test
    public void grundstückDasExistiert(){
        assertEquals(straße, grundbuch.grundstückVon(feldVonStraße));
    }
    @Test
    public void grundstückDasNichtExistiert(){
        assertNull(grundbuch.grundstückVon(Felder.Ereignisfeld1));
    }

    @Test
    public void bestitzerVonFreiemGrundstück(){
        assertNull(grundbuch.getBesitzerVon(straße));
    }
    @Test
    public void bestitzerVonBesetztemGrundstück(){
        grundbuch.übertragenAn(straße, spieler1);
        assertEquals(spieler1, grundbuch.getBesitzerVon(straße));
    }
    @Test
    public void bestitzerVonGrundstückDasNichtExistiert(){
        assertThrows(IllegalArgumentException.class, ()->{
           grundbuch.getBesitzerVon(mock(Straße.class));
        });
    }

    @Test
    public void alleGrundstückeVonSpielerMitNichts(){
        assertTrue(arraysSindGleich(new Grundstück[]{}, grundbuch.alleGrundstückeVon(spieler1)));
    }
    @Test
    public void alleGrundstückeVonSpielerMitEinem(){
        grundbuch.übertragenAn(straße, spieler1);
        assertTrue(arraysSindGleich(new Grundstück[]{straße},grundbuch.alleGrundstückeVon(spieler1)));
    }
    @Test
    public void alleGrundstückeVonSpielerMitAllem(){
        for (Grundstück g : alleGrundstücke){
            grundbuch.übertragenAn(g, spieler1);
        }
        assertTrue(arraysSindGleich(alleGrundstücke, grundbuch.alleGrundstückeVon(spieler1)));
    }

    @Test
    public void bahnhöfeVonKeinem(){
        assertEquals(0, grundbuch.anzahlBahnhöfeVon(spieler1));
    }
    @Test
    public void bahnhöfeVonEinem(){
        grundbuch.übertragenAn(straße, spieler1);
        grundbuch.übertragenAn(bahnhof, spieler1);
        assertEquals(1, grundbuch.anzahlBahnhöfeVon(spieler1));
    }

    @Test
    public void keinWerk() {
        assertThrows(IllegalStateException.class, () -> grundbuch.hatBeideWerke(spieler1));
    }
    @Test
    public void einWerk(){
        grundbuch.übertragenAn(werk, spieler1);
        assertFalse(grundbuch.hatBeideWerke(spieler1));
    }
    @Test
    public void beideWerke(){
        grundbuch.übertragenAn(werk, spieler1);
        grundbuch.übertragenAn(werk2, spieler1);
        assertTrue(grundbuch.hatBeideWerke(spieler1));
    }
}
