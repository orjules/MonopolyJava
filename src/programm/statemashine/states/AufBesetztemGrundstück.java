package programm.statemashine.states;

import programm.statemashine.Kontext;
import programm.system.Würfel;
import programm.system.brett.Brett;
import programm.system.spieler.Spielleiter;

public class AufBesetztemGrundstück extends State {

    public AufBesetztemGrundstück(Kontext kontext, Würfel würfel, Spielleiter spielleiter, Brett brett) {
        super(kontext, würfel, spielleiter, brett);
    }
}
