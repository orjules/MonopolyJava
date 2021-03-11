package programm.system.interfaces;

import programm.system.Felder;

public interface IGrundbuch {

    public IGrundstück grundstückVon(Felder feld);

    public Boolean istZuVerkaufen(IGrundstück grundstück);

    String textFürGelandetAuf(IGrundstück grundstück);
}
