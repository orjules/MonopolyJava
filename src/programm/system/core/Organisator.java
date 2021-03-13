package programm.system.core;


import programm.grundstücke.Grundbuch;
import programm.grundstücke.Grundstück;
import programm.karten.Ereigniskarte;
import programm.karten.Kartenmanager;
import programm.system.Darsteller;
import programm.system.Felder;
import programm.system.Würfel;
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

    // Vorerst fest, später evtl im Constructor injected
    Org_Hilfe orgHilfe;

    public Organisator(Spielleiter spielleiter, Darsteller darsteller, Würfel würfel, Grundbuch grundbuch,
                       Kartenmanager kartenmanager) {
        this.spielleiter = spielleiter;
        this.darsteller = darsteller;
        this.würfel = würfel;
        this.grundbuch = grundbuch;
        this.kartenmanager = kartenmanager;
        orgHilfe = new Org_Hilfe(darsteller, grundbuch, spielleiter);
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
            // TODO Überprüfen ob spieler im Gefängnis ist: -> script fürs evtl frei kommen
            //  solange in dem Script bis frei oder eben nicht (man darf darin die Übersicht öffnen)
            //  wenn frei normal weiter, sonst runde beenden

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
                            orgHilfe.übersichtAnzeigen();
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
        // Diese werden nur in dieser Funktion gebraucht
        Org_Karten orgKarten = new Org_Karten(darsteller, kartenmanager, orgHilfe, gradDran);
        Org_Grundstücke orgGrundstücke = new Org_Grundstücke(spielleiter, darsteller, grundbuch, orgHilfe, gradDran);

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
            orgKarten.karteAbarbeiten(karte);
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
            orgGrundstücke.kaufenVon(grundstück);
            return;
        }else if (besitzer.equals(gradDran)){
            ausgabe += grundbuch.pronomenFür(grundstück, true) + "gehört dir.";
            darsteller.ausgabe(ausgabe);
            return;
        }else {
            ausgabe += grundbuch.pronomenFür(grundstück, true) + "gehört " + besitzer.getName() + ".";
            darsteller.ausgabe(ausgabe);
            int miete = grundstück.mieteBerechnen(besitzer, grundbuch, würfel.getLetztenWurf());
            orgGrundstücke.mieteZahlenBei(grundstück, besitzer, gradDran, miete);
            return;
        }
    }
}
