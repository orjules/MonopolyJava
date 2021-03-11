package tests.system.Mocks;

import programm.system.enums.Felder;
import programm.system.interfaces.IKartenmanager;

public class KartenmanagerMock implements IKartenmanager {
    EreigniskarteMock testKarte;

    public void init(EreigniskarteMock testKarte) {
        this.testKarte = testKarte;
    }

    public EreigniskarteMock karteZiehen(Felder feld){
        return testKarte;
    }
}
