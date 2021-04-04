package programm.statemashine.states;

import programm.grundstücke.Grundbuch;
import programm.grundstücke.Grundstück;
import programm.karten.Ereigniskarte;
import programm.karten.Kartenmanager;
import programm.statemashine.Kontext;
import programm.statemashine.io.*;
import programm.statemashine.enums.FehlerMeldungen;
import programm.system.Felder;
import programm.system.Würfel;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

public class ErsterWurf implements State {

    Kontext kontext;
    Spielleiter spielleiter;
    Würfel würfel;
    Grundbuch grundbuch;
    Kartenmanager kartenmanager;

    public ErsterWurf(Kontext kontext, Spielleiter spielleiter, Würfel würfel, Grundbuch grundbuch, Kartenmanager kartenmanager) {
        this.kontext = kontext;
        this.spielleiter = spielleiter;
        this.würfel = würfel;
        this.grundbuch = grundbuch;
        this.kartenmanager = kartenmanager;
    }

    @Override
    public NeuesAusgabeModell werfen() {
        int[] wurf = würfel.würfeln();
        spielleiter.spielerBewegen(wurf[2]);
        return gewürfeltErstellen(wurf);
    }

    @Override
    public NeuesAusgabeModell bestätigen() {
        return gehtNichtErstellen();
    }

    @Override
    public NeuesAusgabeModell übersicht() {
        return gehtNichtErstellen();
    }

    @Override
    public NeuesAusgabeModell zurück() {
        return gehtNichtErstellen();
    }

    private NeuesAusgabeModell gehtNichtErstellen(){
        NeuesAusgabeModell modell = new NeuesAusgabeModell(spielleiter.getGeradeDran());
        // Brett Hinzufügen
        modell.setFehlerMeldung(FehlerMeldungen.gehtGeradeNicht);
        return modell;
    }
    private NeuesAusgabeModell gewürfeltErstellen(int[] wurf){
        NeuesAusgabeModell modell = new NeuesAusgabeModell(spielleiter.getGeradeDran());
        // Brett hinzufügen
        A_Gewürfelt gewürfelt = new A_Gewürfelt(wurf);
        modell.addAusgabe(gewürfelt);
        modell.addAusgabe(feldBestimmen());
        return modell;
    }
    private Ausgabe feldBestimmen(){
        Felder neuesFeld = spielleiter.getGeradeDran().getAktuellePos();
        Grundstück grundstück = grundbuch.grundstückVon(neuesFeld);
        if (grundstück != null){
            Spieler besitzer = grundbuch.getBesitzerVon(grundstück);
            if (besitzer != null){
                kontext.setAktuellerState(kontext.getBesetzesGrundstück());
                return new A_BesetztesGrundstück(grundstück, besitzer);
            }else {
                kontext.setAktuellerState(kontext.getFreiesGrundstück());
                return new A_FreiesGrundstück(grundstück);
            }
        }
        Ereigniskarte karte = kartenmanager.karteZiehen(neuesFeld);
        if (karte != null){
            kontext.setAktuellerState(kontext.getKarteZiehen());
            return new A_KarteZiehen(karte);
        }
        throw new IllegalStateException("Es muss immer entweder ein Grundstück oder eine Karte sein");
    }
}
