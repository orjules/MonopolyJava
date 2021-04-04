package programm.statemashine.io;

import programm.grundstücke.Grundstück;
import programm.system.spieler.Spieler;

public class A_BesetztesGrundstück extends AusgabeModell{

    Grundstück grundstück;
    Spieler besitzer;

    public A_BesetztesGrundstück(Grundstück grundstück, Spieler besitzer) {
        this.grundstück = grundstück;
        this.besitzer = besitzer;
    }

}
