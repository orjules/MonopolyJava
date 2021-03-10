package programm.system;

import programm.system.enums.Felder;
import programm.system.interfaces.IEreigniskarte;
import programm.system.interfaces.IGrundstück;
import programm.system.interfaces.IGrundbuch;
import programm.system.interfaces.IKartenmanager;
import programm.system.interfaces.IDarsteller;
import programm.system.interfaces.ISpielleiter;

public class Organisator2 {
    ISpielleiter spielleiter;
    IDarsteller darsteller;
    Würfel würfel;
    IGrundbuch grundbuch;
    IKartenmanager kartenmanager;

    public Organisator2(ISpielleiter spielleiter, IDarsteller darsteller, Würfel würfel, IGrundbuch grundbuch, IKartenmanager kartenmanager) {
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

    public void feldAbarbeiten(){
        Felder feld = spielleiter.getGeradeDran().getAktuellePos();

        // 1. schauen ob frei - return oder weiter
        Felder [] freieFelder = new Felder[]{Felder.Los, Felder.Gefängnis_bzw_Besuch};  // später auch noch frei parken
        for (Felder evtlFrei : freieFelder) {
            if (feld.equals(evtlFrei)){
                darsteller.ausgabe("Du bist auf " + feld.name() + " gelandet. Hier passiert nichts weiter.");
                return;
            }
        }
        // 2. Kartenmanager fragen - bei Karte abarbeiten und dann abbrechen, bei null weiter
        IEreigniskarte karte = kartenmanager.karteZiehen(feld);
        if (karte != null){
            karteAbarbeiten();
            return;
        }

        // 3. Grundbuch fragen
        //  - bei Grundstück fragen ob es wem gehört
        //      - Ja - Zur miete springen und dann abbrechen
        //      - Neine - zur Kauffrage springen und dann abbrechen
        //  - bei null - Fehler werfen
        IGrundstück grundstück = grundbuch.grundstückVon(feld);
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
        darsteller.ausgabe("Debug: Endpunkt, Karte wird hier abgearbeitet.");
    }

    private void kaufenVon(IGrundstück grundstück){
        // TODO implementieren
        darsteller.ausgabe("Debug: Endpunkt, Kaufen passiert hier.");
    }

    private void mieteZahlenBei(IGrundstück grundstück){
        // TODO implementieren
        darsteller.ausgabe("Debug: Endpunkt, Miete zahlen passiert hier.");
    }

    // Später wichtig, wenn man etwas kaufen/ bezahlen will aber nicht genug Geld hat, soll man die Verwaltung öffnen können
    private void nichtGenugKapital(){
        // TODO implementieren
    }
}
