package programm.statemashine.states;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programm.statemashine.Kontext;
import programm.statemashine.enums.Ausgaben;
import programm.statemashine.enums.EingabeBeschreibungen;
import programm.statemashine.enums.Eingaben;
import programm.statemashine.io.AusgabeModell;
import programm.system.Würfel;
import programm.system.brett.Brett;
import programm.system.brett.Feld;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ErsterWurfTest {

    Kontext kontext = mock(Kontext.class);
    Würfel würfel = mock(Würfel.class);
    Spielleiter spielleiter = mock(Spielleiter.class);
    Brett brett = mock(Brett.class);

    Spieler spieler1 = mock(Spieler.class);
    int[] festerWürfelWert = new int[]{1,2,3};
    Feld feld = mock(Feld.class);
    AufNeuemFeld aufNeuemFeld = mock(AufNeuemFeld.class);

    ErsterWurf ersterWurf;

    @BeforeEach
    public void init(){
        ersterWurf = new ErsterWurf(kontext, würfel, spielleiter, brett);
        when(spielleiter.getGeradeDran()).thenReturn(spieler1);
        when(würfel.würfeln()).thenReturn(festerWürfelWert);
        when(brett.spielerBewegen(festerWürfelWert[2])).thenReturn(feld);
        when(kontext.getAufNeuemFeld()).thenReturn(aufNeuemFeld);
    }

    @Test
    public void übersichtGehtNicht(){
        gehtNichtIstKorrekt(ersterWurf.übersicht());
    }
    @Test
    public void bestätigenGehtNicht(){
        gehtNichtIstKorrekt(ersterWurf.bestätigen());
    }
    @Test
    public void zurückGehtNicht(){
        gehtNichtIstKorrekt(ersterWurf.zurück());
    }
    private void gehtNichtIstKorrekt(AusgabeModell ausgabeModell){
        assertNull(ausgabeModell.getFeld());
        assertEquals(spieler1, ausgabeModell.getGeradeDran());
        assertEquals(nurWürfelnErlaubt(), ausgabeModell.getErlaubteEingaben());
        assertEquals(erwarteteAusgaben(), ausgabeModell.getAusgaben());
    }
    private HashMap<Eingaben, EingabeBeschreibungen> nurWürfelnErlaubt(){
        HashMap<Eingaben, EingabeBeschreibungen> erwartet = new HashMap<>();
        erwartet.put(Eingaben.werfen, EingabeBeschreibungen.ersterWurf);
        return erwartet;
    }
    private ArrayList<Ausgaben> erwarteteAusgaben(){
        ArrayList<Ausgaben> erwartet = new ArrayList<>();
        erwartet.add(Ausgaben.mussErstWürfeln);
        return erwartet;
    }

    @Test
    public void würfeltBisFreiesGrundstück(){
        when(feld.istGrundstück()).thenReturn(true);

        AusgabeModell ausgabeModell = ersterWurf.werfen();
        assertEquals(feld, ausgabeModell.getFeld());
        assertEquals(spieler1, ausgabeModell.getGeradeDran());
        assertEquals(neuErlaubt(), ausgabeModell.getErlaubteEingaben());
        assertEquals(neueErwarteteAusgaben(), ausgabeModell.getAusgaben());
        assertEquals(brett, ausgabeModell.getBrett());
        assertEquals(festerWürfelWert, ausgabeModell.getLetzterWurf());
        verify(kontext,times(1)).setAktuellerState(aufNeuemFeld);
    }
    private HashMap<Eingaben, EingabeBeschreibungen> neuErlaubt(){
        HashMap<Eingaben, EingabeBeschreibungen> erwartet = new HashMap<>();
        erwartet.put(Eingaben.bestätigen, EingabeBeschreibungen.kaufen);
        erwartet.put(Eingaben.übersicht, EingabeBeschreibungen.übersicht);
        return erwartet;
    }
    private ArrayList<Ausgaben> neueErwarteteAusgaben(){
        ArrayList<Ausgaben> erwartet = new ArrayList<>();
        erwartet.add(Ausgaben.aufNeuemFeldGelandet);
        return erwartet;
    }

    @Test
    public void würfeltBisBesetztesGrundstück(){
        assertTrue(false);
    }

    @Test
    public void würfeltBisKarte(){
        assertTrue(false);
    }

    @Test
    public void würfeltPaschBisFreiesGrunstück(){
        assertTrue(false);
    }

    @Test
    public void würfeltÜberLosBisFreiesGrunstück(){
        assertTrue(false);
    }

    //TODO Refactor wenn sich Code in den Tests wiederholt

}