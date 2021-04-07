package programm.statemashine;

import programm.consoleUI.KontextGrenze;
import programm.statemashine.enums.Eingaben;
import programm.statemashine.io.AusgabeModell;
import programm.statemashine.io.EingabeModell;
import programm.statemashine.states.*;

public class Kontext implements KontextGrenze {

    State aufNeuemFeld;
    State übersicht;

    State aktuellerState;

    public void statesReingeben(State aufNeuemFeld, State ersterWurf, State übersicht) {
        this.aufNeuemFeld = aufNeuemFeld;
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

    public State getÜbersicht() {
        return übersicht;
    }

    public State getAktuellerState() {
        return aktuellerState;
    }
}
