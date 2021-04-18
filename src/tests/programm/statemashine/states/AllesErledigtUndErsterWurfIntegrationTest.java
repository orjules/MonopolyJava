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
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class AllesErledigtUndErsterWurfIntegrationTest {

    Kontext kontext = mock(Kontext.class);
    Würfel würfel = mock(Würfel.class);
    Spielleiter spielleiter = mock(Spielleiter.class);
    Brett brett = mock(Brett.class);

    AllesErledigt allesErledigt = new AllesErledigt(kontext, würfel, spielleiter, brett);
    ErsterWurf ersterWurf = new ErsterWurf(kontext, würfel, spielleiter, brett);

    AufFreiemGrundstück aufFreiemGrundstück = mock(AufFreiemGrundstück.class);
    AufBesetztemGrundstück aufBesetztemGrundstück = mock(AufBesetztemGrundstück.class);
    AufKarte aufKarte = mock(AufKarte.class);
    Spieler spieler1 = mock(Spieler.class);
    Spieler spieler2 = mock(Spieler.class);

    Feld feld = mock(Feld.class);
    int[] neuerWurf = new int[]{1,2,3};

    @BeforeEach
    public void init(){
        when(kontext.getErsterWurf()).thenReturn(ersterWurf);
        when(kontext.getAufFreiemGrundstück()).thenReturn(aufFreiemGrundstück);
        when(kontext.getAufBesetztemGrundstück()).thenReturn(aufBesetztemGrundstück);
        when(kontext.getAllesErledigt()).thenReturn(allesErledigt);
        when(kontext.getAufKarte()).thenReturn(aufKarte);
        when(feld.istGrundstück()).thenReturn(true);
        when(würfel.würfeln()).thenReturn(neuerWurf);
        when(brett.spielerBewegen(3)).thenReturn(feld);
        when(spielleiter.getGeradeDran()).thenReturn(spieler1);
    }


    @Test
    public void paschBisFreiesGrundstück(){
        when(würfel.darfNochmalWerfen()).thenReturn(true);

        modelleSindGleich(modellWennFrei(), allesErledigt.werfen());
        verify(kontext, times(1)).setAktuellerState(aufFreiemGrundstück);
    }
    private AusgabeModell modellWennFrei(){
        LinkedHashMap<Eingaben, EingabeBeschreibungen> erlaubt = new LinkedHashMap<>();
        erlaubt.put(Eingaben.bestätigen, EingabeBeschreibungen.kaufen);
        erlaubt.put(Eingaben.übersicht, EingabeBeschreibungen.übersicht);
        erlaubt.put(Eingaben.zurück, EingabeBeschreibungen.versteigern);
        ArrayList<Ausgaben> ausgaben = new ArrayList<>();
        ausgaben.add(Ausgaben.aufFreiemGrundstück);
        return new AusgabeModell(feld, spieler1, brett, neuerWurf, erlaubt, ausgaben);
    }

    @Test
    public void paschBisBesetztesGrundstück(){
        when(würfel.darfNochmalWerfen()).thenReturn(true);
        when(feld.getBesitzer()).thenReturn(spieler2);

        modelleSindGleich(modellWennBesetzt(), allesErledigt.werfen());
        verify(kontext,times(1)).setAktuellerState(aufBesetztemGrundstück);
    }
    private AusgabeModell modellWennBesetzt(){
        LinkedHashMap<Eingaben, EingabeBeschreibungen> erlaubt = new LinkedHashMap<>();
        erlaubt.put(Eingaben.bestätigen, EingabeBeschreibungen.mieteZahlen);
        erlaubt.put(Eingaben.übersicht, EingabeBeschreibungen.übersicht);
        ArrayList<Ausgaben> ausgaben = new ArrayList<>();
        ausgaben.add(Ausgaben.aufBesetztemGrundstück);
        return new AusgabeModell(feld, spieler1, brett, neuerWurf, erlaubt, ausgaben);
    }

    @Test
    public void paschBisEigenesGrundstück(){
        when(würfel.darfNochmalWerfen()).thenReturn(true);
        when(feld.getBesitzer()).thenReturn(spieler1);

        modelleSindGleich(modellWennEigenes(), allesErledigt.werfen());
        verify(kontext,times(1)).setAktuellerState(allesErledigt);
    }
    private AusgabeModell modellWennEigenes(){
        LinkedHashMap<Eingaben, EingabeBeschreibungen> erlaubt = new LinkedHashMap<>();
        erlaubt.put(Eingaben.übersicht, EingabeBeschreibungen.übersicht);
        erlaubt.put(Eingaben.zurück, EingabeBeschreibungen.zugBeenden);
        ArrayList<Ausgaben> ausgaben = new ArrayList<>();
        ausgaben.add(Ausgaben.nichtsPassiert);
        return new AusgabeModell(feld, spieler1, brett, neuerWurf, erlaubt, ausgaben);
    }

    @Test
    public void paschBisKarte(){
        when(würfel.darfNochmalWerfen()).thenReturn(true);
        when(feld.istGrundstück()).thenReturn(false);

        modelleSindGleich(modellWennKarte(), allesErledigt.werfen());
        verify(kontext,times(1)).setAktuellerState(aufKarte);

    }
    private AusgabeModell modellWennKarte(){
        LinkedHashMap<Eingaben, EingabeBeschreibungen> erlaubt = new LinkedHashMap<>();
        erlaubt.put(Eingaben.bestätigen, EingabeBeschreibungen.karteBestätigen);
        erlaubt.put(Eingaben.übersicht, EingabeBeschreibungen.übersicht);
        ArrayList<Ausgaben> ausgaben = new ArrayList<>();
        ausgaben.add(Ausgaben.aufKartenFeld);
        return new AusgabeModell(feld, spieler1, brett, neuerWurf, erlaubt, ausgaben);
    }

    @Test
    public void paschBisFreiesGrundstückÜberLos(){
        when(würfel.darfNochmalWerfen()).thenReturn(true);
        when(brett.istÜberLosGegangen()).thenReturn(true);

        modelleSindGleich(modellWennFreiUndÜberLos(), allesErledigt.werfen());
        verify(kontext,times(1)).setAktuellerState(aufFreiemGrundstück);
    }
    private AusgabeModell modellWennFreiUndÜberLos(){
        LinkedHashMap<Eingaben, EingabeBeschreibungen> erlaubt = new LinkedHashMap<>();
        erlaubt.put(Eingaben.bestätigen, EingabeBeschreibungen.kaufen);
        erlaubt.put(Eingaben.übersicht, EingabeBeschreibungen.übersicht);
        erlaubt.put(Eingaben.zurück, EingabeBeschreibungen.versteigern);
        ArrayList<Ausgaben> ausgaben = new ArrayList<>();
        ausgaben.add(Ausgaben.aufFreiemGrundstück);
        ausgaben.add(Ausgaben.überLosGegangen);
        return new AusgabeModell(feld, spieler1, brett, neuerWurf, erlaubt, ausgaben);
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
