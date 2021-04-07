package programm.statemashine.io;

import programm.statemashine.enums.EingabeBeschreibungen;
import programm.statemashine.enums.Eingaben;
import programm.system.brett.Brett;
import programm.system.spieler.Spieler;

import java.util.HashMap;

public class AusgabeModell {

    private Spieler geradeDran;
    private Brett brett;
    int[] letzterWurf;
    private HashMap<Eingaben, EingabeBeschreibungen> erlaubteEingaben;

    public void addErlaubteEingabe(Eingaben eingabe, EingabeBeschreibungen beschreibung){
        erlaubteEingaben.clear();
        erlaubteEingaben.put(eingabe, beschreibung);
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

}
