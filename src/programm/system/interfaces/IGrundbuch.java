package programm.system.interfaces;

import programm.grundstücke.Grundstück;
import programm.system.enums.Felder;

public interface IGrundbuch {

    public IGrundstück grundstückVon(Felder feld);

    public Boolean istZuVerkaufen(IGrundstück grundstück);
}
