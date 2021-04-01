package programm.grundstücke;

import programm.system.spieler.Spieler;
import programm.system.Felder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Grundbuch{

    Grundstück[] alleGrundstücke;
    Farbgruppen[] alleFarbgruppen;
    Map<Grundstück, Spieler> grundbuch = new HashMap<>();

    public Grundbuch(Grundstück[] alleGrundstücke, Farbgruppen[] alleFarbgruppen) {
        this.alleGrundstücke = alleGrundstücke;
        this.alleFarbgruppen = alleFarbgruppen;

        // hier die Werte für grundbuch initialisieren
        for (Grundstück grund : alleGrundstücke){
            grundbuch.put(grund, null);
        }
    }


    // Funktionen zu Status ändern
    public void übertragenAn(Grundstück grundstück, Spieler an){
        grundbuch.put(grundstück, an);
    }


    // Funktionen für Anfragen
    public Grundstück grundstückVon(Felder feld){
        for (Grundstück grund : alleGrundstücke){
            if (grund.getFeld() == feld){
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
        throw new IllegalArgumentException("Dieses Grundstück gibt es nicht im Grundbuch!");
    }

    public Grundstück[] alleGrundstückeVon(Spieler spieler){
        ArrayList<Grundstück> ausgabe = new ArrayList<>();
        for (Map.Entry<Grundstück, Spieler> entry : grundbuch.entrySet()){
            if (entry.getValue() != null && entry.getValue().equals(spieler)){
                ausgabe.add(entry.getKey());
            }
        }
        return ausgabe.toArray(new Grundstück[0]);
    }


    // Hilfsfunktionen für Klassen im Package
    public int anzahlBahnhöfeVon(Spieler besitzer){
        int anzahl = 0;
        for (Map.Entry<Grundstück, Spieler> entry : grundbuch.entrySet()){
            if (entry.getValue() != null && entry.getValue().equals(besitzer) && entry.getKey() instanceof Bahnhof){
                anzahl ++;
            }
        }
        return anzahl;
    }

    boolean hatBeideWerke(Spieler besitzer){
        int anzahl = 0;
        for (Map.Entry<Grundstück, Spieler> entry : grundbuch.entrySet()){
            if (entry.getValue() != null && entry.getValue().equals(besitzer) && entry.getKey() instanceof Werk){
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

    // Funktionen für die Tests
    Map<Grundstück, Spieler> getGrundbuch(){
        return grundbuch;
    }
}
