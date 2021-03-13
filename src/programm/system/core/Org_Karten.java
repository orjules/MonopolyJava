package programm.system.core;

import programm.karten.Ereigniskarte;
import programm.karten.Kartenmanager;
import programm.system.Darsteller;
import programm.system.spieler.Spieler;

public class Org_Karten {

    Darsteller darsteller;
    Kartenmanager kartenmanager;
    Org_Hilfe orgHilfe;
    Spieler gradDran;

    public Org_Karten(Darsteller darsteller, Kartenmanager kartenmanager, Org_Hilfe orgHilfe, Spieler gradDran) {
        this.darsteller = darsteller;
        this.kartenmanager = kartenmanager;
        this.orgHilfe = orgHilfe;
        this.gradDran = gradDran;
    }

    void karteAbarbeiten(Ereigniskarte karte){
        // TODO Je nach Karte etwas anders machen
        darsteller.ausgabe(karte.getBeschreibung());
    }
}
