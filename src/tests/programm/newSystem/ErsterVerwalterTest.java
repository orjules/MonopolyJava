package programm.newSystem;

import org.junit.jupiter.api.Test;
import programm.grundstücke.Grundbuch;
import programm.grundstücke.Grundstück;
import programm.system.Würfel;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ErsterVerwalterTest {

    Spieler spieler1 = mock(Spieler.class);
    Spieler spieler2 = mock(Spieler.class);
    Spielleiter spielleiter = mock(Spielleiter.class);
    Würfel würfel = mock(Würfel.class);
    Grundbuch grundbuch = mock(Grundbuch.class);
    Grundstück grundstück = mock(Grundstück.class);
    EingabeModell eingabe = mock(EingabeModell.class);

    ErsterVerwalter ersterVerwalter = new ErsterVerwalter();

    public void initialerZustandErstellen(){
        when(spielleiter.getGeradeDran()).thenReturn(spieler1);
    }

    @Test
    public void initialeEingabe(){
        when(eingabe.getAntwort()).thenReturn(MöglicheEingaben.start);

        AusgabeModell ausgabe = ersterVerwalter.modellErstellen(eingabe);
        assertEquals(MöglicheAusgaben.würfeln, ausgabe.getFrage());
    }

    @Test
    public void initialerWurfBisFreiesGrundstück(){
        // System ist im initalzustand (Spieler auf Los)
        // Eingabe Modell gibt 'wurf'

        // System soll würfeln, Spieler bewegen, neues Feld als Grundstück erkennen und allen in das Ausgabe-Modell schreiben
    }

    @Test
    public void initialerWurfBisBesetzesGrundstück(){

    }

    @Test
    public void initialerWurfBisFreiesGrundstückMitZuWenigGeld(){

    }

    @Test
    public void initialerWurfBisBesetzesGrundstückMitZuWenigGeld(){

    }


}