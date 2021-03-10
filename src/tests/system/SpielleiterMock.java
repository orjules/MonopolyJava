package tests.system;

import programm.system.Spieler;
import programm.system.interfaces.Spielleiter;

public class SpielleiterMock implements Spielleiter {

    Spieler festerSpieler;

    public SpielleiterMock(Spieler festerSpieler) {
        this.festerSpieler = festerSpieler;
    }

    public Spieler getGeradeDran(){
        return festerSpieler;
    }
    public void weiter(){}
    public void spielerBewegen(int wert){}
}
