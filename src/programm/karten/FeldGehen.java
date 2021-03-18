package programm.karten;

import programm.system.Felder;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

public class FeldGehen extends NormaleKarte{

    Felder zielFeld;
    Felder altesFeld;
    boolean istGefängnisKarte;

    protected FeldGehen(String beschreibung, Spielleiter spielleiter, Felder zielFeld) {
        super(beschreibung, spielleiter);
        this.zielFeld = zielFeld;
    }

    public void aktionAusführen(){
        altesFeld = spielleiter.getGeradeDran().getAktuellePos();
        spielleiter.spielerSetzten(zielFeld);
        if (istGefängnisKarte){
            spielleiter.einsperrenVon(spielleiter.getGeradeDran());
        }else if(zielFeld.ordinal() < altesFeld.ordinal()){
            spielleiter.kapitalÄndernVon(spielleiter.getGeradeDran(), 200);
        }
    }

    @Override
    public String getBestätigung() {
        Spieler gradDran = spielleiter.getGeradeDran();
        if (istGefängnisKarte){
            return gradDran.toString() + " sitzt nun im Gefängnis.";
        }else if(zielFeld.ordinal() < altesFeld.ordinal()){
            return gradDran.toString() + " ist über Los gegangen und hat 200€ eingezogen.";
        }else if (gradDran.getAktuellePos().equals(zielFeld)){
            return spielleiter.getGeradeDran().toString() + " ist nun auf " + zielFeld.name() + ".";
        }else
            throw new IllegalStateException("Etwas ist beim setzten des Spilers schiefgelaufen!");
    }
}
