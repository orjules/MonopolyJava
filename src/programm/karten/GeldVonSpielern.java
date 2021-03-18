package programm.karten;

import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

public class GeldVonSpielern extends NormaleKarte{

    int wert;
    int gesamt = 0;

    protected GeldVonSpielern(String beschreibung, Spielleiter spielleiter, int wert) {
        super(beschreibung, spielleiter);
        this.wert = wert;
    }

    public void aktionAusführen(){
        for (Spieler p : spielleiter.getAlleSpieler()){
            spielleiter.kapitalÄndernVon(p, wert);
            gesamt += wert;
        }
        spielleiter.kapitalÄndernVon(spielleiter.getGeradeDran(), gesamt);
    }

    @Override
    public String getBestätigung() {
        return spielleiter.getGeradeDran().toString() + " hat insgesamt " + gesamt + "€ bekommen.";
    }
}
