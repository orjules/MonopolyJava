package programm.statemashine;

import programm.newSystem.AusgabeModell;

public interface State {
    AusgabeModell werfen();
    AusgabeModell bestätigen();
    AusgabeModell übersicht();
    AusgabeModell zurück();
}
