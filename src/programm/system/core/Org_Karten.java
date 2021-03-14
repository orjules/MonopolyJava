package programm.system.core;

import programm.grundstücke.Grundbuch;
import programm.karten.*;
import programm.system.Darsteller;
import programm.system.Würfel;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

public class Org_Karten {

    Darsteller darsteller;
    Kartenmanager kartenmanager;
    Org_Hilfe orgHilfe;
    Grundbuch grundbuch;
    Spieler gradDran;
    Würfel würfel;
    Spielleiter spielleiter;

    public Org_Karten(Darsteller darsteller, Kartenmanager kartenmanager, Org_Hilfe orgHilfe, Grundbuch grundbuch,
                      Spieler gradDran, Würfel würfel, Spielleiter spielleiter) {
        this.darsteller = darsteller;
        this.kartenmanager = kartenmanager;
        this.orgHilfe = orgHilfe;
        this.grundbuch = grundbuch;
        this.gradDran = gradDran;
        this.würfel = würfel;
        this.spielleiter = spielleiter;
    }

    void karteAbarbeiten(Ereigniskarte karte){
        darsteller.ausgabe(karte.toString());
        // TODO Eingabe abfragen 'a' für bestätigen und 'ü' für übersicht
        if (BankGeld.class.equals(karte.getClass())) {
            int wert = ((BankGeld) karte).getWert();
            if (gradDran.getKapital() + wert < 0) {
                // Plus, weil auch negative Werte erlaubt sind
                orgHilfe.nichtGenugKapital(gradDran.getKapital() + wert);
            }
            darsteller.ausgabe(((BankGeld) karte).geldBewegen(spielleiter));
        } else if (FeldGehen.class.equals(karte.getClass())) {
            darsteller.ausgabe(((FeldGehen) karte).bewegenZu(spielleiter));
        } else if (Gefängnisfrei.class.equals(karte.getClass())) {// TODO
        } else if (GeldVonSpielern.class.equals(karte.getClass())) {
            ((GeldVonSpielern) karte).geldEinfordern(spielleiter);
        } else if (ZuWerkGehen.class.equals(karte.getClass())) {
            darsteller.ausgabe(((ZuWerkGehen) karte).losgehen(würfel, spielleiter, grundbuch));
        } else {
            throw new IllegalArgumentException("Typ von Ereigniskarte nicht erkannt.");
        }
    }
}
