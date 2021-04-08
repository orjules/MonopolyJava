package programm.statemashine.states;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programm.grundstücke.Grundstück;
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
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AufFreiemGrundstückTest {

    Kontext kontext = mock(Kontext.class);
    Würfel würfel = mock(Würfel.class);
    Spielleiter spielleiter = mock(Spielleiter.class);
    Brett brett = mock(Brett.class);

    AusgabeModell letztesAusgabeModell = mock(AusgabeModell.class);

    ErsterWurf ersterWurf = mock(ErsterWurf.class);
    AufBesetztemGrundstück aufBesetztemGrundstück = mock(AufBesetztemGrundstück.class);
    AufKarte aufKarte = mock(AufKarte.class);
    AllesErledigt allesErledigt = mock(AllesErledigt.class);

    Spieler spieler1 = mock(Spieler.class);
    int[] festerWürfelWert = new int[]{1,2,3};
    Feld feld = mock(Feld.class);
    int miete = 10;

    AufFreiemGrundstück aufFreiemGrundstück;

    @BeforeEach
    public void init(){
        aufFreiemGrundstück = new AufFreiemGrundstück(kontext, würfel, spielleiter, brett);
        when(spielleiter.getGeradeDran()).thenReturn(spieler1);
        when(kontext.getLetzteAusgabe()).thenReturn(letztesAusgabeModell);
        when(brett.getAktuellesFeldVon(spieler1)).thenReturn(feld);
        when(brett.mieteVon(feld)).thenReturn(miete);
        when(kontext.getAllesErledigt()).thenReturn(allesErledigt);
    }

    @Test
    public void werfenGehtNicht(){
        assertEquals(letztesAusgabeModell, aufFreiemGrundstück.werfen());
    }

    @Test
    public void bestätigenKauft(){
        AusgabeModell ausgabeModell = aufFreiemGrundstück.bestätigen();

        verify(spielleiter, times(1)).kapitalÄndernVon(spieler1, miete);
        verify(feld, times(1)).setBesitzer(spieler1);
        verify(kontext, times(1)).setAktuellerState(allesErledigt);
        modelleSindGleich(modellWennAllesErledigt(), ausgabeModell);
    }
    private AusgabeModell modellWennAllesErledigt(){
        HashMap<Eingaben, EingabeBeschreibungen> erlaubt = new HashMap<>();
        erlaubt.put(Eingaben.übersicht, EingabeBeschreibungen.übersicht);
        erlaubt.put(Eingaben.zurück, EingabeBeschreibungen.zugBeenden);
        ArrayList<Ausgaben> ausgaben = new ArrayList<>();
        ausgaben.add(Ausgaben.fertig);
        ausgaben.add(Ausgaben.gekauft);

        return new AusgabeModell(feld, spieler1, brett, null, erlaubt, ausgaben);
    }

    private void modelleSindGleich(AusgabeModell erwartet, AusgabeModell wirklich){
        assertEquals(erwartet.getFeld(), wirklich.getFeld());
        assertEquals(erwartet.getGeradeDran(), wirklich.getGeradeDran());
        assertEquals(erwartet.getBrett(), wirklich.getBrett());
        assertEquals(erwartet.getLetzterWurf(), wirklich.getLetzterWurf());
        assertEquals(erwartet.getErlaubteEingaben(), wirklich.getErlaubteEingaben());
        assertEquals(erwartet.getAusgaben(), wirklich.getAusgaben());
    }

    @Test
    public void übersichtGibtÜbersicht(){
        // kontext -> übersicht
        // Feld ist leer
        // geradeDran ist spieler1
        // brett ist brett
        // erlaubt ist zurück, zurück und später mehr
        // Ausgabe ist übersicht
        assertTrue(false);
    }

    @Test
    public void zurückGibtVersteigerung(){
        // kontext -> versteigerung
        // Feld ist feld
        // geradeDran ist spieler1
        // brett ist brett
        // erlaubt ist ??
        // Ausgabe ist versteigerung
        assertTrue(false);
    }
}