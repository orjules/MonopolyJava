package programm.system;


import programm.grundstücke.Grundbuch;
import programm.grundstücke.Grundstück;
import programm.karten.Ereigniskarte;
import programm.karten.Kartenmanager;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

import java.util.ArrayList;

public class Organisator {
    Spielleiter spielleiter;
    Darsteller darsteller;
    Würfel würfel;
    Grundbuch grundbuch;
    Kartenmanager kartenmanager;

    // der wird so oft gebraucht und nur an einer Stelle geändert
    Spieler gradDran;

    public Organisator(Spielleiter spielleiter, Darsteller darsteller, Würfel würfel, Grundbuch grundbuch, Kartenmanager kartenmanager) {
        this.spielleiter = spielleiter;
        this.darsteller = darsteller;
        this.würfel = würfel;
        this.grundbuch = grundbuch;
        this.kartenmanager = kartenmanager;
    }

    public void gameLoop(){
        außenLoop: while (true){
            // Hier wird einmal geschrieben wer gerade dran ist
            gradDran = spielleiter.getGeradeDran();
            darsteller.ausgabe(gradDran.getName() + " (" + gradDran.getSymbol() + ") ist dran.");

            // sofern der Spieler nicht im Gefängnis ist muss er immer erst würfeln
            if (!gradDran.getIstImGefängnis()){
                darsteller.eingabeFragen("Als erstes musst du würfeln. Drücke 'w'", new String[]{"w"});
                würfelnUndDarstellen();
            }

            innenLoop: while (true){
                feldAbarbeiten();
                //  Überprüfen ob jemand aufgegeben bzw der letzte aufgegeben hat
                if (!gradDran.equals(spielleiter.getGeradeDran())){
                    if (!spielleiter.spielLäuft()){
                        darsteller.ausgabe("Das Spiel ist vorbei. " + spielleiter.getGeradeDran().getName() + " hat gewonnen.");
                        break außenLoop;
                    }
                    String ausgabe = "";
                    ausgabe += gradDran.getName() + " hat aufgegeben, ";
                    gradDran = spielleiter.getGeradeDran();
                    ausgabe += gradDran.getName() + " ist jetzt dran.";
                    darsteller.ausgabe(ausgabe);
                    break innenLoop;
                }

                endabfrageLoop: while (true){
                    // Abfrage erstellen: immer 'ü' aber unterscheiden zwischen 'w' bei pasch und 'z' sonst
                    String ausgabeText = "";
                    ArrayList<String> erlaubteEingaben = new ArrayList<>();
                    ausgabeText += "'ü' um die Übersicht zu öffnen\n";
                    erlaubteEingaben.add("ü");
                    if (würfel.darfNochmalWerfen()){
                        ausgabeText += "'w' um nochmal zu würfeln\n";
                        erlaubteEingaben.add("w");
                    }else {
                        ausgabeText += "'z' um den Zug zu beenden\n";
                        erlaubteEingaben.add("z");
                    }

                    // Abfragen und auswerten
                    darsteller.umbruch();
                    darsteller.ausgabe(gradDran.getName() + " ist fertig mit den wichtigen Dingen aber noch dran.");
                    String eingabe = darsteller.eingabeFragen(ausgabeText, erlaubteEingaben.toArray(new String[erlaubteEingaben.size()]));
                    switch (eingabe) {
                        case "w":
                            würfelnUndDarstellen();
                            break endabfrageLoop;
                        case "z":
                            spielleiter.weiter();
                            gradDran = spielleiter.getGeradeDran();
                            würfel.reset();
                            break innenLoop;
                        case "ü":
                            übersichtAnzeigen();
                            break;
                        default:
                            throw new IllegalStateException("Hier sollte ich nie hinkommen");
                    }
                }
            }
            if (!spielleiter.spielLäuft()){
                break außenLoop;
            }
        }
    }

    private void würfelnUndDarstellen(){
        int [] wurf = würfel.würfeln();
        String evtlText = null;
        if (würfel.mussInsGefängnis()){
            // TODO ins Gefängnis für zu viele Pasche
        }else {
            evtlText = spielleiter.spielerBewegen(wurf[2]);
        }
        darsteller.brettZeichnen();
        darsteller.spielerHatGeworfen(wurf);
        if (evtlText != null){
            darsteller.ausgabe(evtlText);
        }
    }

    private void feldAbarbeiten(){
        Felder feld = gradDran.getAktuellePos();

        // 1. schauen ob frei - return oder weiter
        Felder [] freieFelder = new Felder[]{Felder.Los, Felder.Gefängnis_bzw_Besuch};  // später auch noch frei parken
        for (Felder evtlFrei : freieFelder) {
            if (feld.equals(evtlFrei)){
                darsteller.ausgabe("Du bist auf " + feld.name() + " gelandet. Hier passiert nichts weiter.");
                return;
            }
        }
        // 2. Kartenmanager fragen - bei Karte abarbeiten und dann abbrechen, bei null weiter
        Ereigniskarte karte = kartenmanager.karteZiehen(feld);
        if (karte != null){
            karteAbarbeiten(karte);
            return;
        }

        // 3. Grundbuch fragen
        Grundstück grundstück = grundbuch.grundstückVon(feld);
        if (grundstück == null){
            throw new IllegalStateException("Es wurde weder ein freies Feld, noch ein Karte, noch ein Grundstück gefunden!");
        }
        Spieler besitzer = grundbuch.getBesitzerVon(grundstück);
        String ausgabe = "";
        ausgabe += "Du bist auf " + grundbuch.artikelFür(grundstück, false, false) +
                grundstück.getName() + " gelandet. ";
        if (besitzer == null){
            ausgabe += grundbuch.pronomenFür(grundstück, true) + "ist noch zu verkaufen.";
            darsteller.ausgabe(ausgabe);
            kaufenVon(grundstück);
            return;
        }else if (besitzer.equals(gradDran)){
            ausgabe += grundbuch.pronomenFür(grundstück, true) + "gehört dir.";
            darsteller.ausgabe(ausgabe);
            return;
        }else {
            ausgabe += grundbuch.pronomenFür(grundstück, true) + "gehört " + besitzer.getName() + ".";
            darsteller.ausgabe(ausgabe);
            mieteZahlenBei(grundstück, besitzer);
            return;
        }
    }

    private void karteAbarbeiten(Ereigniskarte karte){
        // TODO Je nach Karte etwas anders machen
        darsteller.ausgabe(karte.getBeschreibung());
    }

    private void kaufenVon(Grundstück grundstück){
        while (true){
            darsteller.umbruch();
            darsteller.ausgabe(
                    "Der Kaufpreis für " + grundbuch.artikelFür(grundstück, true, false)
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
            switch (darsteller.eingabeFragen(frage, erlaubteEingaben.toArray(new String[erlaubteEingaben.size()]))){
                case "a":
                    spielleiter.kapitalÄndernVon(gradDran, -grundstück.getGrundstücksWert());
                    darsteller.ausgabe(grundbuch.übertragenAn(grundstück, gradDran)
                            + " Dein neues Kapital ist: " + gradDran.getKapital());
                    return;
                case "n":
                    grundstückVersteigern(grundstück);
                    return;
                case "ü":
                    übersichtAnzeigen();
                    break;
                default:
            }
        }
    }

    private void mieteZahlenBei(Grundstück grundstück, Spieler besitzer){
        int miete = grundstück.mieteBerechnen(besitzer, grundbuch, würfel.getLetztenWurf());
        while (true){
            // Sagen was los ist
            darsteller.umbruch();
            darsteller.ausgabe("Die Miete von " + grundbuch.artikelFür(grundstück, false, false) +
                    grundstück.getName() + " ist " + miete + "€.");

            // Bestätigung fragen, bzw weiterleiten, wenn man zu wenig Geld hat
            String frage = "";
            ArrayList<String> erlaubteEingaben = new ArrayList<>();
            if (gradDran.getKapital() - miete < 0){
                nichtGenugKapital(miete);
                // Hier hat der Spieler evtl aufgegeben, in dem Fall soll das Bezahlen abgebrochen werden, sonst bezahlen lassen
                if (!gradDran.equals(spielleiter.getGeradeDran()) || !spielleiter.spielLäuft()){
                    return;
                }
            }else {
                frage += "'a' um das Bezahlen zu bestätigen\n";
                erlaubteEingaben.add("a");
            }
            frage += "'ü' um die Übersicht zu öffnen";
            erlaubteEingaben.add("ü");
            switch (darsteller.eingabeFragen(frage, erlaubteEingaben.toArray(new String[erlaubteEingaben.size()]))){
                case "a":
                    spielleiter.geldÜbertragen(gradDran, besitzer, miete);
                    darsteller.ausgabe("Dein neues Kapital ist " + gradDran.getKapital() + "€.");
                    return;
                case "ü":
                    übersichtAnzeigen();
                    break;
            }
        }
    }

    private void übersichtAnzeigen(){
        darsteller.ausgabe("Dein momentanes Kapital ist: " + gradDran.getKapital() + "€.");
        darsteller.grundstückÜbersicht(grundbuch.alleGrundstückeVon(gradDran));
        darsteller.ausgabe("DEBUG: Häuser ver-/kaufen und Handel ist noch nicht implementiert.");
    }

    // Später wichtig, wenn man etwas kaufen/ bezahlen will aber nicht genug Geld hat, soll man die Verwaltung öffnen können
    private void nichtGenugKapital(int zuZahlen){
        while (true){
            // 1. ausrechnen wie viel zu wenig
            int neuesKapital = gradDran.getKapital() - zuZahlen;
            // 2. Überprüfen ob abgebrochen werden kann
            if (neuesKapital > 0){
                return;
            }
            // 3. sagen, wie viel zu wenig und sagen man kann zur Übersicht oder aufgeben und gleich verabrbeiten
            switch (darsteller.eingabeFragen("Dir fehlen " + (-neuesKapital) + "€. Du muss in der Übersicht " +
                            "etwas verkaufen oder das Spiel aufgeben.\n'ü' um die Übersicht zu öffnen\n'x' um aufzugeben",
                    new String[]{"ü","x"})){
                case "ü":
                    übersichtAnzeigen();
                    break;
                case "x":
                    spielleiter.aufgeben();
                    return;
            }
        }
    }

    private void grundstückVersteigern(Grundstück grundstück){
        // TODO implementieren
        darsteller.ausgabe("Debug: Endpunkt, Versteigerung von " + grundstück.getName());
    }
}
