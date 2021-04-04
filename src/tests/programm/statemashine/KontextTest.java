package programm.statemashine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programm.statemashine.enums.Eingaben;
import programm.statemashine.io.EingabeModell;
import programm.statemashine.states.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class KontextTest {

    EingabeModell eingabe = mock(EingabeModell.class);

    S_BesetzesGrundstück SBesetzesGrundstück = mock(S_BesetzesGrundstück.class);
    S_ErsterWurf SErsterWurf = mock(S_ErsterWurf.class);
    S_FreiesGrundstück SFreiesGrundstück = mock(S_FreiesGrundstück.class);
    S_KarteZiehen SKarteZiehen = mock(S_KarteZiehen.class);
    S_Übersicht SÜbersicht = mock(S_Übersicht.class);

    Kontext kontext = new Kontext();

    @BeforeEach
    public void init(){
        kontext.statesReingeben(SBesetzesGrundstück, SErsterWurf, SFreiesGrundstück,
                SKarteZiehen, SÜbersicht);
    }

    @Test
    public void hatErstelleModell(){
        when(eingabe.getAntwort()).thenReturn(Eingaben.bestätigen);
        kontext.erstelleModell(eingabe);
    }

    @Test
    public void ersterStateIstErsterWurf(){
        State state = kontext.getAktuellerState();
        assertTrue(state instanceof S_ErsterWurf);
    }

}