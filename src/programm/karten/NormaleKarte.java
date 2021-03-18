package programm.karten;

import programm.system.spieler.Spielleiter;

public abstract class NormaleKarte extends Ereigniskarte{

    Spielleiter spielleiter;

    protected NormaleKarte(String beschreibung, Spielleiter spielleiter) {
        super(beschreibung);
        this.spielleiter = spielleiter;
    }

    public abstract void aktionAusführen();
}
