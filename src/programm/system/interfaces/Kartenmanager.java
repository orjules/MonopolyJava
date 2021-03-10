package programm.system.interfaces;

import programm.karten.Ereigniskarte;
import programm.system.enums.Felder;

public interface Kartenmanager {

    public Ereigniskarte karteZiehen(Felder feld);
}
