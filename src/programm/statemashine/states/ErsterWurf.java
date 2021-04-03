package programm.statemashine.states;

import programm.newSystem.AusgabeModell;
import programm.statemashine.Kontext;
import programm.statemashine.NeuesAusgabeModell;
import programm.statemashine.enums.FehlerMeldungen;
import programm.system.spieler.Spielleiter;

public class ErsterWurf implements State {

    Kontext kontext;
    Spielleiter spielleiter;

    public ErsterWurf(Kontext kontext, Spielleiter spielleiter) {
        this.kontext = kontext;
        this.spielleiter = spielleiter;
    }

    @Override
    public NeuesAusgabeModell werfen() {
        return null;
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
}
