package tests.system;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import programm.system.Felder;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

import java.util.ArrayList;
import java.util.Arrays;

public class SpielleiterTest {

    // Bei aufgeben bin ich nicht sicher ob es wegen der indizes ein Porblem geben könnte - darum Test!
    @Test
    public void einePersonGeht(){
        // setUp
        ArrayList<Spieler> dreiSpieler = new ArrayList<>(Arrays.asList(
                new Spieler("Günther", '#', Felder.Los, false, 1500),
                new Spieler("Monika", '?', Felder.Los, false, 1500),
                new Spieler("Detlef", '!', Felder.Los, false, 1500)));
        Spielleiter testLeiter = new Spielleiter(new ArrayList<>(dreiSpieler));
        // Ausführen, wenn der mittlere Aufgibt
        testLeiter.weiter();
        testLeiter.aufgeben();
        assertEquals(dreiSpieler.get(2), testLeiter.getGeradeDran());
    }

    @Test
    public void letztePersonGeht(){
        // setUp
        ArrayList<Spieler> einSpieler = new ArrayList<>(Arrays.asList(
                new Spieler("Günther", '#', Felder.Los, false, 1500)));
        Spielleiter testLeiter = new Spielleiter(new ArrayList<>(einSpieler));
        // Ausführen
        testLeiter.aufgeben();
        assertEquals(einSpieler.get(0),testLeiter.getGeradeDran());
    }

    @Test
    public void letzteBeidenPersonenGehen(){
        // setUp
        ArrayList<Spieler> zweiSpieler = new ArrayList<>(Arrays.asList(
                new Spieler("Günther", '#', Felder.Los, false, 1500),
                new Spieler("Detlef", '!', Felder.Los, false, 1500)));
        Spielleiter testLeiter = new Spielleiter(new ArrayList<>(zweiSpieler));
        // Ausführen
        testLeiter.aufgeben();
        assertEquals(zweiSpieler.get(1),testLeiter.getGeradeDran());
    }
}
