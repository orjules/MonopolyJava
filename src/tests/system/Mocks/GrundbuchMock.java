package tests.system.Mocks;

import programm.grundstücke.Grundstück;
import programm.system.enums.Felder;
import programm.system.interfaces.IGrundbuch;
import programm.system.interfaces.IGrundstück;

public class GrundbuchMock implements IGrundbuch {
    GrundstückMock testStück;
    Boolean testBool;

    public GrundbuchMock(GrundstückMock testStück, Boolean testBool) {
        this.testStück = testStück;
        this.testBool = testBool;
    }

    public GrundstückMock grundstückVon(Felder feld){
        return testStück;
    }

    public Boolean istZuVerkaufen(IGrundstück grundstück){
        return testBool;
    }
}
