package programm.statemashine.states;

import programm.statemashine.Kontext;
import programm.statemashine.io.AusgabeModell;
import programm.system.Würfel;
import programm.system.brett.Brett;
import programm.system.spieler.Spielleiter;

public abstract class State {

    Kontext kontext;
    Würfel würfel;
    Spielleiter spielleiter;
    Brett brett;

    public State(Kontext kontext, Würfel würfel, Spielleiter spielleiter, Brett brett) {
        this.kontext = kontext;
        this.würfel = würfel;
        this.spielleiter = spielleiter;
        this.brett = brett;
    }

    public AusgabeModell werfen() {
        return null;
    }

    public AusgabeModell bestätigen() {
        return null;
    }

    public AusgabeModell übersicht() {
        return null;
    }

    public AusgabeModell zurück() {
        return null;
    }
}
