package programm.system.spieler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SpielleiterTest {

    ArrayList<Spieler> alleSpieler;
    Spieler spieler1 = mock(Spieler.class);
    Spieler spieler2 = mock(Spieler.class);
    Spieler spieler3 = mock(Spieler.class);
    Spielleiter spielleiter;

    @BeforeEach
    public void init(){
        alleSpieler = new ArrayList<>();
        alleSpieler.add(spieler1);
        alleSpieler.add(spieler2);
        alleSpieler.add(spieler3);
        spielleiter = new Spielleiter(alleSpieler);
    }

    @Test
    public void ersterDranIst0(){
        assertEquals(spieler1, spielleiter.getGeradeDran());
    }

    @Test
    public void weiterNormal(){
        spielleiter.weiter();
        assertEquals(spieler2, spielleiter.getGeradeDran());
    }

    @Test
    public void weiterOverflow(){
        spielleiter.weiter();
        spielleiter.weiter();
        spielleiter.weiter();
        assertEquals(spieler1, spielleiter.getGeradeDran());
    }

}