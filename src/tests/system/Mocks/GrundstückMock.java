package tests.system.Mocks;

import programm.system.enums.Felder;
import programm.system.interfaces.IGrundstück;

public class GrundstückMock implements IGrundstück {

    String name;
    Felder feld;
    int grundstücksWert;
    int hypothekWert;

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
