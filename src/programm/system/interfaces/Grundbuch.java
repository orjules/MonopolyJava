package programm.system.interfaces;

import programm.grundstücke.Grundstück;
import programm.system.enums.Felder;

public interface Grundbuch {

    public Grundstück grundstückVon(Felder feld);

    public Boolean istZuVerkaufen(Grundstück grundstück);
}
