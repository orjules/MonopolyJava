package programm.karten;

import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

public class BankGeld extends Ereigniskarte {

    int wert;

    protected BankGeld(String beschreibung, int wert) {
        super(beschreibung);
        this.wert = wert;
    }

    public int getWert(){
        return wert;
    }

    public String geldBewegen(Spielleiter spielleiter){
        spielleiter.kapitalÄndernVon(spielleiter.getGeradeDran(), wert);                // Ist simpel genug, dass man es im Org_Karten machen könnte
        return "Das Kapital von " + spielleiter.getGeradeDran().toString() + " ist nun " + spielleiter.getGeradeDran().getKapital();
    }
}
