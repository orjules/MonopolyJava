package programm.grundstücke;

import programm.system.Felder;
import programm.system.brett.Brett;
import programm.system.spieler.Spieler;

public class Straße extends Grundstück{
    private Farben farbe;
    private int mieteAlleine;
    private int mieteEinHaus;
    private int mieteZweiHaus;
    private int mieteDreiHaus;
    private int mieteVierHaus;
    private int mieteHotel;
    private int baukosten;
    private int anzahlHaus;
    private boolean hatHotel;

    public Straße(String name, Felder feld, int grundstücksWert, int hypothekWert, Farben farbe, int mieteAlleine,
                  int mieteEinHaus, int mieteZweiHaus, int mieteDreiHaus, int mieteVierHaus, int mieteHotel,
                  int baukosten) {
        this.name = name;
        this.feld = feld;
        this.grundstücksWert = grundstücksWert;
        this.hypothekWert = hypothekWert;
        this.farbe = farbe;
        this.mieteAlleine = mieteAlleine;
        this.mieteEinHaus = mieteEinHaus;
        this.mieteZweiHaus = mieteZweiHaus;
        this.mieteDreiHaus = mieteDreiHaus;
        this.mieteVierHaus = mieteVierHaus;
        this.mieteHotel = mieteHotel;
        this.baukosten = baukosten;
        this.anzahlHaus = 0;
        this.hatHotel = false;
    }

    public String getAusbauLevel(){
        if (hatHotel)
            return "Ein Hotel";
        switch (anzahlHaus){
            case 0:
                return "Kein Haus";
            case 1:
                return "Ein Haus ";
            case 2:
                return "Zwei Häuser ";
            case 3:
                return "Drei Häuser ";
            case 4:
                return "Vier Häuser ";
            default:
                throw new IllegalStateException("Ein Grundstück darf nur zwischen 0 und 4 Häusern haben!");
        }
    }

    @Override
    public int mieteBerechnen(Spieler besitzer, Brett brett, int letzterWurf) {
        if (hatHotel){
            return mieteHotel;
        }
        switch (anzahlHaus){
            case 0:
                return mieteAlleine;
            case 1:
                return mieteEinHaus;
            case 2:
                return mieteZweiHaus;
            case 3:
                return mieteDreiHaus;
            case 4:
                return mieteVierHaus;
            default:
                throw new IllegalStateException("Das Grundstück darf nicht mehr als 4 bzw weniger als 0 Häuser haben.");
        }
    }

    @Override
    public String getPronomenKlein() {
        return "die";
    }

    @Override
    public String getArtikelKlein(Fälle fall) {
        return switch (fall) {
            case Nominativ, Akkusativ -> "die";
            case Genitiv, Dativ -> "der";
        };
    }
}
