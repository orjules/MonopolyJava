package programm.grundstücke;

import programm.system.enums.Felder;

public class Grundbuch implements programm.system.interfaces.Grundbuch {

    public Grundstück grundstückVon(Felder feld){
        System.out.println("Debug: Grundbuch wurde gefragt.");
        return null;
    }

    public Boolean istZuVerkaufen(Grundstück grundstück){
        System.out.println("Debug: Abfrage ob zu verkaufen.");
        return true;
    }

}
