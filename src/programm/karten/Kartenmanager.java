package programm.karten;

import programm.system.enums.Felder;
import programm.system.interfaces.IKartenmanager;

public class Kartenmanager implements IKartenmanager {

    public Ereigniskarte karteZiehen(Felder feld){
        System.out.println("Debug: Kartenmanager wurde gefragt.");
        return null;
    }
}
