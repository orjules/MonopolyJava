package programm.statemashine;

import org.junit.jupiter.api.Test;
import programm.newSystem.EingabeModell;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class KontextTest {

    NeuesEingabeModell eingabe = mock(NeuesEingabeModell.class);

    Kontext kontext = new Kontext();

    @Test
    public void hatErstelleModell(){
        when(eingabe.getAntwort()).thenReturn(Eingaben.werfen);
        kontext.erstelleModell(eingabe);
    }

}