package programm.karten;

import programm.system.spieler.Spielleiter;

public class BankGeld extends NormaleKarte {

    int wert;

    protected BankGeld(String beschreibung, Spielleiter spielleiter, int wert) {
        super(beschreibung, spielleiter);
        this.wert = wert;
    }

    public void aktionAusführen(){
        spielleiter.kapitalÄndernVon(spielleiter.getGeradeDran(), wert);                // Ist simpel genug, dass man es im Org_Karten machen könnte
        //return "Das Kapital von " + spielleiter.getGeradeDran().toString() + " ist nun " + spielleiter.getGeradeDran().getKapital();
    }
}
