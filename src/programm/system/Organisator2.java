package programm.system;

import programm.system.enums.Felder;
import programm.system.interfaces.IEreigniskarte;
import programm.system.interfaces.IGrundstück;
import programm.system.interfaces.IGrundbuch;
import programm.system.interfaces.IKartenmanager;
import programm.system.interfaces.IDarsteller;
import programm.system.interfaces.ISpielleiter;

import java.util.ArrayList;

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
        außenLoop: while (true){
            // Hier wird einmal geschrieben wer gerade dran ist
            Spieler geradeDran = spielleiter.getGeradeDran();
            darsteller.ausgabe(geradeDran.getName() + " (" + geradeDran.getSymbol() + ") ist dran.");

            // sofern der Spieler nicht im Gefängnis ist muss er immer erst würfeln
            if (!spielleiter.getGeradeDran().getIstImGefängnis()){
                darsteller.eingabeFragen("Als erstes musst du würfeln. Drücke 'w'", new String[]{"w"});
                würfelnUndDarstellen();
            }

            innenLoop: while (true){
                feldAbarbeiten();

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
                    String eingabe = darsteller.eingabeFragen(ausgabeText, erlaubteEingaben.toArray(new String[erlaubteEingaben.size()]));
                    switch (eingabe) {
                        case "w":
                            würfelnUndDarstellen();
                            break endabfrageLoop;
                        case "z":
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
        if (würfel.mussInsGefängnis()){
            // TODO ins Gefängnis für zu viele Pasche
        }else {
            spielleiter.spielerBewegen(wurf[2]);
        }
        darsteller.brettZeichnen();
        darsteller.spielerHatGeworfen(wurf);
    }

    private void übersichtAnzeigen(){
        // TODO Übersicht implementieren
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
        IGrundstück grundstück = grundbuch.grundstückVon(feld);
        if (grundstück == null){
            throw new IllegalStateException("Es wurde weder ein freies Feld, noch ein Karte, noch ein Grundstück gefunden!");
        }
        if (grundbuch.istZuVerkaufen(grundstück)){
            darsteller.ausgabe(grundbuch.textFürGelandetAuf(grundstück) + " Der Kaufpreis ist " + grundstück.getGrundstücksWert() + "€. Dein Kapital ist "
                    + spielleiter.getGeradeDran().getKapital() + "€.");
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
