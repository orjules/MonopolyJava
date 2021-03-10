package programm.grundstücke;

import programm.system.enums.Felder;

public class Bahnhof extends Grundstück{
    public Bahnhof(String name, Felder feld) {
        this.name = name;
        this.feld = feld;
        this.grundstücksWert = 200;
        this.hypothekWert = 100;
    }
}
