package programm.statemashine;

import programm.newSystem.MöglicheEingaben;

public class NeuesEingabeModell {
    Eingaben eingabe;

    public NeuesEingabeModell(Eingaben eingabe) {
        this.eingabe = eingabe;
    }

    public Eingaben getAntwort() {
        return eingabe;
    }
}
