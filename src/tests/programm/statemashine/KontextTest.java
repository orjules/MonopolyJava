package programm.statemashine;

import org.junit.jupiter.api.Test;
import programm.newSystem.EingabeModell;
import programm.statemashine.states.ErsterWurf;
import programm.statemashine.states.State;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class KontextTest {

    NeuesEingabeModell eingabe = mock(NeuesEingabeModell.class);

    Kontext kontext = new Kontext();


    @Test
    public void hatErstelleModell(){
        when(eingabe.getAntwort()).thenReturn(Eingaben.bestätigen);
        kontext.erstelleModell(eingabe);
    }

    // Am Anfang soll der State Erster Wurf sein
    @Test
    public void ersterStateIstErsterWurf(){
        State state = kontext.getState();
        assertEquals(ErsterWurf.class, state.getClass());
    }

}