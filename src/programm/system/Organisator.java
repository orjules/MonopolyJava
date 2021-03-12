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
                    darsteller.umbruch();
                    darsteller.ausgabe(geradeDran.getName() + " ist fertig mit den wichtigen Dingen aber noch dran.");
                    String eingabe = darsteller.eingabeFragen(ausgabeText, erlaubteEingaben.toArray(new String[erlaubteEingaben.size()]));
                    switch (eingabe) {
                        case "w":
                            würfelnUndDarstellen();
                            break endabfrageLoop;
                        case "z":
                            spielleiter.weiter();
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
        }else if (besitzer.equals(spielleiter.getGeradeDran())){
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
                            + spielleiter.getGeradeDran().getKapital() + "€.");
            String eingabe = darsteller.eingabeFragen(
                    "'a' um das Grundstück zu kaufen\n'n' um das Grundstück nicht zu kaufen\n'ü' um zur Übersicht zu gehen",
                    new String[]{"a", "n", "ü"});
            if (eingabe.equals("a")){
                spielleiter.kapitalÄndernVon(spielleiter.getGeradeDran(), -grundstück.getGrundstücksWert());
                darsteller.ausgabe(grundbuch.übertragenAn(grundstück, spielleiter.getGeradeDran())
                        + " Dein neues Kapital ist: " + spielleiter.getGeradeDran().getKapital());
                return;
            }else if (eingabe.equals("n")){
                grundstückVersteigern(grundstück);
                return;
            }else if (eingabe.equals("ü")){
                übersichtAnzeigen();
            }
        }
    }

    private void mieteZahlenBei(Grundstück grundstück, Spieler besitzer){
        int miete = grundstück.mieteBerechnen(besitzer, grundbuch, würfel.getLetztenWurf());
        darsteller.ausgabe("Die Miete von " + grundbuch.artikelFür(grundstück, false, false) +
                grundstück.getName() + " ist " + miete + "€.");
        spielleiter.geldÜbertragen(spielleiter.getGeradeDran(), besitzer, miete);
    }

    private void übersichtAnzeigen(){
        darsteller.ausgabe("Dein momentanes Kapital ist: " + spielleiter.getGeradeDran().getKapital() + "€.");
        darsteller.grundstückÜbersicht(grundbuch.alleGrundstückeVon(spielleiter.getGeradeDran()));
    }

    // Später wichtig, wenn man etwas kaufen/ bezahlen will aber nicht genug Geld hat, soll man die Verwaltung öffnen können
    private void nichtGenugKapital(){
        // TODO implementieren
    }

    private void grundstückVersteigern(Grundstück grundstück){
        // TODO implementieren
        darsteller.ausgabe("Debug: Endpunkt, Versteigerung von " + grundstück.getName());
    }
}
