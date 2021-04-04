package programm.statemashine.states;

import programm.statemashine.io.AusgabeModell;

public interface State {
    AusgabeModell werfen();
    AusgabeModell bestätigen();
    AusgabeModell übersicht();
    AusgabeModell zurück();
}
