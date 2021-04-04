package programm.statemashine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programm.statemashine.enums.Eingaben;
import programm.statemashine.io.NeuesEingabeModell;
import programm.statemashine.states.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class KontextTest {

    NeuesEingabeModell eingabe = mock(NeuesEingabeModell.class);

    AllesErledigt allesErledigt = mock(AllesErledigt.class);
    BesetzesGrundstück besetzesGrundstück = mock(BesetzesGrundstück.class);
    ErsterWurf ersterWurf = mock(ErsterWurf.class);
    FreiesGrundstück freiesGrundstück = mock(FreiesGrundstück.class);
    ImGefängnis imGefängnis = mock(ImGefängnis.class);
    KarteZiehen karteZiehen = mock(KarteZiehen.class);
    Versteigern versteigern = mock(Versteigern.class);
    ZuWenigGeld zuWenigGeld = mock(ZuWenigGeld.class);
    Übersicht übersicht = mock(Übersicht.class);

    Kontext kontext = new Kontext();

    @BeforeEach
    public void init(){
        kontext.statesReingeben(allesErledigt, besetzesGrundstück, ersterWurf, freiesGrundstück, imGefängnis,
                karteZiehen, versteigern, zuWenigGeld, übersicht);
    }

    @Test
    public void hatErstelleModell(){
        when(eingabe.getAntwort()).thenReturn(Eingaben.bestätigen);
        kontext.erstelleModell(eingabe);
    }

    @Test
    public void ersterStateIstErsterWurf(){
        State state = kontext.getAktuellerState();
        assertTrue(state instanceof ErsterWurf);
    }

}