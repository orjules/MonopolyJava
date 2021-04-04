package programm.statemashine.states;

import programm.grundstücke.Grundbuch;
import programm.grundstücke.Grundstück;
import programm.karten.Ereigniskarte;
import programm.karten.Kartenmanager;
import programm.statemashine.Kontext;
import programm.statemashine.io.*;
import programm.system.Felder;
import programm.system.Würfel;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

public class S_ErsterWurf implements State {

    Kontext kontext;
    Spielleiter spielleiter;
    Würfel würfel;
    Grundbuch grundbuch;
    Kartenmanager kartenmanager;

    public S_ErsterWurf(Kontext kontext, Spielleiter spielleiter, Würfel würfel, Grundbuch grundbuch, Kartenmanager kartenmanager) {
        this.kontext = kontext;
        this.spielleiter = spielleiter;
        this.würfel = würfel;
        this.grundbuch = grundbuch;
        this.kartenmanager = kartenmanager;
    }

    @Override
    public AusgabeModell werfen() {
        int[] wurf = würfel.würfeln();
        spielleiter.spielerBewegen(wurf[2]);
        // neues AusgabeModell erstellen
        return null;
    }

    @Override
    public AusgabeModell bestätigen() {
        return null;
    }

    @Override
    public AusgabeModell übersicht() {
        return null;
    }

    @Override
    public AusgabeModell zurück() {
        return null;
    }

}
