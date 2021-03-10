package tests.system;

import programm.karten.Ereigniskarte;
import programm.system.enums.Felder;
import programm.system.interfaces.Kartenmanager;

public class KartenmanagerMock implements Kartenmanager {
    Ereigniskarte testKarte;

    public KartenmanagerMock(Ereigniskarte testKarte) {
        this.testKarte = testKarte;
    }

    public Ereigniskarte karteZiehen(Felder feld){
        return testKarte;
    }
}
