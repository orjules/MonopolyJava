package programm.grundstücke;

import programm.system.enums.Felder;
import programm.system.interfaces.IGrundbuch;
import programm.system.interfaces.IGrundstück;

public class Grundbuch implements IGrundbuch{

    public Grundstück grundstückVon(Felder feld){
        System.out.println("Debug: Grundbuch wurde gefragt.");
        return null;
    }

    public Boolean istZuVerkaufen(IGrundstück grundstück){
        System.out.println("Debug: Abfrage ob zu verkaufen.");
        return true;
    }

}
