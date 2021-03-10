package tests.system.Mocks;

import programm.system.Spieler;
import programm.system.interfaces.ISpielleiter;

public class SpielleiterMock implements ISpielleiter {

    Spieler festerSpieler;
    boolean spielLäuft = true;

    public SpielleiterMock(Spieler festerSpieler) {
        this.festerSpieler = festerSpieler;
    }

    public Spieler getGeradeDran(){
        return festerSpieler;
    }
    public void weiter(){}
    public void spielerBewegen(int wert){}

    public boolean spielLäuft(){
        return spielLäuft;
    }

    public void setSpielLäuft(boolean spielLäuft) {
        this.spielLäuft = spielLäuft;
    }
}
