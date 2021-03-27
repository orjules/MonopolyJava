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
        if (gesamt == 0){
            throw new IllegalStateException("Es kann nicht sein, dass ein Spieler nichts von den anderen bekommt.");
        }
        if (istBekommen)
            return spielleiter.getGeradeDran().toString() + " hat insgesamt " + gesamt + "€ bekommen.";
        else
            return spielleiter.getGeradeDran().toString() + " hat insgesamt " + gesamt + "€ bezahlt.";
    }

    private void geldBekommen(){
        Spieler gradDran = spielleiter.getGeradeDran();
        for (Spieler p : spielleiter.getAlleSpieler()){
            if (p.equals(gradDran)){
                continue;
            }else{
                spielleiter.kapitalÄndernVon(p, -wert);
                gesamt += wert;
            }
        }
        spielleiter.kapitalÄndernVon(gradDran, gesamt);
    }

    private void geldZahlen(){
        Spieler gradDran = spielleiter.getGeradeDran();
        for (Spieler p : spielleiter.getAlleSpieler()){
            if (p.equals(gradDran)){
                continue;
            }else{
                spielleiter.kapitalÄndernVon(p, wert);
                gesamt -= wert;
            }
        }
        spielleiter.kapitalÄndernVon(gradDran, gesamt);
    }
}
