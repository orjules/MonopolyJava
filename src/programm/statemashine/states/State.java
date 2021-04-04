package programm.statemashine.states;

import programm.statemashine.io.NeuesAusgabeModell;

public interface State {
    NeuesAusgabeModell werfen();
    NeuesAusgabeModell bestätigen();
    NeuesAusgabeModell übersicht();
    NeuesAusgabeModell zurück();
}
