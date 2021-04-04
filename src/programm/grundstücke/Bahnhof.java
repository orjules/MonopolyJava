package programm.grundstücke;

import programm.system.Felder;
import programm.system.spieler.Spieler;

public class Bahnhof extends Grundstück{
    public Bahnhof(String name, Felder feld) {
        this.name = name;
        this.feld = feld;
        this.grundstücksWert = 200;
        this.hypothekWert = 100;
    }

    @Override
    public int mieteBerechnen(Spieler besitzer, Grundbuch grundbuch, int letzerWurf) {
        return switch (grundbuch.anzahlBahnhöfeVon(besitzer)) {
            case 1 -> 25;
            case 2 -> 50;
            case 3 -> 100;
            case 4 -> 200;
            default -> throw new IllegalStateException("Es dürfen weder weniger als 1, noch mehr als 4 Bahnhöfe im Besitz geben.");
        };
    }

    @Override
    public String getPronomenKlein() {
        return "der";
    }

    @Override
    public String getArtikelKlein(Fälle fall) {
        return switch (fall) {
            case Nominativ -> "der";
            case Genitiv -> "des";
            case Dativ -> "dem";
            case Akkusativ -> "den";
        };
    }
}
