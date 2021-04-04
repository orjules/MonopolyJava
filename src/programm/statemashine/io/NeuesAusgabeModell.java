package programm.statemashine.io;

import programm.statemashine.enums.EingabeBeschreibungen;
import programm.statemashine.enums.Eingaben;
import programm.statemashine.enums.FehlerMeldungen;
import programm.system.spieler.Spieler;

import java.util.ArrayList;
import java.util.HashMap;

public class NeuesAusgabeModell {

    private FehlerMeldungen fehlerMeldung;  // Kann ich evtl rausnehmen
    private Spieler geradeDran;
    // Brett
    private HashMap<Eingaben, EingabeBeschreibungen> erlaubteEingaben;
    private ArrayList<Ausgabe> ausgaben;

    public NeuesAusgabeModell(Spieler geradeDran) {
        fehlerMeldung = null;
        ausgaben = new ArrayList<>();
        this.geradeDran = geradeDran;
        erlaubteEingaben = new HashMap<>();
        erlaubteEingaben.put(Eingaben.bestätigen, EingabeBeschreibungen.standard);
    }

    public void setFehlerMeldung(FehlerMeldungen neueFehlerMeldung){
        fehlerMeldung = neueFehlerMeldung;
    }

    public void addAusgabe(Ausgabe ausgabe){
        ausgaben.add(ausgabe);
    }

    public void addErlaubteEingabe(Eingaben eingabe, EingabeBeschreibungen beschreibung){
        erlaubteEingaben.clear();
        erlaubteEingaben.put(eingabe, beschreibung);
    }

    public FehlerMeldungen getFehlerMeldung() {
        return fehlerMeldung;
    }

    public Spieler getGeradeDran() {
        return geradeDran;
    }

    public HashMap<Eingaben, EingabeBeschreibungen> getErlaubteEingaben() {
        return erlaubteEingaben;
    }

    public ArrayList<Ausgabe> getAusgaben() {
        return ausgaben;
    }
}
