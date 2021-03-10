package programm.grundstücke;

import programm.system.enums.Felder;
import programm.system.interfaces.IGrundstück;

public abstract class Grundstück implements IGrundstück {

    String name;
    Felder feld;
    int grundstücksWert;
    int hypothekWert;

}
