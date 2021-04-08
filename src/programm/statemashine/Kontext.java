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
    State allesErledigt;

    State aktuellerState;
    State letzterState;
    AusgabeModell letzteAusgabe;

    public void statesFüllen(AufNeuemFeld aufNeuemFeld, ErsterWurf ersterWurf, Übersicht übersicht,
                             AllesErledigt allesErledigt) {
        this.aufNeuemFeld = aufNeuemFeld;
        this.ersterWurf = ersterWurf;
        this.übersicht = übersicht;
        this.allesErledigt = allesErledigt;

        aktuellerState = ersterWurf;
    }

    @Override
    public AusgabeModell erstelleModell(EingabeModell eingabe) {
        Eingaben eingaben = eingabe.getAntwort();
        AusgabeModell ausgabeModell = switch (eingaben) {
            case werfen -> aktuellerState.werfen();
            case bestätigen -> aktuellerState.bestätigen();
            case übersicht -> aktuellerState.übersicht();
            case zurück -> aktuellerState.zurück();
        };
        letzteAusgabe = ausgabeModell;
        return ausgabeModell;
    }

    public void setAktuellerState(State neuerState) {
        letzterState = aktuellerState;
        aktuellerState = neuerState;
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

    public State getAllesErledigt() {
        return allesErledigt;
    }

    public State getLetzterState() {
        return letzterState;
    }

    public AusgabeModell getLetzteAusgabe() {
        return letzteAusgabe;
    }
}
