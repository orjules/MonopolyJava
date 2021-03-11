package programm.system.interfaces;

import programm.karten.Ereigniskarte;
import programm.system.enums.Felder;

public interface IKartenmanager {

    public IEreigniskarte karteZiehen(Felder feld);
}
