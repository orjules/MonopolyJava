package programm.karten;

import programm.system.Felder;
import programm.system.spieler.Spielleiter;

public class FeldGehen extends NormaleKarte{

    Felder zielFeld;
    boolean istGefängnisKarte;

    protected FeldGehen(String beschreibung, Spielleiter spielleiter, Felder zielFeld) {
        super(beschreibung, spielleiter);
        this.zielFeld = zielFeld;
    }

    public void aktionAusführen(){
        Felder altesFeld = spielleiter.getGeradeDran().getAktuellePos();
        spielleiter.spielerSetzten(zielFeld);
        if (istGefängnisKarte){
            //return spielleiter.getGeradeDran().toString() + " sitzt nun im Gefängnis.";
        }else if(zielFeld.ordinal() < altesFeld.ordinal()){
            spielleiter.kapitalÄndernVon(spielleiter.getGeradeDran(), 200);
            //return spielleiter.getGeradeDran().toString() + " ist über Los gegangen und hat 200€ eingezogen.";
        }
        //return spielleiter.getGeradeDran().toString() + " ist nun auf " + zielFeld.name() + ".";
    }
}
