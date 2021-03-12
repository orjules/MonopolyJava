package programm.grundstücke;

import programm.system.Felder;
import programm.system.Würfel;
import programm.system.spieler.Spieler;

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

    public String getAusbauLevel(){
        String ausgabe = "";
        switch (anzahlHaus){
            case 0:
                ausgabe += "Kein Haus ";
                break;
            case 1:
                ausgabe += "Ein Haus ";
                break;
            case 2:
                ausgabe += "Zwei Häuser ";
                break;
            case 3:
                ausgabe += "Drei Häuser ";
                break;
            case 4:
                ausgabe += "Vier Häuser ";
                break;
            default:
                throw new IllegalStateException("Ein Grundstück darf nur zwischen 0 und 4 Häusern haben!");
        }
        if (hatHotel){
            ausgabe += "und ein Hotel";
        }else {
            ausgabe += "und kein Hotel";
        }
        return ausgabe;
    }

    @Override
    public int mieteBerechnen(Spieler besitzer, Grundbuch grundbuch, int letzterWurf) {
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
}
