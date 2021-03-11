package tests.system.Mocks;

import programm.system.Felder;
import programm.system.interfaces.IGrundbuch;
import programm.system.interfaces.IGrundstück;

public class GrundbuchMock implements IGrundbuch {
    GrundstückMock testStück;
    Boolean testBool;

    public void init(GrundstückMock testStück, Boolean istZuVerkaufen) {
        this.testStück = testStück;
        this.testBool = istZuVerkaufen;
    }

    public GrundstückMock grundstückVon(Felder feld){
        return testStück;
    }

    public Boolean istZuVerkaufen(IGrundstück grundstück){
        return testBool;
    }

    public String textFürGelandetAuf(IGrundstück grundstück){
      return "Testgrundstück.";
    }
}
