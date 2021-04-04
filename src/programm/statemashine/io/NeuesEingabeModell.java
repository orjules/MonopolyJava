package programm.statemashine.io;

import programm.statemashine.enums.Eingaben;

public class NeuesEingabeModell {
    Eingaben eingabe;

    public NeuesEingabeModell(Eingaben eingabe) {
        this.eingabe = eingabe;
    }

    public Eingaben getAntwort() {
        return eingabe;
    }
}
