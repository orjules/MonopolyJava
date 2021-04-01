package programm.karten;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import programm.system.Felder;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

import static org.mockito.Mockito.*;

class DreiFelderZurückTest {

    DreiFelderZurück dreiFelderZurück;
    String consturtorBeschreibung = "Testbeschreibung";

    Spielleiter spielleiter = mock(Spielleiter.class);
    Spieler geradeDran = mock(Spieler.class);

    String geradeDranString = "Spieler 1 (?)";
    Felder altesFeld = Felder.Turmstraße;
    Felder neuesFeld = Felder.Los;


    @BeforeEach
    public void init(){
        dreiFelderZurück = new DreiFelderZurück(consturtorBeschreibung, spielleiter);
        when(spielleiter.getGeradeDran()).thenReturn(geradeDran);
        when(geradeDran.toString()).thenReturn(geradeDranString);
    }

    @Test
    public void aktionAusführen(){
        when(geradeDran.getAktuellePos()).thenReturn(altesFeld);
        dreiFelderZurück.aktionAusführen();
        verify(spielleiter, times(1)).spielerSetzten(neuesFeld);
    }

    @Test
    public void bestätigungIstKorrekt(){
        when(geradeDran.getAktuellePos()).thenReturn(neuesFeld);
        assertEquals(geradeDranString + " ist nun auf " + neuesFeld.name(), dreiFelderZurück.getBestätigung());
    }

    @Test
    public void aktionWennLosUnterschritten(){
        when(geradeDran.getAktuellePos()).thenReturn(Felder.Badstraße);
        dreiFelderZurück.aktionAusführen();
        Felder vorletztesFeld = Felder.values()[Felder.values().length-2];
        verify(spielleiter,times(1)).spielerSetzten(vorletztesFeld);
    }

}