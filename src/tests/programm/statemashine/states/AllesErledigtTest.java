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

class AllesErledigtTest {

    Kontext kontext = mock(Kontext.class);
    Würfel würfel = mock(Würfel.class);
    Spielleiter spielleiter = mock(Spielleiter.class);
    Brett brett = mock(Brett.class);

    AllesErledigt allesErledigt = new AllesErledigt(kontext, würfel, spielleiter, brett);

    ErsterWurf ersterWurf = mock(ErsterWurf.class);
    Übersicht übersicht = mock(Übersicht.class);
    AusgabeModell letztesAusgabeModell = mock(AusgabeModell.class);
    Spieler spieler1 = mock(Spieler.class);
    Spieler spieler2 = mock(Spieler.class);


    @BeforeEach
    public void init(){
        when(kontext.getLetzteAusgabe()).thenReturn(letztesAusgabeModell);
        when(kontext.getErsterWurf()).thenReturn(ersterWurf);
        when(kontext.getÜbersicht()).thenReturn(übersicht);
        when(spielleiter.getGeradeDran()).thenReturn(spieler1);
    }

    @Test
    public void bestätigenGehtNicht(){
        assertEquals(letztesAusgabeModell, allesErledigt.bestätigen());
    }


    @Test
    public void zurückBeendetZug(){
        when(spielleiter.getGeradeDran()).thenReturn(spieler2);

        AusgabeModell ausgabeModell = allesErledigt.zurück();

        verify(spielleiter, times(1)).weiter();
        verify(kontext, times(1)).setAktuellerState(ersterWurf);
        verify(würfel, times(1)).reset();
        modelleSindGleich(modellWennBeendet(), ausgabeModell);
    }
    private AusgabeModell modellWennBeendet(){
        HashMap<Eingaben, EingabeBeschreibungen> erlaubt = new HashMap<>();
        erlaubt.put(Eingaben.werfen, EingabeBeschreibungen.ersterWurf);
        ArrayList<Ausgaben> ausgaben = new ArrayList<>();
        ausgaben.add(Ausgaben.mussErstWürfeln);
        return new AusgabeModell(null, spieler2, brett, null, erlaubt, ausgaben);
    }

    @Test
    public void übersichtGehtZurÜbersicht(){

        AusgabeModell ausgabeModell = allesErledigt.übersicht();

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
    public void werfenOhnePaschGehtNicht(){
        when(würfel.darfNochmalWerfen()).thenReturn(false);

        assertEquals(letztesAusgabeModell, allesErledigt.werfen());
        verify(kontext, times(0)).setAktuellerState(any());
    }

    // Hilfsfunktion für alle
    private void modelleSindGleich(AusgabeModell erwartet, AusgabeModell wirklich){
        assertEquals(erwartet.getFeld(), wirklich.getFeld());
        assertEquals(erwartet.getGeradeDran(), wirklich.getGeradeDran());
        assertEquals(erwartet.getBrett(), wirklich.getBrett());
        assertEquals(erwartet.getLetzterWurf(), wirklich.getLetzterWurf());
        assertEquals(erwartet.getErlaubteEingaben(), wirklich.getErlaubteEingaben());
        assertEquals(erwartet.getAusgaben(), wirklich.getAusgaben());
    }
}