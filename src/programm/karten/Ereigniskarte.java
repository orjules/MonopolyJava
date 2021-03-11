package programm.karten;

import programm.system.interfaces.IEreigniskarte;

public class Ereigniskarte implements IEreigniskarte {

    String beschreibung;

    public Ereigniskarte(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getBeschreibung() {
        return beschreibung;
    }
}
