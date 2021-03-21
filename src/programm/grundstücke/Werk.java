package programm.grundstücke;

import programm.system.Felder;
import programm.system.Würfel;
import programm.system.spieler.Spieler;

public class Werk extends Grundstück{
    public Werk(String name, Felder feld, int grundstücksWert, int hypothekWert) {
        this.name = name;
        this.feld = feld;
        this.grundstücksWert = grundstücksWert;
        this.hypothekWert = hypothekWert;
    }

    @Override
    public int mieteBerechnen(Spieler besitzer, Grundbuch grundbuch, int letzterWurf) {
        // Besitzt er eines, beträgt die Miete das 4-fache des Wurfes.
        // Besitzt der Spieler beide, handelt es sich um das 10-fache des Wurfes
        if (letzterWurf < 2 || letzterWurf > 12){
            throw new IllegalArgumentException("Der Wurf darf nicht großer 12 oder kleiner 2 sein");
        }
        if (grundbuch.beideWerke(besitzer)){
            return letzterWurf * 10;
        }else {
            return letzterWurf * 4;
        }
    }
}
