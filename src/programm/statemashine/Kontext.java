package programm.statemashine;

import programm.statemashine.states.ErsterWurf;
import programm.statemashine.states.State;

public class Kontext {

    ErsterWurf ersterWurf = new ErsterWurf();

    State aktuellerState = ersterWurf;

    public NeuesAusgabeModell erstelleModell(NeuesEingabeModell eingabe) {
        Eingaben eingaben = eingabe.getAntwort();
        return switch (eingaben) {
            case werfen -> aktuellerState.werfen();
            case bestätigen -> aktuellerState.bestätigen();
            case übersicht -> aktuellerState.übersicht();
            case zurück -> aktuellerState.zurück();
        };
    }
}
