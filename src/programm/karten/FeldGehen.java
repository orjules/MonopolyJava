package programm.karten;

import programm.system.Felder;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

public class FeldGehen extends NormaleKarte{

    Felder zielFeld;
    Felder altesFeld;
    boolean istGefängnisKarte = false;
    boolean wurdeGestzt = false;

    protected FeldGehen(String beschreibung, Spielleiter spielleiter, Felder zielFeld, boolean istGefängnisKarte) {
        super(beschreibung, spielleiter);
        this.zielFeld = zielFeld;
        this.istGefängnisKarte = istGefängnisKarte;
    }

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
        wurdeGestzt = true;
    }

    @Override
    public String getBestätigung() {
        Spieler gradDran = spielleiter.getGeradeDran();
        if (istGefängnisKarte && wurdeGestzt){
            wurdeGestzt = false;
            return gradDran.toString() + " sitzt nun im Gefängnis.";
        }else if(altesFeld != null && zielFeld.ordinal() < altesFeld.ordinal()){
            wurdeGestzt = false;
            return gradDran.toString() + " ist über Los gegangen und hat 200€ eingezogen.";
        }else if (gradDran.getAktuellePos().equals(zielFeld) && wurdeGestzt){
            wurdeGestzt = false;
            return spielleiter.getGeradeDran().toString() + " ist nun auf " + zielFeld.name() + ".";
        }else
            throw new IllegalStateException("Etwas ist beim setzten des Spielers schiefgelaufen!");
    }
}
