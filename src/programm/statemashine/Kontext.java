package programm.statemashine;

import programm.statemashine.states.*;

public class Kontext {

    State allesErledigt;
    State besetzesGrundstück;
    State ersterWurf;
    State freiesGrundstück;
    State imGefängnis;
    State zuWenigGeld;
    State übersicht;

    State aktuellerState;

    public Kontext() {
        allesErledigt = new AllesErledigt(this);
        besetzesGrundstück = new BesetzesGrundstück(this);
        ersterWurf = new ErsterWurf(this);
        freiesGrundstück = new FreiesGrundstück(this);
        imGefängnis = new ImGefängnis(this);
        zuWenigGeld = new ZuWenigGeld(this);
        übersicht = new Übersicht(this);

        aktuellerState = ersterWurf;
    }

    public NeuesAusgabeModell erstelleModell(NeuesEingabeModell eingabe) {
        Eingaben eingaben = eingabe.getAntwort();
        return switch (eingaben) {
            case werfen -> aktuellerState.werfen();
            case bestätigen -> aktuellerState.bestätigen();
            case übersicht -> aktuellerState.übersicht();
            case zurück -> aktuellerState.zurück();
        };
    }

    public State getState() {
        return aktuellerState;
    }

    public void setState(State newState){
        aktuellerState = newState;
    }

    public State getAllesErledigt() {
        return allesErledigt;
    }

    public State getBesetzesGrundstück() {
        return besetzesGrundstück;
    }

    public State getErsterWurf() {
        return ersterWurf;
    }

    public State getFreiesGrundstück() {
        return freiesGrundstück;
    }

    public State getImGefängnis() {
        return imGefängnis;
    }

    public State getZuWenigGeld() {
        return zuWenigGeld;
    }

    public State getÜbersicht() {
        return übersicht;
    }

    public State getAktuellerState() {
        return aktuellerState;
    }
}
