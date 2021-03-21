package programm.grundstücke;

import programm.system.spieler.Spieler;
import programm.system.Felder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Grundbuch{

    Grundstück[] alleGrundstücke = new Grundstück[11]; // Vorläufig 11
    Farbgruppen[] alleFarbgruppen = new Farbgruppen[3]; // Vorläufig 3
    Map<Grundstück, Spieler> grundbuch = new HashMap<>();

    public Grundbuch() {
        // hier die Werte für alleGrundstücke initialisieren
        alleGrundstücke[0] = new Straße(Felder.Badstraße.name(), Felder.Badstraße, 60, 30, Farben.braun, 2, 10, 30,90, 160, 250, 50);
        alleGrundstücke[1] = new Straße(Felder.Turmstraße.name(), Felder.Turmstraße, 60, 30, Farben.braun, 4, 20, 60,180, 320, 450, 50);
        alleGrundstücke[2] = new Bahnhof(Felder.Südbahnhof.name(), Felder.Südbahnhof);
        alleGrundstücke[3] = new Straße(Felder.Chaussestraße.name(), Felder.Chaussestraße, 100, 50, Farben.hellblau, 6, 30, 90,270, 400, 550, 50);
        alleGrundstücke[4] = new Straße(Felder.Elisenstraße.name(), Felder.Elisenstraße, 100, 50, Farben.hellblau, 6, 30, 90,270, 400, 550, 50);
        alleGrundstücke[5] = new Straße(Felder.Poststraße.name(), Felder.Poststraße, 120, 60, Farben.hellblau, 8, 40, 100,300, 450, 600, 50);
        alleGrundstücke[6] = new Straße(Felder.Seestraße.name(), Felder.Seestraße, 140, 60, Farben.rosa, 8, 40, 100,300, 450, 600, 50);     // Bis auf Kaufpreis sind die Werte falsch
        alleGrundstücke[7] = new Werk(Felder.Elektrizitätswerk.name(), Felder.Elektrizitätswerk, 150, 60);                                                                                                                          // Hypothekswert ist falsch
        alleGrundstücke[8] = new Straße(Felder.Hafenstraße.name(), Felder.Hafenstraße, 120, 60, Farben.rosa, 8, 40, 100,300, 450, 600, 50); // Bis auf Kaufpreis sind die Werte falsch
        alleGrundstücke[9] = new Straße(Felder.NeueStraße.name(), Felder.NeueStraße, 120, 60, Farben.rosa, 8, 40, 100,300, 450, 600, 50);   // Bis auf Kaufpreis sind die Werte falsch
        alleGrundstücke[10] = new Bahnhof(Felder.Westbahnhof.name(), Felder.Westbahnhof);
        // hier die Werte für alleFarbgruppen initialisieren
        alleFarbgruppen[0] = new Farbgruppen(Farben.braun, new Straße[]{(Straße) alleGrundstücke[0], (Straße) alleGrundstücke[1]});
        alleFarbgruppen[1] = new Farbgruppen(Farben.hellblau, new Straße[]{(Straße) alleGrundstücke[3], (Straße) alleGrundstücke[4], (Straße) alleGrundstücke[5]});
        alleFarbgruppen[2] = new Farbgruppen(Farben.rosa, new Straße[]{(Straße) alleGrundstücke[6], (Straße) alleGrundstücke[8], (Straße) alleGrundstücke[9]});
        // hier die Werte für grundbuch initialisieren
        for (Grundstück grund : alleGrundstücke){
            grundbuch.put(grund, null);
        }
    }

    public Grundstück grundstückVon(Felder feld){
        for (Grundstück grund : alleGrundstücke){
            if (grund.feld == feld){
                return grund;
            }
        }
        return null;
    }

    public Spieler getBesitzerVon(Grundstück grundstück){
        for (Map.Entry<Grundstück, Spieler> entry : grundbuch.entrySet()){
            if (entry.getKey().equals(grundstück)){
                return entry.getValue();
            }
        }
        return null;
    }

    public Grundstück[] alleGrundstückeVon(Spieler spieler){
        ArrayList<Grundstück> ausgabe = new ArrayList<>();
        for (Map.Entry<Grundstück, Spieler> entry : grundbuch.entrySet()){
            if (entry.getValue() != null && entry.getValue().equals(spieler)){
                ausgabe.add(entry.getKey());
            }
        }
        return ausgabe.toArray(new Grundstück[ausgabe.size()]);
    }


    // gibt die Kaufbestätigung zurück
    public String übertragenAn(Grundstück grundstück, Spieler an){
        grundbuch.put(grundstück, an);
        return GrammatikHandler.artikelFür(grundstück, true, true) + grundstück.getName() + " gehört nun " + an.getName() + ".";
    }

    // Hilfsfunktionen für Klassen im Package
    int bahnhöfeVon(Spieler besitzer){
        int anzahl = 0;
        for (Map.Entry<Grundstück, Spieler> entry : grundbuch.entrySet()){
            if (entry.getValue() != null && entry.getValue().equals(besitzer) && entry.getKey().getClass().equals(Bahnhof.class)){
                anzahl ++;
            }
        }
        return anzahl;
    }
    boolean beideWerke(Spieler besitzer){
        int anzahl = 0;
        for (Map.Entry<Grundstück, Spieler> entry : grundbuch.entrySet()){
            if (entry.getValue() != null && entry.getValue().equals(besitzer) && entry.getKey().getClass().equals(Werk.class)){
                anzahl ++;
            }
        }
        if (anzahl == 1){
            return false;
        }else if (anzahl == 2){
            return true;
        }else {
            throw new IllegalStateException("Der Besitzer muss mind ein Werk haben, weil die Funktion sonst nicht aufgerufen wird.");
        }
    }
}
