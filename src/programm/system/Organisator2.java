package programm.system;

import programm.system.enums.Felder;

public class Organisator2 {
    Spielleiter spielleiter;
    Darsteller darsteller;
    Würfel würfel;

    public Organisator2(Spielleiter spielleiter, Darsteller darsteller, Würfel würfel) {
        this.spielleiter = spielleiter;
        this.darsteller = darsteller;
        this.würfel = würfel;
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
                // Feld checken/abarbeiten

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
        // 2. Kartenmanager fragen - bei Karte abarbeiten und dann abbrechen, bei null weiter
        // 3. Grundbuch fragen
        //  - bei Grundstück fragen ob es wem gehört
        //      - Ja - Zur miete springen und dann abbrechen
        //      - Neine - zur Kauffrage springen und dann abbrechen
        //  - bei null - Fehler werfen
    }
}
