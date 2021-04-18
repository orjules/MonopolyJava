package programm.system.brett;

import programm.system.spieler.Spieler;

public class Brett {
    public Feld spielerBewegen(int wurf) {
        return null;
    }

    public boolean istÜberLosGegangen() {
        return false;
        // Ruft die Karte auf, die den Spielleiter aufruft, die dem Spieler die 200 gibt (erlaubt es auch andere werte zu haben)
        // Danach resetten
    }

    public int mieteVon(Feld feld) {
        return 0;
        // Breit selber weiß nun mal welches Grundstück wo ist und was es für die Berechnung braucht
    }

    public Feld getAktuellesFeldVon(Spieler geradeDran) {
        return null;
        // Sucht wo der Spieler gerade ist und gibt das Feld an wo er ist
    }

    public boolean hatBeideWerke(Spieler besitzer) {
        return false;
    }

    public int anzahlBahnhöfeVon(Spieler besitzer) {
        return 0;
    }
}
