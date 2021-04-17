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

class AufFreiemGrundstückTest {

    Kontext kontext = mock(Kontext.class);
    Würfel würfel = mock(Würfel.class);
    Spielleiter spielleiter = mock(Spielleiter.class);
    Brett brett = mock(Brett.class);

    AusgabeModell letztesAusgabeModell = mock(AusgabeModell.class);

    AllesErledigt allesErledigt = mock(AllesErledigt.class);
    Übersicht übersicht = mock(Übersicht.class);

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
        when(kontext.getÜbersicht()).thenReturn(übersicht);
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
        AusgabeModell ausgabeModell = aufFreiemGrundstück.übersicht();

        verify(kontext, times(1)).setAktuellerState(übersicht);
        modelleSindGleich(modellWennÜbersicht(), ausgabeModell);
    }
    private AusgabeModell modellWennÜbersicht(){
        HashMap<Eingaben, EingabeBeschreibungen> erlaubt = new HashMap<>();
        erlaubt.put(Eingaben.zurück, EingabeBeschreibungen.zurück);
        ArrayList<Ausgaben> ausgaben = new ArrayList<>();
        ausgaben.add(Ausgaben.übersicht);

        return new AusgabeModell(null, spieler1, brett, null, erlaubt, ausgaben);
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

    // Hilfsfunktion für alle
    private AusgabeModell modellWennAllesErledigt(){
        HashMap<Eingaben, EingabeBeschreibungen> erlaubt = new HashMap<>();
        erlaubt.put(Eingaben.übersicht, EingabeBeschreibungen.übersicht);
        erlaubt.put(Eingaben.zurück, EingabeBeschreibungen.zugBeenden);
        ArrayList<Ausgaben> ausgaben = new ArrayList<>();
        ausgaben.add(Ausgaben.fertig);
        ausgaben.add(Ausgaben.gekauft);

        return new AusgabeModell(feld, spieler1, brett, null, erlaubt, ausgaben);
    }
}