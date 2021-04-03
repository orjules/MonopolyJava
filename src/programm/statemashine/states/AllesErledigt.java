package programm.statemashine.states;

import programm.statemashine.NeuesAusgabeModell;

public class AllesErledigt implements State {
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
