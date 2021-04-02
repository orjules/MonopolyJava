package programm.newSystem;

import programm.grundstücke.Grundbuch;
import programm.grundstücke.Grundstück;
import programm.system.Würfel;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

import java.util.ArrayList;

public class ErsterVerwalter extends Verwalter implements ControllerGrenze {

    Würfel würfel;
    Spielleiter spielleiter;
    Grundbuch grundbuch;

    public ErsterVerwalter(Würfel würfel, Spielleiter spielleiter, Grundbuch grundbuch) {
        this.würfel = würfel;
        this.spielleiter = spielleiter;
        this.grundbuch = grundbuch;
    }

    @Override
    public AusgabeModell modellErstellen(EingabeModell eingabe) {
        ArrayList<MöglicheAusgaben> ausgaben = new ArrayList<>();

        if (eingabe.getAntwort().equals(MöglicheEingaben.start)){
            ausgaben.add(MöglicheAusgaben.würfeln);
            return new AusgabeModell(ausgaben);
        }

        int[] wurf = würfel.würfeln();
        spielleiter.spielerBewegen(wurf[2]);
        Grundstück aktuellesGrundstück = grundbuch.grundstückVon(spielleiter.getGeradeDran().getAktuellePos());
        if (aktuellesGrundstück != null){
            Spieler besitzer = grundbuch.getBesitzerVon(aktuellesGrundstück);
            if (besitzer == null){
                ausgaben.add(MöglicheAusgaben.kaufen);
            }else {
                ausgaben.add(MöglicheAusgaben.mieteZahlen);
            }
        }

        ausgaben.add(MöglicheAusgaben.übersicht);
        return new AusgabeModell(ausgaben);
    }
}
