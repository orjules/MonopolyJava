package programm.statemashine.states;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programm.statemashine.Kontext;
import programm.statemashine.NeuesAusgabeModell;
import programm.statemashine.enums.FehlerMeldungen;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ErsterWurfTest {

    Kontext kontext = mock(Kontext.class);
    Spielleiter spielleiter = mock(Spielleiter.class);
    Spieler spieler1 = mock(Spieler.class);

    ErsterWurf ersterWurf;

    @BeforeEach
    public void init(){
        when(spielleiter.getGeradeDran()).thenReturn(spieler1);
        ersterWurf = new ErsterWurf(kontext, spielleiter);
    }

    @Test
    public void übersichtIstNichtErlaubt(){
        gehtNichtWurdeKorrektErstellt(ersterWurf.übersicht());
    }
    @Test
    public void bestätigenIstNichtErlaubt(){
        gehtNichtWurdeKorrektErstellt(ersterWurf.bestätigen());
    }
    @Test
    public void zurückIstNichtErlaubt(){
        gehtNichtWurdeKorrektErstellt(ersterWurf.zurück());
    }
    private void gehtNichtWurdeKorrektErstellt(NeuesAusgabeModell eigentlicheAusgabe){
        assertEquals(FehlerMeldungen.gehtGeradeNicht, eigentlicheAusgabe.getFehlerMeldung());
        assertEquals(spieler1, eigentlicheAusgabe.getGeradeDran());
    }

    // soll bei Wurf auf freiem/ besetztem Grundstück oder Karte landen

}