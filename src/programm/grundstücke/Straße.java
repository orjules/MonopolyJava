package programm.grundstücke;

import programm.system.enums.Felder;

public class Straße extends Grundstück{
    Farben farbe;
    int mieteAlleine;
    int mieteEinHaus;
    int mieteZweiHaus;
    int mieteDreiHaus;
    int mieteVierHaus;
    int mieteHotel;
    int baukosten;
    int anzahlHaus;
    boolean hatHotel;

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
}
