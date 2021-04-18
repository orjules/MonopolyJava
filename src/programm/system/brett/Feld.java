package programm.system.brett;

import programm.grundstücke.Grundstück;
import programm.system.spieler.Spieler;

public class Feld {

    Spieler besitzer;
    Grundstück grundstück;

    public Feld(Grundstück grundstück, Spieler besitzer) {
        this.grundstück = grundstück;
        this.besitzer = besitzer;
    }

    public boolean istGrundstück() {
        return false;
    }

    public Grundstück getGrundstück(){
        return grundstück;
    }

    public Spieler getBesitzer() {
        return besitzer;
    }

    public void setBesitzer(Spieler neuerBesitzer) {
        besitzer = neuerBesitzer;
    }
}
