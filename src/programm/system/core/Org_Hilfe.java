package programm.system.core;

import programm.grundstücke.Grundbuch;
import programm.system.Darsteller;
import programm.system.spieler.Spielleiter;

import java.util.ArrayList;
import java.util.Arrays;

public class Org_Hilfe {
    Darsteller darsteller;
    Grundbuch grundbuch;
    Spielleiter spielleiter;

    public Org_Hilfe(Darsteller darsteller, Grundbuch grundbuch, Spielleiter spielleiter) {
        this.darsteller = darsteller;
        this.grundbuch = grundbuch;
        this.spielleiter = spielleiter;
    }

    void übersichtAnzeigen(){
        darsteller.ausgabe("Dein momentanes Kapital ist: " + spielleiter.getGeradeDran().getKapital() + "€.");
        darsteller.grundstückÜbersicht(grundbuch.alleGrundstückeVon(spielleiter.getGeradeDran()));
        darsteller.ausgabe("DEBUG: Häuser ver-/kaufen und Handel ist noch nicht implementiert.");
    }

    // Später wichtig, wenn man etwas kaufen/ bezahlen will aber nicht genug Geld hat, soll man die Verwaltung öffnen können
    void nichtGenugKapital(int zuZahlen){
        while (true){
            // 1. ausrechnen wie viel zu wenig
            int neuesKapital = spielleiter.getGeradeDran().getKapital() - zuZahlen;
            // 2. Überprüfen ob abgebrochen werden kann
            if (neuesKapital > 0){
                return;
            }
            // 3. sagen, wie viel zu wenig und sagen man kann zur Übersicht oder aufgeben und gleich verabrbeiten
            switch (darsteller.eingabeFragen("Dir fehlen " + (-neuesKapital) + "€. Du muss in der Übersicht " +
                            "etwas verkaufen oder das Spiel aufgeben.\n'ü' um die Übersicht zu öffnen\n'x' um aufzugeben",
                    new ArrayList<String>(Arrays.asList("ü", "x")))){
                case "ü":
                    übersichtAnzeigen();
                    break;
                case "x":
                    spielleiter.aufgeben();
                    return;
            }
        }
    }
    public void bezahlenOderZuWenigGeld(String initial, String textFürA, int wert){
        if (spielleiter.getGeradeDran().getKapital() + wert < 0){
            darsteller.ausgabe(initial);
            nichtGenugKapital(-wert);
            if (spielleiter.jemandHatGeradeAufgegeben())
                return;
        }
        String text = initial + textFürA;
        text += "\n'ü' um die Übersicht zu öffnen";
        while (true){
            String eingabe = darsteller.eingabeFragen(text, new ArrayList<String>(Arrays.asList("a", "ü")));
            switch (eingabe){
                case "a":
                    return;
                case "ü":
                    übersichtAnzeigen();
                    break;
            }
        }
    }
}
