package programm.statemashine.states;

import programm.statemashine.Kontext;
import programm.statemashine.io.AusgabeModell;

public class S_Übersicht implements State {

    Kontext kontext;

    public S_Übersicht(Kontext kontext) {
        this.kontext = kontext;
    }

    @Override
    public AusgabeModell werfen() {
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
