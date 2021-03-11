package tests.system.Mocks;

import programm.grundstücke.Bahnhof;
import programm.grundstücke.Grundstück;
import programm.grundstücke.Straße;
import programm.grundstücke.Werk;
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

    public String textFürGelandetAuf(IGrundstück grundstück){
      return "Testgrundstück.";
    }
}
