package programm.karten;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

import static org.mockito.Mockito.*;

public class BankGeldTest {

    BankGeld bankGeld;
    String consturtorBeschreibung = "Testbeschreibung";
    int constructorWert = 10;

    Spielleiter spielleiter = mock(Spielleiter.class);
    Spieler geradeDran = mock(Spieler.class);

    String geradeDranString = "Spieler 1 (?)";
    int geradeDranKapital = 100;

    @BeforeEach
    public void init(){
        bankGeld = new BankGeld(consturtorBeschreibung, spielleiter, constructorWert);
        when(spielleiter.getGeradeDran()).thenReturn(geradeDran);
        when(geradeDran.toString()).thenReturn(geradeDranString);
        when(geradeDran.getKapital()).thenReturn(geradeDranKapital);
    }

    @Test
    public void aktionAusführen(){
        bankGeld.aktionAusführen();
        verify(spielleiter, times(1)).kapitalÄndernVon(geradeDran, constructorWert);
    }

    @Test
    public void wertIstKorrekt(){
        assertEquals(constructorWert, bankGeld.getWert());
    }

    @Test
    public void beschreibungIstKorrekt(){
        assertEquals(consturtorBeschreibung, bankGeld.getBeschreibung());
    }

    @Test
    public void bestätigungIstKorrekt(){
        assertEquals(geradeDranString + " hat jetzt " + geradeDranKapital + "€.", bankGeld.getBestätigung());
    }
}
