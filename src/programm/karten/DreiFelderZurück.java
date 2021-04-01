package programm.karten;

import programm.system.Felder;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

public class DreiFelderZurück extends NormaleKarte{


    protected DreiFelderZurück(String beschreibung, Spielleiter spielleiter) {
        super(beschreibung, spielleiter);
    }

    @Override
    public void aktionAusführen() {
        int neuePos = spielleiter.getGeradeDran().getAktuellePos().ordinal() - 3;
        if (neuePos < 0)
            neuePos += Felder.values().length;
        spielleiter.spielerSetzten(Felder.values()[neuePos]);
    }

    @Override
    public String getBestätigung() {
        Spieler gradDran = spielleiter.getGeradeDran();
        return gradDran.toString() + " ist nun auf " + gradDran.getAktuellePos().name();
    }
}
