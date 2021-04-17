package programm.statemashine.states;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programm.statemashine.Kontext;
import programm.system.Würfel;
import programm.system.brett.Brett;
import programm.system.spieler.Spielleiter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

// Ist eigentlich nur dazu da die Testcoverage zu erhöhen :)
class AbstractStateTest {

    private class SubState extends State{

        public SubState(Kontext kontext, Würfel würfel, Spielleiter spielleiter, Brett brett) {
            super(kontext, würfel, spielleiter, brett);
        }
    }

    SubState sub;

    @BeforeEach
    public void init(){
        Kontext kontext = mock(Kontext.class);
        Würfel würfel = mock(Würfel.class);
        Spielleiter spielleiter = mock(Spielleiter.class);
        Brett brett = mock(Brett.class);
        sub = new SubState(kontext, würfel, spielleiter, brett);
    }

    @Test
    public void werfengibtNull(){
        assertNull(sub.werfen());
    }

    @Test
    public void bestätigengibtNull(){assertNull(sub.bestätigen());}

    @Test
    public void übersichtgibtNull(){assertNull(sub.übersicht());}

    @Test
    public void zurückgibtNull(){assertNull(sub.zurück());}

}