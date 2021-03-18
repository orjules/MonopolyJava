package programm.karten;

import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

public class GeldVonSpielern extends NormaleKarte{

    int wert;
    int gesamt = 0;
    boolean istBekommen;

    protected GeldVonSpielern(String beschreibung, Spielleiter spielleiter, int wert, boolean istBekommen) {
        super(beschreibung, spielleiter);
        this.wert = wert;
        this.istBekommen = istBekommen;
    }

    public void aktionAusführen(){
        if (istBekommen)
            geldBekommen();
        else
            geldZahlen();
    }

    @Override
    public String getBestätigung() {
        return spielleiter.getGeradeDran().toString() + " hat insgesamt " + gesamt + "€ bekommen.";
    }

    private void geldBekommen(){
        for (Spieler p : spielleiter.getAlleSpieler()){
            spielleiter.kapitalÄndernVon(p, -wert);
            gesamt += wert;
        }
        spielleiter.kapitalÄndernVon(spielleiter.getGeradeDran(), gesamt);
    }

    private void geldZahlen(){
        for (Spieler p : spielleiter.getAlleSpieler()){
            spielleiter.kapitalÄndernVon(p, wert);
            gesamt -= wert;
        }
        spielleiter.kapitalÄndernVon(spielleiter.getGeradeDran(), gesamt);
    }
}
