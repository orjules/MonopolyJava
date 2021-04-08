package programm.statemashine.io;

import programm.statemashine.enums.Ausgaben;
import programm.statemashine.enums.EingabeBeschreibungen;
import programm.statemashine.enums.Eingaben;
import programm.system.brett.Brett;
import programm.system.brett.Feld;
import programm.system.spieler.Spieler;

import java.util.ArrayList;
import java.util.HashMap;

public class AusgabeModell {

    private Feld neuesFeld;
    private Spieler geradeDran;
    private Brett brett;
    int[] letzterWurf;
    private HashMap<Eingaben, EingabeBeschreibungen> erlaubteEingaben;
    private ArrayList<Ausgaben> ausgaben;

    public AusgabeModell(Feld neuesFeld, Spieler geradeDran, Brett brett, int[] letzterWurf,
                         HashMap<Eingaben, EingabeBeschreibungen> erlaubteEingaben, ArrayList<Ausgaben> ausgaben) {
        this.neuesFeld = neuesFeld;
        this.geradeDran = geradeDran;
        this.brett = brett;
        this.letzterWurf = letzterWurf;
        this.erlaubteEingaben = erlaubteEingaben;
        this.ausgaben = ausgaben;
    }

    public void addErlaubteEingabe(Eingaben eingabe, EingabeBeschreibungen beschreibung){
        erlaubteEingaben.clear();
        erlaubteEingaben.put(eingabe, beschreibung);
    }

    public Feld getFeld() {
        return neuesFeld;
    }

    public Brett getBrett() {
        return brett;
    }

    public Spieler getGeradeDran() {
        return geradeDran;
    }

    public HashMap<Eingaben, EingabeBeschreibungen> getErlaubteEingaben() {
        return erlaubteEingaben;
    }

    public ArrayList<Ausgaben> getAusgaben() {
        return ausgaben;
    }

    public int[] getLetzterWurf() {
        return letzterWurf;
    }
}