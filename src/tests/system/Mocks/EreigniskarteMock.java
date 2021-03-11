package tests.system.Mocks;

import programm.system.interfaces.IEreigniskarte;

public class EreigniskarteMock implements IEreigniskarte {

    public String getBeschreibung() {
        return "Dies ist eine Testkarte.";
    }
}
