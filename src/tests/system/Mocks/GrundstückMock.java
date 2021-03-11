package tests.system.Mocks;

import programm.system.Felder;
import programm.system.interfaces.IGrundstück;

public class GrundstückMock implements IGrundstück {

    String name;
    Felder feld;
    int grundstücksWert;
    int hypothekWert;

    public GrundstückMock(Felder feld, int grundstücksWert) {
        name = feld.name();
        this.feld = feld;
        this.grundstücksWert = grundstücksWert;
    }

    public String getName(){
        return name;
    }
    public Felder getFeld(){
        return feld;
    }
    public int getGrundstücksWert(){
        return grundstücksWert;
    }
}
