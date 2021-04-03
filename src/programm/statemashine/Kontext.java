package programm.statemashine;

import programm.statemashine.enums.Eingaben;
import programm.statemashine.states.*;

public class Kontext {

    State allesErledigt;
    State besetzesGrundstück;
    State ersterWurf;
    State freiesGrundstück;
    State imGefängnis;
    State karteZiehen;
    State versteigern;
    State zuWenigGeld;
    State übersicht;

    State aktuellerState;

    public void statesReingeben(State allesErledigt, State besetzesGrundstück, State ersterWurf, State freiesGrundstück,
                                State imGefängnis, State karteZiehen, State versteigern, State zuWenigGeld, State übersicht) {
        this.allesErledigt = allesErledigt;
        this.besetzesGrundstück = besetzesGrundstück;
        this.ersterWurf = ersterWurf;
        this.freiesGrundstück = freiesGrundstück;
        this.imGefängnis = imGefängnis;
        this.karteZiehen = karteZiehen;
        this.versteigern = versteigern;
        this.zuWenigGeld = zuWenigGeld;
        this.übersicht = übersicht;

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

    public State getKarteZiehen() {
        return karteZiehen;
    }

    public State getVersteigern() {
        return versteigern;
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
