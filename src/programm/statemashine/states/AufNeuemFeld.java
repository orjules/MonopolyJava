package programm.statemashine.states;

import programm.statemashine.Kontext;
import programm.statemashine.io.AusgabeModell;
import programm.system.Würfel;
import programm.system.brett.Brett;
import programm.system.spieler.Spielleiter;

public class AufNeuemFeld extends State{

    public AufNeuemFeld(Kontext kontext, Würfel würfel, Spielleiter spielleiter, Brett brett) {
        super(kontext, würfel, spielleiter, brett);
    }

    @Override
    public AusgabeModell werfen(){
        AusgabeModell ausgabeModell = kontext.getLetzteAusgabe();
        if (ausgabeModell != null){
            return ausgabeModell;
        }else
            throw new IllegalStateException("Wenn AufNeuemFeld der aktuelleState ist muss es ein " +
                    "letztes AusgabeModell geben!");
    }
}
