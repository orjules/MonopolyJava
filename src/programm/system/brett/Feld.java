package programm.system.brett;

import programm.system.spieler.Spieler;

public class Feld {

    Spieler besitzer;

    public Feld(Spieler besitzer) {
        this.besitzer = besitzer;
    }

    public boolean istGrundstück() {
        return false;
    }

    public Spieler getBesitzer() {
        return besitzer;
    }
}
