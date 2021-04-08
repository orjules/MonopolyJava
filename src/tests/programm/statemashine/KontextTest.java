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

    AufNeuemFeld aufNeuemFeld = mock(AufNeuemFeld.class);
    ErsterWurf ersterWurf = mock(ErsterWurf.class);
    Übersicht übersicht = mock(Übersicht.class);
    AllesErledigt allesErledigt = mock(AllesErledigt.class);

    AusgabeModell ausgabeModell = mock(AusgabeModell.class);
    EingabeModell eingabeModell = mock(EingabeModell.class);

    Kontext kontext = new Kontext();

    @BeforeEach
    public void init(){
        kontext.statesFüllen(aufNeuemFeld, ersterWurf, übersicht, allesErledigt);
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
    public void vonErsterWurfZuNeuemFeld(){
        kontext.setAktuellerState(kontext.getAufNeuemFeld());
        assertEquals(aufNeuemFeld, kontext.getAktuellerState());
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