package programm.grundstücke;

import programm.system.Felder;
import programm.system.interfaces.IGrundstück;

public abstract class Grundstück implements IGrundstück {

    String name;
    Felder feld;
    int grundstücksWert;
    int hypothekWert;

    public String getName() {
        return name;
    }

    public Felder getFeld() {
        return feld;
    }

    public int getGrundstücksWert() {
        return grundstücksWert;
    }
}
