package programm.statemashine.states;

import programm.newSystem.AusgabeModell;
import programm.statemashine.NeuesAusgabeModell;

public interface State {
    NeuesAusgabeModell werfen();
    NeuesAusgabeModell bestätigen();
    NeuesAusgabeModell übersicht();
    NeuesAusgabeModell zurück();
}
