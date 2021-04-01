package programm.system.core;

import programm.grundstücke.Fälle;
import programm.grundstücke.GrammatikHandler;
import programm.grundstücke.Grundbuch;
import programm.grundstücke.Grundstück;
import programm.system.Darsteller;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

import java.util.ArrayList;
import java.util.Arrays;

public class Org_Grundstücke {

    Spielleiter spielleiter;
    Darsteller darsteller;
    Grundbuch grundbuch;
    Org_Hilfe orgHilfe;
    Spieler gradDran;

    public Org_Grundstücke(Spielleiter spielleiter, Darsteller darsteller, Grundbuch grundbuch, Org_Hilfe orgHilfe, Spieler gradDran) {
        this.spielleiter = spielleiter;
        this.darsteller = darsteller;
        this.grundbuch = grundbuch;
        this.orgHilfe = orgHilfe;
        this.gradDran = gradDran;
    }

    void kaufenVon(Grundstück grundstück){
        while (true){
            darsteller.umbruch();
            darsteller.ausgabe(
                    "Der Kaufpreis " + GrammatikHandler.getkleineArtikel(grundstück, Fälle.Genitiv)
                            + grundstück.getName() + " ist " + grundstück.getGrundstücksWert() + "€. Dein Kapital ist "
                            + gradDran.getKapital() + "€.");
            // Checken ob genug Geld für den Kauf vorhanden ist und danach die Ausgabe anpassen
            int neuesKapital = gradDran.getKapital() - grundstück.getGrundstücksWert();
            String frage = "";
            ArrayList<String> erlaubteEingaben = new ArrayList<>();
            if (neuesKapital < 0){
                frage += "Du besitzt nicht genug Geld um das Grundstück zu kaufen. In der Übersicht kannst du etwas verkaufen.\n";
            }else {
                frage += "'a' um das Grundstück zu kaufen\n";
                erlaubteEingaben.add("a");
            }
            // Egal was davor passiert, man kann immer verwalten und nein sagen
            frage += "'n' um das Grundstück nicht zu kaufen\n";
            erlaubteEingaben.add("n");
            frage += "'ü' um die Übersicht zu öffnen";
            erlaubteEingaben.add("ü");
            switch (darsteller.eingabeFragen(frage, erlaubteEingaben)){
                case "a":
                    spielleiter.kapitalÄndernVon(gradDran, -grundstück.getGrundstücksWert());
                    grundbuch.übertragenAn(grundstück, gradDran);
                    darsteller.ausgabe(GrammatikHandler.getGroßeArtikel(grundstück, Fälle.Nominativ) +
                            grundstück.getName() + " gehört nun " + gradDran.getName() + "."
                            + " Dein neues Kapital ist: " + gradDran.getKapital() + "€.");
                    return;
                case "n":
                    grundstückVersteigern(grundstück);
                    return;
                case "ü":
                    orgHilfe.übersichtAnzeigen();
                    break;
                default:
            }
        }
    }

    void mieteZahlenBei(Grundstück grundstück, Spieler besitzer, int miete){
        while (true){
            // Sagen was los ist
            darsteller.umbruch();
            darsteller.ausgabe("Die Miete " + GrammatikHandler.getkleineArtikel(grundstück, Fälle.Genitiv) + " " +
                    grundstück.getName() + " ist " + miete + "€.");

            // Bestätigung fragen, bzw weiterleiten, wenn man zu wenig Geld hat
            String frage = "";
            ArrayList<String> erlaubteEingaben = new ArrayList<>();
            if (gradDran.getKapital() - miete < 0){
                orgHilfe.nichtGenugKapital(miete);
                // Hier hat der Spieler evtl aufgegeben, in dem Fall soll das Bezahlen abgebrochen werden, sonst bezahlen lassen
                if (!gradDran.equals(spielleiter.getGeradeDran()) || !spielleiter.spielLäuft()){
                    return;
                }
            }
            // Hier muss der Spieler genug Geld haben oder er hat aufgegeben
            switch (darsteller.eingabeFragen("'a' um das Bezahlen zu bestätigen\n'ü' um die Übersicht zu öffnen",
                    new ArrayList<String>(Arrays.asList("a", "ü")))){
                case "a":
                    spielleiter.geldÜbertragen(gradDran, besitzer, miete);
                    darsteller.ausgabe("Dein neues Kapital ist " + gradDran.getKapital() + "€.");
                    return;
                case "ü":
                    orgHilfe.übersichtAnzeigen();
                    break;
            }
        }
    }

    private void grundstückVersteigern(Grundstück grundstück){
        // TODO implementieren
        darsteller.ausgabe("Debug: Endpunkt, Versteigerung von " + grundstück.getName());
    }
}
