package programm.statemashine;

import programm.consoleUI.KontextGrenze;
import programm.statemashine.enums.Eingaben;
import programm.statemashine.io.AusgabeModell;
import programm.statemashine.io.EingabeModell;
import programm.statemashine.states.*;

public class Kontext implements KontextGrenze {

    State aufNeuemFeld;
    State ersterWurf;
    State übersicht;

    State aktuellerState;

    public void statesFüllen(State aufNeuemFeld, State ersterWurf, State übersicht) {
        this.aufNeuemFeld = aufNeuemFeld;
        this.ersterWurf = ersterWurf;
        this.übersicht = übersicht;

        aktuellerState = ersterWurf;
    }

    @Override
    public AusgabeModell erstelleModell(EingabeModell eingabe) {
        Eingaben eingaben = eingabe.getAntwort();
        return switch (eingaben) {
            case werfen -> aktuellerState.werfen();
            case bestätigen -> aktuellerState.bestätigen();
            case übersicht -> aktuellerState.übersicht();
            case zurück -> aktuellerState.zurück();
        };
    }

    public void setAktuellerState(State aktuellerState) {
        this.aktuellerState = aktuellerState;
    }

    public State getAufNeuemFeld() {
        return aufNeuemFeld;
    }

    public State getErsterWurf() {
        return ersterWurf;
    }

    public State getÜbersicht() {
        return übersicht;
    }

    public State getAktuellerState() {
        return aktuellerState;
    }
}
