package programm.karten;

import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

public class BankGeld extends NormaleKarte implements MussZahlen{

    int wert;

    protected BankGeld(String beschreibung, Spielleiter spielleiter, int wert) {
        super(beschreibung, spielleiter);
        this.wert = wert;
    }

    public void aktionAusführen(){
        spielleiter.kapitalÄndernVon(spielleiter.getGeradeDran(), wert);
    }

    @Override
    public int getWert() {
        return wert;
    }

    @Override
    public String getBestätigung() {
        Spieler gradDran = spielleiter.getGeradeDran();
        return gradDran.toString() +  " hat jetzt " + gradDran.getKapital() + "€.";
    }
}
