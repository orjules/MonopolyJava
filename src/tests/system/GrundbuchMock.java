package tests.system;

import programm.grundstücke.Grundstück;
import programm.system.enums.Felder;
import programm.system.interfaces.Grundbuch;

public class GrundbuchMock implements Grundbuch {
    Grundstück testStück;
    Boolean testBool;

    public GrundbuchMock(Grundstück testStück, Boolean testBool) {
        this.testStück = testStück;
        this.testBool = testBool;
    }

    public Grundstück grundstückVon(Felder feld){
        return testStück;
    }

    public Boolean istZuVerkaufen(Grundstück grundstück){
        return testBool;
    }
}
