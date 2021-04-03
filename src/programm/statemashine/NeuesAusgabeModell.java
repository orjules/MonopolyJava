package programm.statemashine;

import programm.statemashine.enums.AusgabeBeschreibungen;
import programm.statemashine.enums.Eingaben;
import programm.statemashine.enums.FehlerMeldungen;
import programm.system.spieler.Spieler;

import java.util.HashMap;

public class NeuesAusgabeModell {

    private FehlerMeldungen fehlerMeldung;
    private Spieler geradeDran;
    // Brett
    private HashMap<Eingaben, AusgabeBeschreibungen> erlaubteEingaben;

    public NeuesAusgabeModell(Spieler geradeDran) {
        this.geradeDran = geradeDran;
        erlaubteEingaben = new HashMap<>();
        erlaubteEingaben.put(Eingaben.bestätigen, AusgabeBeschreibungen.standard);
    }

    public void setFehlerMeldung(FehlerMeldungen neueFehlerMeldung){
        fehlerMeldung = neueFehlerMeldung;
    }

    public void addErlaubteEingabe(Eingaben eingabe, AusgabeBeschreibungen beschreibung){
        erlaubteEingaben.clear();
        erlaubteEingaben.put(eingabe, beschreibung);
    }

    public FehlerMeldungen getFehlerMeldung() {
        return fehlerMeldung;
    }

    public Spieler getGeradeDran() {
        return geradeDran;
    }

    public HashMap<Eingaben, AusgabeBeschreibungen> getErlaubteEingaben() {
        return erlaubteEingaben;
    }
}
