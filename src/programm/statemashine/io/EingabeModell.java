package programm.statemashine.io;

import programm.statemashine.enums.Eingaben;

public class EingabeModell {
    Eingaben eingabe;

    public EingabeModell(Eingaben eingabe) {
        this.eingabe = eingabe;
    }

    public Eingaben getAntwort() {
        return eingabe;
    }
}
