package programm.grundstücke;

import programm.system.Felder;

public class Werk extends Grundstück{
    public Werk(String name, Felder feld, int grundstücksWert, int hypothekWert) {
        this.name = name;
        this.feld = feld;
        this.grundstücksWert = grundstücksWert;
        this.hypothekWert = hypothekWert;
    }
}
