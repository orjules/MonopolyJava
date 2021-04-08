package programm.statemashine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programm.statemashine.enums.Eingaben;
import programm.statemashine.io.AusgabeModell;
import programm.statemashine.io.EingabeModell;
import programm.statemashine.states.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KontextTest {

    ErsterWurf ersterWurf = mock(ErsterWurf.class);
    AufFreiemGrundstück aufFreiemGrundstück = mock(AufFreiemGrundstück.class);
    AufBesetztemGrundstück aufBesetztemGrundstück = mock(AufBesetztemGrundstück.class);
    AufKarte aufKarte = mock(AufKarte.class);
    Übersicht übersicht = mock(Übersicht.class);
    AllesErledigt allesErledigt = mock(AllesErledigt.class);

    AusgabeModell ausgabeModell = mock(AusgabeModell.class);
    EingabeModell eingabeModell = mock(EingabeModell.class);

    Kontext kontext = new Kontext();

    @BeforeEach
    public void init(){
        kontext.statesFüllen(ersterWurf, aufFreiemGrundstück, aufBesetztemGrundstück, aufKarte, übersicht, allesErledigt);
        when(ersterWurf.werfen()).thenReturn(ausgabeModell);
        when(ersterWurf.bestätigen()).thenReturn(ausgabeModell);
        when(ersterWurf.übersicht()).thenReturn(ausgabeModell);
        when(ersterWurf.zurück()).thenReturn(ausgabeModell);
    }

    @Test
    public void ersterStateIstErsterWurf(){
        assertEquals(ersterWurf, kontext.getAktuellerState());
    }

    @Test
    public void erstelleModellWerfen(){
        when(eingabeModell.getAntwort()).thenReturn(Eingaben.werfen);
        assertEquals(ausgabeModell, kontext.erstelleModell(eingabeModell));
        verify(ersterWurf, times(1)).werfen();
        assertEquals(ausgabeModell, kontext.getLetzteAusgabe());
    }

    @Test
    public void erstelleModellBestätigen(){
        when(eingabeModell.getAntwort()).thenReturn(Eingaben.bestätigen);
        assertEquals(ausgabeModell, kontext.erstelleModell(eingabeModell));
        verify(ersterWurf, times(1)).bestätigen();
        assertEquals(ausgabeModell, kontext.getLetzteAusgabe());
    }

    @Test
    public void erstelleModellÜbericht(){
        when(eingabeModell.getAntwort()).thenReturn(Eingaben.übersicht);
        assertEquals(ausgabeModell, kontext.erstelleModell(eingabeModell));
        verify(ersterWurf, times(1)).übersicht();
        assertEquals(ausgabeModell, kontext.getLetzteAusgabe());
    }

    @Test
    public void erstelleModellZurück(){
        when(eingabeModell.getAntwort()).thenReturn(Eingaben.zurück);
        assertEquals(ausgabeModell, kontext.erstelleModell(eingabeModell));
        verify(ersterWurf, times(1)).zurück();
        assertEquals(ausgabeModell, kontext.getLetzteAusgabe());
    }

    @Test
    public void vonErsterWurfZuFreiemGrundstück(){
        kontext.setAktuellerState(kontext.getAufFreiemGrundstück());
        assertEquals(aufFreiemGrundstück, kontext.getAktuellerState());
        assertEquals(ersterWurf, kontext.getLetzterState());
    }

    @Test
    public void vonErsterWurfZuBesetztemGrundstück(){
        kontext.setAktuellerState(kontext.getAufBesetztemGrundstück());
        assertEquals(aufBesetztemGrundstück, kontext.getAktuellerState());
        assertEquals(ersterWurf, kontext.getLetzterState());
    }

    @Test
    public void vonErsterWurfZuKarte(){
        kontext.setAktuellerState(kontext.getAufKarte());
        assertEquals(aufKarte, kontext.getAktuellerState());
        assertEquals(ersterWurf, kontext.getLetzterState());
    }


    @Test
    public void vonErsterWurfZuÜbersicht(){
        kontext.setAktuellerState(kontext.getÜbersicht());
        assertEquals(übersicht, kontext.getAktuellerState());
        assertEquals(ersterWurf, kontext.getLetzterState());
    }

    @Test
    public void vonErsterWurfZuAllesErledigt(){
        kontext.setAktuellerState(kontext.getAllesErledigt());
        assertEquals(allesErledigt, kontext.getAktuellerState());
        assertEquals(ersterWurf, kontext.getLetzterState());
    }
}