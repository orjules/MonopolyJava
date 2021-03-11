package programm.grundstücke;

import programm.system.Felder;

public abstract class Grundstück {

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
