package programm.statemashine.states;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programm.statemashine.Kontext;
import programm.statemashine.NeuesAusgabeModell;
import programm.statemashine.enums.FehlerMeldungen;
import programm.system.spieler.Spieler;

import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;

class ErsterWurfTest {

    Kontext kontext = mock(Kontext.class);

    ErsterWurf ersterWurf;

    @BeforeEach
    public void init(){
        ersterWurf = new ErsterWurf(kontext);
    }

    @Test
    public void übersichtIstNichtErlaubt(){
        NeuesAusgabeModell eigentlicheAusgabe = ersterWurf.übersicht();

        assertEquals(FehlerMeldungen.gehtGeradeNicht, eigentlicheAusgabe.getFehlerMeldung());
    }

    @Test
    public void bestätigenIstNichtErlaubt(){
        NeuesAusgabeModell eigentlicheAusgabe = ersterWurf.bestätigen();

        assertEquals(FehlerMeldungen.gehtGeradeNicht, eigentlicheAusgabe.getFehlerMeldung());
    }

    @Test
    public void zurückIstNichtErlaubt(){
        NeuesAusgabeModell eigentlicheAusgabe = ersterWurf.zurück();

        assertEquals(FehlerMeldungen.gehtGeradeNicht, eigentlicheAusgabe.getFehlerMeldung());
    }

    // soll bei Wurf auf freiem/ besetztem Grundstück oder Karte landen

}