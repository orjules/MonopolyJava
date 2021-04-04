package programm.statemashine.states;

import programm.statemashine.Kontext;
import programm.statemashine.io.NeuesAusgabeModell;

public class AllesErledigt implements State {
    Kontext kontext;

    public AllesErledigt(Kontext kontext) {
        this.kontext = kontext;
    }

    @Override
    public NeuesAusgabeModell werfen() {
        return null;
    }

    @Override
    public NeuesAusgabeModell bestätigen() {
        return null;
    }

    @Override
    public NeuesAusgabeModell übersicht() {
        return null;
    }

    @Override
    public NeuesAusgabeModell zurück() {
        return null;
    }
}
