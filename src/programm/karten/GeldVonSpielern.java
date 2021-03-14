package programm.karten;

import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

public class GeldVonSpielern extends Ereigniskarte{

    int wert;

    protected GeldVonSpielern(String beschreibung, int wert) {
        super(beschreibung);
        this.wert = wert;
    }

    public String geldEinfordern(Spielleiter spielleiter){
        int gesamt = 0;
        for (Spieler p : spielleiter.getAlleSpieler()){
            spielleiter.kapitalÄndernVon(p, wert);
            gesamt += wert;
        }
        spielleiter.kapitalÄndernVon(spielleiter.getGeradeDran(), gesamt);
        return spielleiter.getGeradeDran().toString() + " hat insgesamt " + gesamt + "€ bekommen.";
    }

}
