package programm.grundstücke;

import programm.system.Felder;
import programm.system.brett.Brett;
import programm.system.spieler.Spieler;

public class Werk extends Grundstück{
    public Werk(String name, Felder feld, int grundstücksWert, int hypothekWert) {
        this.name = name;
        this.feld = feld;
        this.grundstücksWert = grundstücksWert;
        this.hypothekWert = hypothekWert;
    }

    @Override
    public int mieteBerechnen(Spieler besitzer, Brett brett, int letzterWurf) {
        // Besitzt er eines, beträgt die Miete das 4-fache des Wurfes.
        // Besitzt der Spieler beide, handelt es sich um das 10-fache des Wurfes
        if (letzterWurf < 2 || letzterWurf > 12){
            throw new IllegalArgumentException("Der Wurf darf nicht großer 12 oder kleiner 2 sein");
        }
        if (brett.hatBeideWerke(besitzer)){
            return letzterWurf * 10;
        }else {
            return letzterWurf * 4;
        }
    }

    @Override
    public String getPronomenKlein() {
        return "das";
    }

    @Override
    public String getArtikelKlein(Fälle fall) {
        return switch (fall) {
            case Nominativ, Akkusativ -> "das";
            case Genitiv -> "des";
            case Dativ -> "dem";
        };
    }
}
