package programm.statemashine.io;

import programm.grundstücke.Grundstück;
import programm.system.spieler.Spieler;

public class A_BesetztesGrundstück implements Ausgabe{

    Grundstück grundstück;
    Spieler besitzer;

    public A_BesetztesGrundstück(Grundstück grundstück, Spieler besitzer) {
        this.grundstück = grundstück;
        this.besitzer = besitzer;
    }

    @Override
    public Object[] getAlles() {
        return new Object[]{grundstück, besitzer};
    }
}
