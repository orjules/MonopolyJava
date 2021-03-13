package tests.system.mocks;

import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

import java.util.ArrayList;

public class MockSpielleiter extends Spielleiter {
    public MockSpielleiter(ArrayList<Spieler> alleSpieler) {
        super(alleSpieler);
    }

    @Override
    public boolean spielLäuft(){
        return false;
    }
}
