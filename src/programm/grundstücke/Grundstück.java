package programm.grundstücke;

import programm.system.Felder;
import programm.system.Würfel;
import programm.system.spieler.Spieler;

public abstract class Grundstück {

    String name;
    Felder feld;
    int grundstücksWert;
    int hypothekWert;

    public String getName() {
        return name;
    }

    public Felder getFeld() {
        return feld;
    }

    public int getGrundstücksWert() {
        return grundstücksWert;
    }

    public abstract int mieteBerechnen(Spieler besitzer, Grundbuch grundbuch, int letzterWurf);

    public abstract String getPronomenKlein();
    public String getPronomenGroß(){
        String klein = getPronomenKlein();
        return klein.substring(0,1).toUpperCase() + klein.substring(1);
    }
    public abstract String getArtikelKlein(Fälle fall);
    public String getArtikelGroß(Fälle fall) {
        String klein = getArtikelKlein(fall);
        return klein.substring(0,1).toUpperCase() + klein.substring(1);
    }
}
