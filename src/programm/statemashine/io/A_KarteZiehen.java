package programm.statemashine.io;

import programm.karten.Ereigniskarte;

public class A_KarteZiehen implements Ausgabe{

    Ereigniskarte karte;

    public A_KarteZiehen(Ereigniskarte karte) {
        this.karte = karte;
    }

    @Override
    public Object[] getAlles() {
        return new Object[0];
    }
}
