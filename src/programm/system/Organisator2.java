package programm.system;

import programm.grundstücke.Grundbuch;
import programm.grundstücke.Grundstück;
import programm.karten.Ereigniskarte;
import programm.karten.Kartenmanager;
import programm.system.enums.Felder;

public class Organisator2 {
    Spielleiter spielleiter;
    Darsteller darsteller;
    Würfel würfel;
    Grundbuch grundbuch;
    Kartenmanager kartenmanager;

    public Organisator2(Spielleiter spielleiter, Darsteller darsteller, Würfel würfel, Grundbuch grundbuch, Kartenmanager kartenmanager) {
        this.spielleiter = spielleiter;
        this.darsteller = darsteller;
        this.würfel = würfel;
        this.grundbuch = grundbuch;
        this.kartenmanager = kartenmanager;
    }

    public void gameLoop(){
        Boolean spielLäuft = true;

        // Außenloop, der das ganze Spiel läuft
        while (spielLäuft){
            Boolean zugBeendet = false;

            // Hier wird einmal geschrieben wer gerade dran ist
            Spieler geradeDran = spielleiter.getGeradeDran();
            darsteller.ausgabe(geradeDran.getName() + " (" + geradeDran.getSymbol() + ") ist dran.");

            // sofern der Spieler nicht im Gefängnis ist muss er immer erst würfeln
            if (!spielleiter.getGeradeDran().getIstImGefängnis()){
                darsteller.eingabeFragen("Als erstes musst du würfeln. Drücke 'w'", new String[]{"w"});
                würfelnUndDarstellen();
            }
            while (!zugBeendet){
                feldAbarbeiten();

                // Endabfrage loop
                while (!zugBeendet){
                    // Abfrage erstellen: immer 'ü' aber unterscheiden zwischen 'w' bei pasch und 'z' sonst
                    // bei 'w' gewählt: break mit label nach dem endabfrage loop
                    // bei 'z' gewählt: zugBeendet = true
                    // bei 'ü' geählt: zur Übersicht und von da zurück in diesen loop
                }
            }
        }
    }

    private void würfelnUndDarstellen(){
        int [] wurf = würfel.würfeln();
        if (würfel.mussInsGefängnis()){
            // TODO ins Gefängnis für zu viele Pasche
        }else {
            spielleiter.spielerBewegen(wurf[2]);
        }
        darsteller.brettZeichnen();
        darsteller.spielerHatGeworfen(wurf);
    }

    private void feldAbarbeiten(){
        Felder feld = spielleiter.getGeradeDran().getAktuellePos();

        // 1. schauen ob frei - return oder weiter
        Felder [] freieFelder = new Felder[]{Felder.Los, Felder.Gefängnis_bzw_Besuch};  // später auch noch frei parken
        for (Felder evtlFrei : freieFelder) {
            if (feld.equals(evtlFrei)){
                return;
            }
        }
        // 2. Kartenmanager fragen - bei Karte abarbeiten und dann abbrechen, bei null weiter
        Ereigniskarte karte = kartenmanager.karteZiehen(feld);
        if (karte != null){
            karteAbarbeiten();
            return;
        }

        // 3. Grundbuch fragen
        //  - bei Grundstück fragen ob es wem gehört
        //      - Ja - Zur miete springen und dann abbrechen
        //      - Neine - zur Kauffrage springen und dann abbrechen
        //  - bei null - Fehler werfen
        Grundstück grundstück = grundbuch.grundstückVon(feld);
        if (grundstück == null){
            throw new IllegalStateException("Es wurde weder ein freies Feld, noch ein Karte, noch ein Grundstück gefunden!");
        }
        if (grundbuch.istZuVerkaufen(grundstück)){
            kaufenVon(grundstück);
        }else {
            mieteZahlenBei(grundstück);
        }
    }

    private void karteAbarbeiten(){
        // TODO Je nach Karte etwas anders machen
        System.out.println("Debug: Endpunkt, Karte wird hier abgearbeitet.");
    }

    private void kaufenVon(Grundstück grundstück){
        // TODO implementieren
        System.out.println("Debug: Endpunkt, Kaufen passiert hier.");
    }

    private void mieteZahlenBei(Grundstück grundstück){
        // TODO implementieren
        System.out.println("Debug: Endpunkt, Miete zahlen passiert hier.");
    }

    // Später wichtig, wenn man etwas kaufen/ bezahlen will aber nicht genug Geld hat, soll man die Verwaltung öffnen können
    private void nichtGenugKapital(){
        // TODO implementieren
    }
}
